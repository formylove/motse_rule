package ink.moshuier.motse.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


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
