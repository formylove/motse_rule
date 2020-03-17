package ink.moshuier.motse.api;

import ink.moshuier.motse.api.bean.dto.BaseDTO;
import ink.moshuier.motse.api.bean.response.PageResponseBean;
import ink.moshuier.motse.api.bean.response.ResponseBean;
import ink.moshuier.motse.bean.BaseBean;
import ink.moshuier.motse.bean.SearchCondition;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BaseApi<DTO extends BaseDTO, BEAN extends BaseBean> {
    default ResponseBean<Map<String, Object>> create(DTO request) {
        return null;
    }

    default ResponseBean<Void> update(DTO request, Long id) {


        return null;
    }

    default ResponseBean<Void> delete(Long id) {
        return null;
    }

    default ResponseBean<DTO> get(Long id) {
        return null;
    }

    default PageResponseBean<DTO, BEAN> search(Map<String, Object> filters, List<SearchCondition> searchConditions, Integer pageNum, Integer pageSize, Pageable pageable) {
        return null;
    }
}
