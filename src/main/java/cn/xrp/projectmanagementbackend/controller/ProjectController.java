package cn.xrp.projectmanagementbackend.controller;


import cn.xrp.projectmanagementbackend.common.BaseResponse;

import cn.xrp.projectmanagementbackend.common.ErrorCode;
import cn.xrp.projectmanagementbackend.common.ResultUtil;
import cn.xrp.projectmanagementbackend.exception.BusinessException;
import cn.xrp.projectmanagementbackend.mapper.ProjectMapper;
import cn.xrp.projectmanagementbackend.model.Project;

import cn.xrp.projectmanagementbackend.model.Projectmanager;
import cn.xrp.projectmanagementbackend.model.response.ProjectDTO;
import cn.xrp.projectmanagementbackend.service.ProjectService;
import cn.xrp.projectmanagementbackend.service.ProjectmanagerService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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
    @Resource
    private ProjectmanagerService projectmanagerService;
    @GetMapping("/list")
    public BaseResponse<List<ProjectDTO>> getProjectList(){
        return ResultUtil.success(projectService.getProjectList());
    }
    @PostMapping("/add")
    public BaseResponse<Integer> addProject(@RequestBody ProjectDTO projectDTO){

        String managerName = projectDTO.managerName;
        int res = projectService.addProject(projectDTO,managerName);
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
    public BaseResponse<Integer> deleteProject(@RequestBody ProjectDTO projectDTO){
        int projectID = projectDTO.getProjectID();
        if(projectID < 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Integer res = projectService.updateProject(projectDTO);
        return ResultUtil.success(res);
    }
    @PostMapping("/search")
    public BaseResponse<List<Project>> searchProject(@RequestBody ProjectDTO projectDTO){
        LambdaQueryWrapper<Project> wrapper = new LambdaQueryWrapper<>();
        if(projectDTO.getProjectID() != null && !projectDTO.getProjectID().equals("")){
            wrapper.eq(Project::getProjectID,projectDTO.getProjectID());
        }
        if(projectDTO.getProjectName() != null && !projectDTO.getProjectName().equals("")){
            wrapper.like(Project::getProjectName,projectDTO.getProjectName());
        }
        if(projectDTO.getStartDate() != null&& !projectDTO.getStartDate().equals("")){
            wrapper.eq(Project::getStartDate,projectDTO.getStartDate());
        }
        if(projectDTO.getManagerName() != null && !projectDTO.getManagerName().equals("")){ //有姓名查询条件
            //1.根据负责人姓名获取负责人ID
            LambdaQueryWrapper<Projectmanager> tmpWrapper = new LambdaQueryWrapper<>();
            tmpWrapper.eq(Projectmanager::getManagerName,projectDTO.getManagerName());
            Projectmanager manager = projectmanagerService.getOne(tmpWrapper);
            if(manager == null){
                //1.1不存在该负责人
                //第一种解决方法 ->直接提示不存在
               throw new BusinessException(ErrorCode.PARAMS_ERROR,"该负责人不存在");
                //第二种解决方法 ->把查询条件设为null
               // wrapper.eq(Project::getManagerID,null);
            }else{//1.2 存在该负责人
                wrapper.eq(Project::getManagerID,manager.getManagerID());
            }

        }
        if(projectDTO.getProjectLevel() != null&&!projectDTO.getProjectLevel().equals("")){
            wrapper.eq(Project::getProjectLevel,projectDTO.getProjectLevel());
        }
        if(projectDTO.getBudget() != null&& !projectDTO.getBudget().equals("")){
            wrapper.eq(Project::getBudget,projectDTO.getBudget());
        }

        List<Project> projectList = projectService.list(wrapper);
        return ResultUtil.success(projectList);
    }
}
