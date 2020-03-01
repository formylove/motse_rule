package ink.moshuier.motse.util;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

public class IdGenerator implements IdentifierGenerator {

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

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return getId();
    }
}
