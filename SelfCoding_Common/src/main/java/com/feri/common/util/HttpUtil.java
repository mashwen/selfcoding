package com.feri.common.util;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 *@Author feri
 *@Date Created in 2019/1/23 15:35
 */
public class HttpUtil {
    //

    /**
     * http 请求
     * @param data 请求的参数数据
     * @param url 请求路径
     * @return
     * @throws Exception
     */
    public static String httpRequest(String data, String url,int timeout,String agent) throws Exception{
        BasicHttpClientConnectionManager connManager;
        connManager = new BasicHttpClientConnectionManager(
                RegistryBuilder.<ConnectionSocketFactory>create()
                        .register("http", PlainConnectionSocketFactory.getSocketFactory())
                        .register("https", SSLConnectionSocketFactory.getSocketFactory())
                        .build(),
                null,
                null,
                null
        );
        HttpClient httpClient = HttpClientBuilder.create()
                .setConnectionManager(connManager)
                .build();

        HttpPost httpPost = new HttpPost(url);

        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build();
        httpPost.setConfig(requestConfig);

        StringEntity postEntity = new StringEntity(data, "UTF-8");
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.addHeader("User-Agent", agent);
        httpPost.setEntity(postEntity);
        HttpResponse httpResponse = httpClient.execute(httpPost);
        HttpEntity httpEntity = httpResponse.getEntity();
        return EntityUtils.toString(httpEntity, "UTF-8");
    }
    //
    public static String getDataStr(String url,String method,String data){
        try {
            //1、获取超链接资源对象
            URL dataurl=new URL(url);
            //2、获取连接对象
            HttpURLConnection connection=(HttpURLConnection) dataurl.openConnection();
            //3、设置请求方式
            connection.setRequestMethod(method);
            if(method.equals("POST")){
                connection.setDoOutput(true);
                OutputStream os=connection.getOutputStream();
                os.write(data.getBytes());
                os.flush();
                System.out.println(data);
            }
            //4、设置请求信息
            connection.setConnectTimeout(5000);
            //5、验证响应码
            if(connection.getResponseCode()==200){
                return IOUtils.toString(connection.getInputStream(),"UTF-8");
            }
            System.out.println(connection.getResponseCode());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
