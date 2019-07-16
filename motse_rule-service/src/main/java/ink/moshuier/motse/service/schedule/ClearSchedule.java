package ink.moshuier.motse.service.schedule;

import ink.moshuier.motse.dao.util.ConstantUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;

/**
 * @author : Sarah Xu
 * @date : 2019-05-29
 */
@Component
@EnableScheduling // 1.开启定时任务
@Slf4j
public class ClearSchedule {



/**
* @Description: 每日十二点结算当天收益
*/

  @Scheduled(cron = "${clear.cron}")
  public void clear() throws InterruptedException {


  }
}
