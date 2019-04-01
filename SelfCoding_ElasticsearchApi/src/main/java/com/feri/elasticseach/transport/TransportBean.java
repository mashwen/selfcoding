package com.feri.elasticseach.transport;

import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 *@Author feri
 *@Date Created in 2019/3/28 13:34
 */
public class TransportBean {
    private TransportClient client;
    //"qfjava"
    //"39.105.189.141"
    //9300
    public TransportBean(String clustername,String ip,int port){
        //1、创建配置信息  --必须指定集群名称  查看es服务器  ip:9200
        Settings settings=Settings.builder().put("cluster.name",clustername).build();
        //2、创建客户端对象 --必须指定服务的ip和对应的端口号
        client=new PreBuiltTransportClient(settings).addTransportAddress(
                new TransportAddress(new InetSocketAddress(ip,port)));
    }
    //新增
    public boolean save(String indexName,String type,String id,String data){
        System.out.println(data);
        //新增数据
        IndexResponse response=client.prepareIndex(indexName,type,id).
                setSource(data,XContentType.JSON).get();
        return response.status().getStatus()>0;
    }
    //批量新增
    public boolean saveBatch(String indexName,String type,Map<String,String> data){
        //批量
        BulkRequestBuilder bulkRequestBuilder=client.prepareBulk();
        for(String id:data.keySet()){
            bulkRequestBuilder.add(client.prepareIndex(indexName,type,id).
                    setSource(data,XContentType.JSON));
        }
       BulkResponse bulkResponse= bulkRequestBuilder.execute().actionGet();
        return !bulkResponse.hasFailures();
    }

    //修改
    public boolean update(String indexName,String type,String id,String data){
        //修改数据
        UpdateResponse response=client.prepareUpdate(indexName,type,id).
                setDoc(data,XContentType.JSON).get();
        return response.status().getStatus()>0;
    }
    //删除
    public boolean delete(String indexName,String type,String id){
        //修改数据
        DeleteResponse response=client.prepareDelete(indexName,type,id).get();
        return response.status().getStatus()>0;
    }
    //删除
    public boolean deleteBatch(String indexName,String type,List<String> ids){
        //批量
        BulkRequestBuilder bulkRequestBuilder=client.prepareBulk();
        for(String id:ids){
            bulkRequestBuilder.add(client.prepareDelete(indexName,type,id));
        }
        BulkResponse bulkResponse= bulkRequestBuilder.execute().actionGet();
        return !bulkResponse.hasFailures();
    }
    //查询单个
    public String queryById(String indexName,String type,String id){
        return client.prepareGet(indexName,type,id).get().getSourceAsString();
    }
    //查询某个字段的内容对应的数据
    public String queryByMsg(String indexName,String type,String field,String msg){
        //创建字段和值的对应查询
        TermQueryBuilder termQueryBuilder=QueryBuilders.termQuery(field,msg);
        //查询条件构建器
        SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
        //设置对应的查询条件
        searchSourceBuilder.query(termQueryBuilder);
      // searchSourceBuilder.sort("id",SortOrder.DESC);
        SearchRequest request=new SearchRequest(indexName);
        request.source(searchSourceBuilder);
        try {
            SearchResponse searchResponse=client.search(request).get();
            return searchResponse.getHits().getAt(0).getSourceAsString();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
    //模糊查询
    //查询某个字段的内容对应的数据
    public List<String> queryLike(String indexName,String type,String field,String msg,int index,int count){
        //创建字段和值的对应查询
        WildcardQueryBuilder termQueryBuilder=QueryBuilders.wildcardQuery(field,"*"+msg+"*");
        //查询条件构建器
        SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
        //设置对应的查询条件
        searchSourceBuilder.query(termQueryBuilder);
        //设置分页
        searchSourceBuilder.from(index);//起始
        searchSourceBuilder.size(count);//数量
        SearchRequest request=new SearchRequest(indexName);
        request.source(searchSourceBuilder);
        try {
            SearchResponse searchResponse=client.search(request).actionGet();
            SearchHits searchHits=searchResponse.getHits();
            List<String> data=new ArrayList<>();
            for(SearchHit sh:searchHits){
                data.add(sh.getSourceAsString());
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }





}
