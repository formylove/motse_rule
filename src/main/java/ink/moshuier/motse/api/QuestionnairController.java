package ink.moshuier.motse.api;

import ink.moshuier.motse.api.bean.dto.QuestionnairDTO;
import ink.moshuier.motse.api.bean.response.PageResponseBean;
import ink.moshuier.motse.api.bean.response.ResponseBean;
import ink.moshuier.motse.bean.BeanPage;
import ink.moshuier.motse.bean.QuestionnairBean;
import ink.moshuier.motse.bean.SearchCondition;
import ink.moshuier.motse.bean.TaskBean;
import ink.moshuier.motse.entity.QuestionnairEntity;
import ink.moshuier.motse.service.QuestionnairService;
import ink.moshuier.motse.service.TaskService;
import ink.moshuier.motse.util.ProjectionUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 */
@Api(value = "问卷")
@RestController
@RequestMapping("/questionnairs")
public class QuestionnairController extends RestfulController implements BaseApi<QuestionnairDTO, QuestionnairBean> {
    @Autowired
    QuestionnairService questionnairService;
    @Autowired
    TaskService taskService;


    @ApiOperation(value = "新建问卷")
    @PostMapping(value = "")
    @Override
    public ResponseBean<Map<String, Object>> create(@RequestBody QuestionnairDTO questionnairDTO) {
        QuestionnairBean questionnairBean = ProjectionUtils.mapTo(QuestionnairBean.class, questionnairDTO);
        TaskBean taskBean = taskService.getBeanById(questionnairDTO.getTaskId());
        questionnairBean.setTaskBean(taskBean);
        Long tid = questionnairService.create(questionnairBean);
        return ResponseBean.success("id", tid);
    }

    @ApiOperation(value = "修改问卷")
    @PutMapping(value = "/{id}")
    @Override
    public ResponseBean<Void> update(@RequestBody QuestionnairDTO questionnairDTO, @PathVariable("id") Long id) {
        QuestionnairBean questionnairBean = ProjectionUtils.mapTo(QuestionnairBean.class, questionnairDTO);
        questionnairService.merge(questionnairBean);
        return new ResponseBean<>();
    }

    @ApiOperation(value = "删除问卷")
    @DeleteMapping(value = "/{id}")
    @Override
    public ResponseBean<Void> delete(@PathVariable("id") Long tid) {
        questionnairService.delete(tid);
        return new ResponseBean<>();
    }


    @ApiOperation(value = "查询问卷")
    @GetMapping(value = "/{id}")
    @Override
    public ResponseBean get(@PathVariable("id") Long tid) {
        QuestionnairBean questionnairBean = questionnairService.getBeanById(tid);
        QuestionnairDTO questionnairDTO = ProjectionUtils.mapFromBeanToResponse(QuestionnairDTO.class, questionnairBean);
        return new ResponseBean<>(questionnairDTO);
    }

    @ApiOperation(value = "查询所有问卷")
    @PostMapping(value = "/")
    @Override
    public PageResponseBean search(@RequestBody Map<String, Object> filters, @ApiIgnore @RequestParam(required = false) List<SearchCondition> searchConditions, @RequestParam(value = "pageNum", required = false) Integer pageNum, @RequestParam(value = "pageSize", required = false) Integer pageSize, @ApiIgnore Pageable page) {
        BeanPage<QuestionnairBean, QuestionnairEntity> questionnairs = questionnairService.searchActive(searchConditions, page);
        PageResponseBean<QuestionnairDTO, QuestionnairBean> pageResponseBean = new PageResponseBean<QuestionnairDTO, QuestionnairBean>(questionnairs) {
        };
        return pageResponseBean;
    }

}
