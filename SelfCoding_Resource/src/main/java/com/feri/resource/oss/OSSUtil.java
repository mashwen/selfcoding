package com.feri.resource.oss;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;
import com.feri.resource.config.SystemConfig;

import java.io.*;
import java.net.URL;
import java.util.*;

/**
 *@Author feri
 *@Date Created in 2019/3/22 11:25
 * 基于阿里云的OSS对象关系存储  实现资源的存储操作
 */
public class OSSUtil {

    private OSSClient ossClient;

    public OSSUtil(String endpoint,String keyid,String keysecret){
        // Endpoint以杭州为例，其它Region请按实际情况填写。
//        String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
//        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
//        String accessKeyId = "<yourAccessKeyId>";
//        String accessKeySecret = "<yourAccessKeySecret>";

        // 创建OSSClient实例。
        ossClient = new OSSClient(endpoint, keyid, keysecret);

    }

    //新建存储空间
    public boolean createBucket(String name){
       if(! ossClient.doesBucketExist(name)) {
           // 创建存储空间。
           String bucketName = name;
           // 新建存储空间默认为标准存储类型，私有权限。
           ossClient.createBucket(bucketName);
           return true;
       }else{
           return false;
       }
    }
    //上传文件
    public String upload(byte[] data,String name){
        PutObjectResult result=ossClient.putObject(SystemConfig.BULCKNAME, name, new ByteArrayInputStream(data));
       //SystemConfig.BULCKNAME+ SystemConfig.ENDPOINT+name  对外访问的名称
        return getUrl(name);
    }
    //上传文件
    public String upload(InputStream inputStream,String name){
        PutObjectResult result=ossClient.putObject(SystemConfig.BULCKNAME, name, inputStream);
        //SystemConfig.BULCKNAME+ SystemConfig.ENDPOINT+name  对外访问的名称
        return getUrl(name);
    }
    //分片上传  适合大文件
    public String upload(String name,File file) throws IOException {
        /* 步骤1：初始化一个分片上传事件。
         */
        InitiateMultipartUploadRequest request = new InitiateMultipartUploadRequest(SystemConfig.BULCKNAME, name);
        InitiateMultipartUploadResult result = ossClient.initiateMultipartUpload(request);
        // 返回uploadId，它是分片上传事件的唯一标识，您可以根据这个ID来发起相关的操作，如取消分片上传、查询分片上传等。
        String uploadId = result.getUploadId();
        /* 步骤2：上传分片。
         */
        // partETags是PartETag的集合。PartETag由分片的ETag和分片号组成。
        List<PartETag> partETags =  new ArrayList<PartETag>();
        // 计算文件有多少个分片。
        final long partSize = 10 * 1024 * 1024L;   // 10MB
        long fileLength = file.length();
        int partCount = (int) (fileLength / partSize);
        if (fileLength % partSize != 0) {
            partCount++;
        }
        // 遍历分片上传。
        for (int i = 0; i < partCount; i++) {
            long startPos = i * partSize;
            long curPartSize = (i + 1 == partCount) ? (fileLength - startPos) : partSize;
            InputStream instream = new FileInputStream(file);
            // 跳过已经上传的分片。
            instream.skip(startPos);
            UploadPartRequest uploadPartRequest = new UploadPartRequest();
            uploadPartRequest.setBucketName(SystemConfig.BULCKNAME);
            uploadPartRequest.setKey(name);
            uploadPartRequest.setUploadId(uploadId);
            uploadPartRequest.setInputStream(instream);
            // 设置分片大小。除了最后一个分片没有大小限制，其他的分片最小为100KB。
            uploadPartRequest.setPartSize(curPartSize);
            // 设置分片号。每一个上传的分片都有一个分片号，取值范围是1~10000，如果超出这个范围，OSS将返回InvalidArgument的错误码。
            uploadPartRequest.setPartNumber( i + 1);
            // 每个分片不需要按顺序上传，甚至可以在不同客户端上传，OSS会按照分片号排序组成完整的文件。
            UploadPartResult uploadPartResult = ossClient.uploadPart(uploadPartRequest);
            // 每次上传分片之后，OSS的返回结果会包含一个PartETag。PartETag将被保存到partETags中。
            partETags.add(uploadPartResult.getPartETag());
        }
        /* 步骤3：完成分片上传。
         */
        // 排序。partETags必须按分片号升序排列。
        Collections.sort(partETags, new Comparator<PartETag>() {
            @Override
            public int compare(PartETag p1, PartETag p2) {
                return p1.getPartNumber() - p2.getPartNumber();
            }
        });
        // 在执行该操作时，需要提供所有有效的partETags。OSS收到提交的partETags后，会逐一验证每个分片的有效性。当所有的数据分片验证通过后，OSS将把这些分片组合成一个完整的文件。
        CompleteMultipartUploadRequest completeMultipartUploadRequest =
                new CompleteMultipartUploadRequest(SystemConfig.BULCKNAME, name, uploadId, partETags);
        ossClient.completeMultipartUpload(completeMultipartUploadRequest);
        return getUrl(name);
    }

    //断点续传 和分片
    public void uploadDDXC(String name,File file) throws Throwable {
        ObjectMetadata meta = new ObjectMetadata();
        // 指定上传的内容类型。
        meta.setContentType("text/plain");

        // 通过UploadFileRequest设置多个参数。
        UploadFileRequest uploadFileRequest = new UploadFileRequest(SystemConfig.BULCKNAME,name);
// 指定上传的本地文件。
        uploadFileRequest.setUploadFile(file.getAbsolutePath());
// 指定上传并发线程数，默认为1。
        uploadFileRequest.setTaskNum(5);
// 指定上传的分片大小，范围为100KB~5GB，默认为文件大小/10000。
        uploadFileRequest.setPartSize(10 * 1024 * 1024);
// 开启断点续传，默认关闭。
        uploadFileRequest.setEnableCheckpoint(true);
// 记录本地分片上传结果的文件。开启断点续传功能时需要设置此参数，上传过程中的进度信息会保存在该文件中，如果某一分片上传失败，再次上传时会根据文件中记录的点继续上传。上传完成后，该文件会被删除。默认与待上传的本地文件同目录，为uploadFile.ucp。
        uploadFileRequest.setCheckpointFile("cache.txt");
// 文件的元数据。
        uploadFileRequest.setObjectMetadata(meta);
// 设置上传成功回调，参数为Callback类型。
        uploadFileRequest.setCallback(new Callback(){
            @Override
            public String getCallbackUrl() {
                return super.getCallbackUrl();
            }
        });

        // 断点续传上传。
        ossClient.uploadFile(uploadFileRequest);
    }
    //获取上传文件的访问路径
    public String getUrl(String name){
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.YEAR,1);
        return ossClient.generatePresignedUrl(SystemConfig.BULCKNAME,name,calendar.getTime()).toString();
    }
    //下载
    public String download(String name) throws IOException {
        // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
        OSSObject ossObject = ossClient.getObject(SystemConfig.BULCKNAME, name);
// 读取文件内容。
        BufferedReader reader = new BufferedReader(new InputStreamReader(ossObject.getObjectContent()));
        StringBuffer buffer=new StringBuffer();
        while (true) {
            String line = reader.readLine();
            if (line == null){ break;}
            buffer.append(line);

        }
// 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
        reader.close();
        return buffer.toString();

    }
    //下载
    public boolean down(String name,File file){
        // 下载OSS文件到本地文件。如果指定的本地文件存在会覆盖，不存在则新建。
        ossClient.getObject(new GetObjectRequest(SystemConfig.BULCKNAME, name), file);
        return true;
    }
    //验证文件是否存在
    public boolean exists(String name){
        return ossClient.doesObjectExist(SystemConfig.BULCKNAME, name);
    }
    //获取存储空间的所有的对象名称
    public List<String> list(){
        return ossClient.listObjects(SystemConfig.BULCKNAME).getCommonPrefixes();
    }
    //删除
    public boolean del(String name){
        // 删除文件。
        ossClient.deleteObject(SystemConfig.BULCKNAME, name);
        return true;
    }

}
