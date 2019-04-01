package com.feri.admin.quartz;

import com.feri.common.util.HttpUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Random;

/**
 *@Author feri
 *@Date Created in 2019/3/28 15:04
 */
public class EsJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        long id=System.currentTimeMillis();
        Random random=new Random();
        HttpUtil.getDataStr("http://localhost:17888/essave.do","POST","indeName=selfcoding&type=user&id="+id
                +"&json={\"id\":"+id+",\"name\":\"java1807"+random.nextInt()+"\",\"age\":"+(random.nextInt(100)+6)+"}");

    }
}
