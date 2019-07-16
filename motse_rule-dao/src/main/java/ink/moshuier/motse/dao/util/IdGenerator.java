package ink.moshuier.motse.dao.util;

public class IdGenerator {

  private static long dataCenterId = 1L;

  private static long workerId = 1L;

  private static volatile SnowflakeIdWorker snowflakeIdWorker;

  /**
   * 获得一个唯一的ID
   *
   * @return
   */
  public static Long getId() {
    if (null == snowflakeIdWorker) {
      synchronized (IdGenerator.class) {
        if (null == snowflakeIdWorker) {
          snowflakeIdWorker = new SnowflakeIdWorker(dataCenterId, workerId);
        }
      }
    }
    return snowflakeIdWorker.nextId();
  }
}
