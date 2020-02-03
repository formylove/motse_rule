package ink.moshuier.motse.service.config;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

import static ink.moshuier.motse.dao.util.ConstantUtils.IMAGES_FOLDER;
import static ink.moshuier.motse.dao.util.ConstantUtils.JPG;

/**
 * @author : Sarah Xu
 * @date : 2020-01-31
 **/
@ConfigurationProperties(prefix = "oss")
@Component
//ConfigurationProperties 必须配合setter和getter使用
@Data
public class OSSClient {
    private String accessKey;
    private String secret;
    private String bucket;

    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    public String getUpToken() {
        //密钥配置
        Auth auth = Auth.create(accessKey, secret);
        return auth.uploadToken(bucket);
    }


    public String upload(byte[] bytes) throws IOException {
        //第二种方式: 自动识别要上传的空间(bucket)的存储区域是华东、华北、华南。
        Zone z = Zone.autoZone();
        Configuration c = new Configuration(z);
        //创建上传对象
        UploadManager uploadManager = new UploadManager(c);
        String imgName = UUID.randomUUID().toString().replace("-", "") + JPG;
        //上传到七牛后保存的文件名
        String key = IMAGES_FOLDER + imgName;
        try {
            //调用put方法上传
            Response res = uploadManager.put(bytes, key, getUpToken());
            //打印返回的信息
            System.out.println(res.bodyString());
        } catch (QiniuException e) {
            Response r = e.response;
            //响应的文本信息
            System.out.println(r.bodyString());
        }
        return imgName;
    }

    public void deleteImage(String fileName) {

        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        String key = IMAGES_FOLDER + fileName;
        Auth auth = Auth.create(accessKey, secret);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }


    }
}
