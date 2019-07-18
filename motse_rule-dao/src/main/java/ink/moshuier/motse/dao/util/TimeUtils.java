package ink.moshuier.motse.dao.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

/**
 * @author : Sarah Xu
 * @date : 2019-07-18
 **/
public class TimeUtils {
    public static Long getMilliSecondOfDate(LocalDate localDate){
        LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.of(0, 0));
        return localDateTime.toInstant(ZoneOffset.ofHours(8)).getEpochSecond();
    }
}
