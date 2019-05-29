package com.thizgroup.promotion.util;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.text.MessageFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 **/
public class ConstantUtils {
    public static final long SMS_VALID_TIME = 300000L; // 5分钟
    public static final String TEST_VERIFY_CODE = "PALATE"; // 5分钟
    public static final String CLAIM_KEY_MOBILE = "mobile"; // 5分钟
    public static final String CLAIM_KEY_INVITE_CODE = "invitecode"; // 5分钟
    public static final String SECRET_ODEC = "E7Q9SRSCVMOSUSV0MGYH0WAFZQJETH77R6F6MCJ631E29O0S";
    static private String FILENAME="邀请码绑定_{0}.xlsx";


    public static SecretKey getSecretKey(String code) {
        byte[] secretKeyBytes = Decoders.BASE64.decode(code + SECRET_ODEC);
        SecretKey secretKey = new SecretKeySpec(secretKeyBytes, SignatureAlgorithm.HS256.getJcaName());
        return secretKey;
    }

    public static String generateExcelFileName(){
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
        String currentDate = localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        return MessageFormat.format(FILENAME, currentDate);
    }


}
