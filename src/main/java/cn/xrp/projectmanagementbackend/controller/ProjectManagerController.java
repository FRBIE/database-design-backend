package cn.xrp.projectmanagementbackend.controller;


import cn.xrp.projectmanagementbackend.common.BaseResponse;
import cn.xrp.projectmanagementbackend.common.ErrorCode;
import cn.xrp.projectmanagementbackend.common.ResultUtil;
import cn.xrp.projectmanagementbackend.exception.BusinessException;
import cn.xrp.projectmanagementbackend.model.ProjectManagerDTO;
import cn.xrp.projectmanagementbackend.model.Unit;
import cn.xrp.projectmanagementbackend.model.request.ManagerRequest;
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
//    @PostMapping("/search")
//    public BaseResponse<List<Unit>> searchProject(@RequestBody Unit unit){
//        //考虑中，到底要支持哪些查询
//        LambdaQueryWrapper<Unit> wrapper = new LambdaQueryWrapper<>();
//        if(unit.getUnitID() != null && unit.getUnitID()> 0){
//            wrapper.eq(Unit::getUnitID,unit.getUnitID());
//        }
//        if(unit.getUnitName() != null && !unit.getUnitName().equals("")){
//            wrapper.like(Unit::getUnitName,unit.getUnitName());
//        }
//        if(unit.getAddress() != null && !unit.getAddress().equals("")){
//            wrapper.like(Unit::getAddress,unit.getAddress());
//        }
//        List<Unit> unitList = unitService.list(wrapper);
//        return ResultUtil.success(unitList);
//    }

}
