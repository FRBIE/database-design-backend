package cn.xrp.projectmanagementbackend.controller;


import cn.xrp.projectmanagementbackend.common.BaseResponse;
import cn.xrp.projectmanagementbackend.common.ErrorCode;
import cn.xrp.projectmanagementbackend.common.ResultUtil;
import cn.xrp.projectmanagementbackend.exception.BusinessException;
import cn.xrp.projectmanagementbackend.model.ProjectManagerDTO;
import cn.xrp.projectmanagementbackend.model.Projectmanager;
import cn.xrp.projectmanagementbackend.model.Unit;
import cn.xrp.projectmanagementbackend.model.request.ManagerRequest;
import cn.xrp.projectmanagementbackend.model.request.ManagerSearchRequest;
import cn.xrp.projectmanagementbackend.service.ManagerUnitService;
import cn.xrp.projectmanagementbackend.service.ProjectmanagerService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 项目负责人接口
 */
@RestController
@RequestMapping("/manager")
public class ProjectManagerController {
    @Resource
    private ProjectmanagerService projectmanagerService;
    @Resource
    private ManagerUnitService managerUnitService;
    @GetMapping("/list")
    public BaseResponse<List<ProjectManagerDTO>> getManagerList(){
        List<ProjectManagerDTO> resList = projectmanagerService.getManagerList();
        return ResultUtil.success(resList);
    }
    @PostMapping("/add")
    public BaseResponse<Boolean> addManager(@RequestBody ManagerRequest managerRequest){
        //前端传入负责人信息、单位IDList
        return projectmanagerService.addManager(managerRequest.getManager(), managerRequest.getCheckdUnitIDList());
    }
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteManager(@RequestBody JsonNode index){
        long managerId = index.get("id").longValue();
        if(managerId < 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        return projectmanagerService.deleteManager(managerId);
    }
    @PostMapping("/edit")
    public BaseResponse<Boolean> editManager(@RequestBody ManagerRequest managerRequest){
        return projectmanagerService.editManager(managerRequest);
    }
    @PostMapping("/search")
    public BaseResponse<List<ProjectManagerDTO>> searchManager(@RequestBody ManagerSearchRequest managerSearchRequest ){
        List<ProjectManagerDTO> projectmanagers = projectmanagerService.searchManager(managerSearchRequest);
        return ResultUtil.success(projectmanagers);
    }

}
