package ink.moshuier.motse.bean;

import ink.moshuier.motse.util.ProjectionUtils;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Page;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@SuperBuilder
public class BeanPage<BEAN, ENTITY> {
    private Integer pageSize;
    private Integer pageNum;
    private Integer totalPage;
    private Long totalElements;
    List<BEAN> content = new ArrayList<>();

    public BeanPage(Page<ENTITY> page, Class<BEAN> beanClass, Class<ENTITY> entityClass) {
        this.pageSize = page.getSize();
        this.pageNum = page.getNumber();
        this.totalPage = page.getTotalPages();
        this.totalElements = page.getTotalElements();

        if (page.getContent() != null) {
            for (ENTITY e : page.getContent()) {
                try {
                    BEAN bean = ProjectionUtils.mapTo(beanClass, e);
                    content.add(bean);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }


    }

    public BeanPage(Page<ENTITY> page) {
        this.pageSize = page.getSize();
        this.pageNum = page.getNumber();
        this.totalPage = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        Class<BEAN> beanClass;

        Type superclass = getClass().getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing brokerType parameter.");
        } else {
            ParameterizedType parameterized = (ParameterizedType) superclass;
            beanClass = (Class<BEAN>) parameterized.getActualTypeArguments()[0];
        }

        if (page.getContent() != null) {
            for (ENTITY e : page.getContent()) {
                try {
                    BEAN bean = ProjectionUtils.mapTo(beanClass, e);
                    content.add(bean);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
    }


}
