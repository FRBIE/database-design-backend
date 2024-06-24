package cn.xrp.projectmanagementbackend.controller;


import cn.xrp.projectmanagementbackend.common.BaseResponse;

import cn.xrp.projectmanagementbackend.common.ErrorCode;
import cn.xrp.projectmanagementbackend.common.ResultUtil;
import cn.xrp.projectmanagementbackend.exception.BusinessException;
import cn.xrp.projectmanagementbackend.model.Project;

import cn.xrp.projectmanagementbackend.service.ProjectService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;


import java.util.List;




/**
 * 项目接口
 */
@RestController
@RequestMapping("/project")
public class ProjectController {
    @Resource
    private ProjectService projectService;

    @GetMapping("/list")
    public BaseResponse<List<Project>> getProjectList(){
        return ResultUtil.success(projectService.getProjectList());
    }
    @PostMapping("/add")
    public BaseResponse<Boolean> addProject(@RequestBody Project project){
        //前端做参数校验，这里暂时不做
        boolean res = projectService.save(project);
        return ResultUtil.success(res);
    }
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteProject(@RequestBody JsonNode index){
        long id = index.get("id").longValue();
        if(id < 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean b =  projectService.removeById(id);
        return ResultUtil.success(b);
    }
    @PostMapping("/edit")
    public BaseResponse<Boolean> deleteProject(@RequestBody Project project){
        int projectID = project.getProjectID();
        if(projectID < 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean res = projectService.updateById(project);
        return ResultUtil.success(res);
    }
    @PostMapping("/search")
    public BaseResponse<List<Project>> searchProject(@RequestBody Project project){
        LambdaQueryWrapper<Project> wrapper = new LambdaQueryWrapper<>();
        if(project.getProjectID() != null && !project.getProjectID().equals("")){
            wrapper.eq(Project::getProjectID,project.getProjectID());
        }
        if(project.getProjectName() != null && !project.getProjectName().equals("")){
            wrapper.like(Project::getProjectName,project.getProjectName());
        }
        if(project.getStartDate() != null&& !project.getStartDate().equals("")){
            wrapper.eq(Project::getStartDate,project.getStartDate());
        }
        if(project.getManagerID() != null && !project.getManagerID().equals("")){
            wrapper.eq(Project::getManagerID,project.getManagerID());
        }
        if(project.getProjectLevel() != null&&!project.getProjectLevel().equals("")){
            wrapper.eq(Project::getProjectLevel,project.getProjectLevel());
        }
        if(project.getBudget() != null&& !project.getBudget().equals("")){
            wrapper.eq(Project::getBudget,project.getBudget());
        }

        List<Project> projectList = projectService.list(wrapper);
        return ResultUtil.success(projectList);
    }
}
