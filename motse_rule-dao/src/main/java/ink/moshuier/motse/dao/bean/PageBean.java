package ink.moshuier.motse.dao.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.lang.reflect.Constructor;
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
public class PageBean<BEAN, ENTITY>{
    private Integer pageSize;
    private Integer pageNum;
    private Integer totalPage;
    List<BEAN> content = new ArrayList<>();

  public PageBean(Page<ENTITY> page) {
            this.pageSize =page.getSize();
            this.pageNum=page.getNumber();
            this.totalPage=page.getTotalPages();
    Class<BEAN> beanClass = null;
    Class<ENTITY> entityClass = null;

    Type superclass = getClass().getGenericSuperclass();
    if (superclass instanceof Class) {
      throw new RuntimeException("Missing brokerType parameter.");
    } else {
      ParameterizedType parameterized = (ParameterizedType) superclass;
      beanClass = (Class<BEAN>) parameterized.getActualTypeArguments()[0];
      entityClass = (Class<ENTITY>) parameterized.getActualTypeArguments()[1];
    }

    Constructor<BEAN> constructor = null;
    try {
      constructor = beanClass.getConstructor(entityClass);
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    }
    if(page.getContent()!=null){
    for (ENTITY e : page.getContent()) {
      try {
        content.add(constructor.newInstance(e));
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    }
    }
}


}
