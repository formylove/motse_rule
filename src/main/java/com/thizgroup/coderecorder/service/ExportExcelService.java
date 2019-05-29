package com.thizgroup.coderecorder.service;

import com.thizgroup.coderecorder.bean.bo.RecordBean;
import com.thizgroup.coderecorder.dao.RecordDao;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 */
@Service
public class ExportExcelService {
  @Autowired RecordDao recordDao;

  @Value("${export.directory}")
  private String directory;

  public void exportExcel() {
    LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
    String currentDate = localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

    // 创建新工作簿
    HSSFWorkbook workbook = new HSSFWorkbook();
    // 新建工作表
    final HSSFSheet sheet = workbook.createSheet("hello");
    HSSFRow row0 = sheet.createRow(0);
    HSSFCell title = row0.createCell(0);
    title.setCellValue("ID");
    title = row0.createCell(1);
    title.setCellValue("邀请码");
    title = row0.createCell(2);
    title.setCellValue("手机号");
    title = row0.createCell(3);
    title.setCellValue("时间");
    final List<RecordBean> allRecords = recordDao.getAllRecords().stream().sorted((t1,t2)->t1.getId().compareTo(t2.getId())).collect(Collectors.toList());
    IntStream.range(1, allRecords.size())
        .forEach(
            index -> {
              HSSFRow row = sheet.createRow(index);

              RecordBean recordBean = allRecords.get(index);
              HSSFCell cell = row.createCell(0);
              cell.setCellValue(recordBean.getId());
              cell = row.createCell(1);
              cell.setCellValue(recordBean.getInviteCode());
              cell = row.createCell(2);
              cell.setCellValue(recordBean.getMobile());
              cell = row.createCell(3);
              cell.setCellValue(Instant.ofEpochMilli(recordBean.getCreateTime()).toString());
            });

    // 创建行,行号作为参数传递给createRow()方法,第一行从0开始计算
    // 创建单元格,row已经确定了行号,列号作为参数传递给createCell(),第一列从0开始计算
    // 设置单元格的值,即C1的值(第一行,第三列)
    // 输出到磁盘中
    FileOutputStream fos = null;
    try {
      fos = new FileOutputStream(new File(MessageFormat.format(directory, currentDate)));
      workbook.write(fos);

      workbook.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        fos.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
