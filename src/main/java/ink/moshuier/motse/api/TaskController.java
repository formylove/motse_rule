package ink.moshuier.motse.api;

import ink.moshuier.motse.api.bean.dto.TaskDTO;
import ink.moshuier.motse.api.bean.response.PageResponseBean;
import ink.moshuier.motse.api.bean.response.ResponseBean;
import ink.moshuier.motse.bean.BeanPage;
import ink.moshuier.motse.bean.SearchCondition;
import ink.moshuier.motse.bean.TaskBean;
import ink.moshuier.motse.entity.TaskEntity;
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
@Api(value = "任务/长期任务")
@RestController
@RequestMapping("/tasks")
public class TaskController extends RestfulController implements BaseApi<TaskDTO, TaskBean> {
    @Autowired
    TaskService taskService;

    @ApiOperation(value = "添加任务")
    @PostMapping(value = "")
    @Override
    public ResponseBean<Map<String, Object>> create(@RequestBody TaskDTO taskDTO) {
        TaskBean taskBean = ProjectionUtils.mapTo(TaskBean.class, taskDTO);
        Long tid = taskService.create(taskBean);
        return ResponseBean.success("id", tid);
    }

    @ApiOperation(value = "修改任务")
    @PutMapping(value = "/{id}")
    @Override
    public ResponseBean<Void> update(@RequestBody TaskDTO taskDTO, @PathVariable("id") Long id) {
        TaskBean taskBean = ProjectionUtils.mapTo(TaskBean.class, taskDTO);
        taskService.merge(taskBean);
        return new ResponseBean<>();
    }

    @ApiOperation(value = "删除任务")
    @DeleteMapping(value = "/{id}")
    @Override
    public ResponseBean<Void> delete(@PathVariable("id") Long tid) {
        taskService.delete(tid);
        return new ResponseBean<>();
    }


    @ApiOperation(value = "查询任务")
    @GetMapping(value = "/{id}")
    @Override
    public ResponseBean get(@PathVariable("id") Long tid) {
        TaskBean taskBean = taskService.getBeanById(tid);
        TaskDTO taskDTO = ProjectionUtils.mapFromBeanToResponse(TaskDTO.class, taskBean);
        return new ResponseBean<>(taskDTO);
    }

    @ApiOperation(value = "查询所有任务")
    @PostMapping(value = "/")
    @Override
    public PageResponseBean search(@RequestBody Map<String, Object> filters, @ApiIgnore @RequestParam(required = false) List<SearchCondition> searchConditions, @RequestParam(value = "pageNum", required = false) Integer pageNum, @RequestParam(value = "pageSize", required = false) Integer pageSize, @ApiIgnore Pageable page) {
        BeanPage<TaskBean, TaskEntity> tasks = taskService.searchActive(searchConditions, page);
        PageResponseBean<TaskDTO, TaskBean> pageResponseBean = new PageResponseBean<TaskDTO, TaskBean>(tasks) {
        };
        return pageResponseBean;
    }


}
