package com.thizgroup.coderecorder.schedule;

import com.thizgroup.coderecorder.dao.RecordDao;
import com.thizgroup.coderecorder.service.ExportExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author : Sarah Xu
 * @date : 2019-05-29
 **/
@Component
@EnableScheduling   // 1.开启定时任务
@EnableAsync        // 2.开启多线程
@PropertySource(value = "classpath:application-dev.yml")
public class ExportExcelSchedule {
    @Autowired
    ExportExcelService exportExcelService;
    @Async
    @Scheduled(cron = "${export.cron}")
    public void export() throws InterruptedException {
    exportExcelService.exportExcel();

        System.out.println("第一个定时任务开始 : " + LocalDateTime.now().toLocalTime() + "\r\n线程 : " + Thread.currentThread().getName());
    }

}
