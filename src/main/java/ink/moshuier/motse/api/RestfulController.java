package ink.moshuier.motse.api;

import ink.moshuier.motse.api.bean.dto.BaseDTO;
import ink.moshuier.motse.api.bean.response.PageResponseBean;
import ink.moshuier.motse.api.bean.response.ResponseBean;
import ink.moshuier.motse.bean.BaseBean;
import ink.moshuier.motse.bean.BeanPage;
import ink.moshuier.motse.bean.SearchCondition;
import ink.moshuier.motse.service.BaseDao;
import ink.moshuier.motse.util.EnumUtils;
import ink.moshuier.motse.util.ProjectionUtils;
import lombok.extern.java.Log;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

import static ink.moshuier.motse.bean.SearchCondition.DEFAULT_IGNORE_IF_EMPTY;
import static ink.moshuier.motse.bean.SearchCondition.DEFAULT_IGNORE_IF_NULL;

@Log
public class RestfulController<DTO extends BaseDTO, BEAN extends BaseBean, SERVICE extends BaseDao> implements ApplicationContextAware {
    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        RestfulController.applicationContext = applicationContext;
    }

    SERVICE service;
    Class<BEAN> beanClass;
    Class<DTO> dtoClass;
    Class<SERVICE> serviceClass;

    {
//        Class<?> cls = this.getClass().getSuperclass();
        Type superclass = getClass().getGenericSuperclass();
        if (!(superclass instanceof Class)) {
            ParameterizedType parameterized = (ParameterizedType) superclass;
            dtoClass = (Class<DTO>) parameterized.getActualTypeArguments()[0];
            beanClass = (Class<BEAN>) parameterized.getActualTypeArguments()[1];
            serviceClass = (Class<SERVICE>) parameterized.getActualTypeArguments()[2];
        }
    }

    void setService() {
        if (service == null) {
            try {
                service = ((ApplicationContext) this.getClass().getSuperclass().getDeclaredField("applicationContext").get(this)).getBean(serviceClass);
            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }

    @PostMapping("")
    ResponseBean<Map<String, Object>> create(@RequestBody DTO request) {
        setService();
        BEAN bean = ProjectionUtils.mapTo(beanClass, request);
        Long id = service.create(bean);
        return ResponseBean.id(id);
    }

    @PutMapping("/{id}")
    ResponseBean<Void> update(@RequestBody DTO request, @PathVariable("id") Long id) {

        setService();
        BEAN bean = ProjectionUtils.mapTo(beanClass, request);
        bean.setId(id);
        service.merge(bean);
        return new ResponseBean<>();
    }

    @DeleteMapping("/{id}")
    ResponseBean<Void> delete(@PathVariable("id") Long id) {
        setService();
        service.delete(id);
        return new ResponseBean<>();
    }

    @GetMapping("/{id}")
    ResponseBean<DTO> get(@PathVariable("id") Long id) {
        setService();
        DTO dto = ProjectionUtils.mapFromBeanToResponse(dtoClass, service.getBeanById(id));
        return new ResponseBean<>(dto);
    }

    //RequestParam 在父类无法接收
    @GetMapping("/")
    PageResponseBean<DTO, BEAN> search(@RequestBody Map<String, Object> filters) {
        setService();
        List<String> desc = (List<String>) filters.get("desc");
        List<String> asc = (List<String>) filters.get("asc");
        Integer pageSize = (Integer) filters.get("pageSize");
        Integer pageNum = (Integer) filters.get("pageNum");
        filters.remove("desc");
        filters.remove("asc");
        PageRequest pageRequest = PageRequest.of(Optional.ofNullable(pageNum).orElse(0), Optional.ofNullable(pageSize).orElse(1000));
        final List<SearchCondition> searchConditions = new ArrayList<>();
        filters.keySet().stream().forEach((key) -> {
            Map<String, Object> attributes = (Map<String, Object>) filters.get(key);
            Boolean ignoreIfEmpty = (Boolean) attributes.get("ignoreIfEmpty");
            Boolean ignoreIfNull = (Boolean) attributes.get("ignoreIfNull");
            Object value = attributes.get("value");
            String enumType = (String) attributes.get("enum");
            String operator = (String) attributes.get("operator");
            if (Objects.nonNull(enumType)) {
                value = EnumUtils.locateEnum(enumType, (String) value);
            }
            // //means operator
            SearchCondition.CriteriaOperator operatorEnum = SearchCondition.CriteriaOperator.getEnumFromName(operator);
            SearchCondition searchCondition = new SearchCondition(key, value, Optional.ofNullable(ignoreIfEmpty).orElse(DEFAULT_IGNORE_IF_EMPTY), Optional.ofNullable(ignoreIfNull).orElse(DEFAULT_IGNORE_IF_NULL), operatorEnum);
            searchConditions.add(searchCondition);
        });

        BeanPage<BEAN, ?> beanPage = service.searchActive(searchConditions, pageRequest, desc, asc);
        PageResponseBean<DTO, BEAN> pageResponseBean = new PageResponseBean();
        pageResponseBean.setPageSize(beanPage.getPageSize());
        pageResponseBean.setPageNum(beanPage.getPageNum() + 1);
        pageResponseBean.setTotalPage(beanPage.getTotalPage());
        pageResponseBean.setTotalElements(beanPage.getTotalElements());
        List<DTO> data = pageResponseBean.getData();
        Class<DTO> dtoClass = null;

        Type superclass = getClass().getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing brokerType parameter.");
        } else {
            ParameterizedType parameterized = (ParameterizedType) superclass;
            dtoClass = (Class<DTO>) parameterized.getActualTypeArguments()[0];
        }
        for (BEAN e : beanPage.getContent()) {
            try {
                DTO dto = ProjectionUtils.mapTo(dtoClass, e);
                data.add(dto);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return pageResponseBean;
    }
}
