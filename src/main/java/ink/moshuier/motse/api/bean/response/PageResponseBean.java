package ink.moshuier.motse.api.bean.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRawValue;
import ink.moshuier.motse.bean.BeanPage;
import ink.moshuier.motse.util.ProjectionUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Sarah Xu
 * @date : 2019-05-18
 **/
@Data
@NoArgsConstructor
public class PageResponseBean<DTO, BEAN> {
    Integer errCode = 0;
    @JsonRawValue
    String errMsg = "\"OK\"";
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected Integer pageSize;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected Integer pageNum;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected Integer totalPage;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected Long totalElements;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected List<DTO> data = new ArrayList<>();


    public PageResponseBean(BeanPage<BEAN, ?> page) {
        this.pageSize = page.getPageSize();
        this.pageNum = page.getPageNum() + 1;
        this.totalPage = page.getTotalPage();
        this.totalElements = page.getTotalElements();
        Class<BEAN> beanClass = null;
        Class<DTO> dtoClass = null;

        Type superclass = getClass().getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing brokerType parameter.");
        } else {
            ParameterizedType parameterized = (ParameterizedType)superclass;
            dtoClass = (Class<DTO>) parameterized.getActualTypeArguments()[0];
        }
        for (BEAN e : page.getContent()) {
            try {
                DTO dto = ProjectionUtils.mapTo(dtoClass, e);
                data.add(dto);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

    }

}
