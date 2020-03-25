package ink.moshuier.motse.api;

import ink.moshuier.motse.annotation.ControllerDefiner;
import ink.moshuier.motse.api.bean.response.ResponseBean;
import ink.moshuier.motse.config.OSSClient;
import ink.moshuier.motse.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

import static ink.moshuier.motse.util.ConstantUtils.IMG_BED_PREFIX;


/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 */
@ControllerDefiner(value = "静态文件上传下载", path = "/static")
public class FileController extends RestfulController {
    @Autowired
    CardService cardService;
    @Autowired
    OSSClient ossClient;

    @PostMapping("/images")
    public ResponseBean<Map<String, Object>> uploadImage(MultipartFile img) throws IOException {
        String imgName = ossClient.upload(img.getBytes());
        return ResponseBean.builder().populate("name", imgName).populate("url", IMG_BED_PREFIX + imgName);
    }

    @DeleteMapping("/images/{imgName}")
    public ResponseBean<Map<String, Object>> deleteImg(@PathVariable("imgName") String imgName) throws IOException {
        ossClient.deleteImage(imgName);
        return ResponseBean.success();
    }

}
