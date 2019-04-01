package com.feri.common.util;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import java.net.InetSocketAddress;

/**
 *@Author feri
 *@Date Created in 2019/3/25 13:52
 */
public class ElasticsearchUtil {
    private TransportClient client;
    public ElasticsearchUtil(String clustername,String host,int port){
        //1、创建配置信息  --必须指定集群名称  查看es服务器  ip:9200
        Settings settings=Settings.builder().put("cluster.name",clustername).build();
        //2、创建客户端对象 --必须指定服务的ip和对应的端口号
        client=new PreBuiltTransportClient(settings).addTransportAddress(
                new TransportAddress(new InetSocketAddress(host,port)));
    }
    //新增
    public boolean save(String json){
        return false;
    }
    //批量新增

}
