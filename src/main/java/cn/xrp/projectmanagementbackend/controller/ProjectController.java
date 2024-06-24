package cn.xrp.projectmanagementbackend.controller;


import cn.xrp.projectmanagementbackend.common.BaseResponse;

import cn.xrp.projectmanagementbackend.common.ErrorCode;
import cn.xrp.projectmanagementbackend.common.ResultUtil;
import cn.xrp.projectmanagementbackend.exception.BusinessException;
import cn.xrp.projectmanagementbackend.model.Project;

import cn.xrp.projectmanagementbackend.service.ProjectService;
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
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUser(@RequestBody JsonNode index){
        long id = index.get("id").longValue();
        if(id < 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean b =  projectService.removeById(id);
        return ResultUtil.success(b);
    }

}
