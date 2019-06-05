package com.thizgroup.promotion.service.service;

import com.thizgroup.promotion.dao.RecordDao;
import com.thizgroup.promotion.dao.bean.RecordBean;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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
  private final RecordDao recordDao;

  @Autowired
  public ExportExcelService(RecordDao recordDao) {
    this.recordDao = recordDao;
  }
  /**
   * @Description: 导出excel @Param: [destination]
   *
   * @return: void @Author: Sarah Xu @Date: 2019/6/5
   */
  public void exportExcel(OutputStream destination) {
    LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
    String currentDate = localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

    // 创建新工作簿
    HSSFWorkbook workbook = new HSSFWorkbook();
    // 新建工作表
    final HSSFSheet sheet = workbook.createSheet("hello");
    sheet.setColumnWidth(0, 3350);
    sheet.setColumnWidth(1, 3100);
    sheet.setColumnWidth(2, 5200);
    sheet.setColumnWidth(3, 13300);
    HSSFRow row0 = sheet.createRow(0);
    HSSFCell title = row0.createCell(0);
    title.setCellValue("数据库存储ID");
    title = row0.createCell(1);
    title.setCellValue("邀请码");
    title = row0.createCell(2);
    title.setCellValue("手机号");
    title = row0.createCell(3);
    title.setCellValue("时间");
    final List<RecordBean> allRecords =
        recordDao.getAllRecords().stream()
            .sorted((t1, t2) -> t1.getId().compareTo(t2.getId()))
            .collect(Collectors.toList());
    IntStream.range(0, allRecords.size())
        .forEach(
            index -> {
              HSSFRow row = sheet.createRow(index + 1);

              RecordBean recordBean = allRecords.get(index);
              HSSFCell cell = row.createCell(0);
              cell.setCellValue(recordBean.getId());
              cell = row.createCell(1);
              cell.setCellValue(recordBean.getInviteCode());
              cell = row.createCell(2);
              cell.setCellValue(recordBean.getMobile());
              cell = row.createCell(3);
              cell.setCellValue(
                  ZonedDateTime.ofInstant(
                          Instant.ofEpochMilli(recordBean.getCreationDate()),
                          ZoneId.systemDefault())
                      .toLocalDateTime()
                      .toString());
            });

    // 创建行,行号作为参数传递给createRow()方法,第一行从0开始计算
    // 创建单元格,row已经确定了行号,列号作为参数传递给createCell(),第一列从0开始计算
    // 设置单元格的值,即C1的值(第一行,第三列)
    // 输出到磁盘中
    try {
      workbook.write(destination);
      workbook.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
