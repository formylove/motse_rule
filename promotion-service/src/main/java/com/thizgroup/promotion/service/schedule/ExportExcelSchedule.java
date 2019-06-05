package com.thizgroup.promotion.service.schedule;

import com.thizgroup.promotion.model.util.ConstantUtils;
import com.thizgroup.promotion.service.service.ExportExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDateTime;

/**
 * @author : Sarah Xu
 * @date : 2019-05-29
 */
@Component
@EnableScheduling // 1.开启定时任务
@EnableAsync // 2.开启多线程
@PropertySource(value = "classpath:application-dev.yml") // todo
public class ExportExcelSchedule {
  @Autowired ExportExcelService exportExcelService;

  @Value("${export.directory}")
  private String directory;

  @Async
  @Scheduled(cron = "${export.cron}")
  public void export() throws InterruptedException {
    FileOutputStream fos = null;

    try {
      fos = new FileOutputStream(new File(directory + ConstantUtils.generateExcelFileName()));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    exportExcelService.exportExcel(fos);

    System.out.println(
        "第一个定时任务开始 : "
            + LocalDateTime.now().toLocalTime()
            + "\r\n线程 : "
            + Thread.currentThread().getName());
  }
}
