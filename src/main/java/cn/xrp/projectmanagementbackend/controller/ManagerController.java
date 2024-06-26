package cn.xrp.projectmanagementbackend.controller;


import cn.xrp.projectmanagementbackend.common.BaseResponse;
import cn.xrp.projectmanagementbackend.common.ErrorCode;
import cn.xrp.projectmanagementbackend.common.ResultUtil;
import cn.xrp.projectmanagementbackend.exception.BusinessException;
import cn.xrp.projectmanagementbackend.model.Projectmanager;
import cn.xrp.projectmanagementbackend.service.ProjectmanagerService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 负责人接口
 */
@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Resource
    private ProjectmanagerService managerService;

    @GetMapping("/list")
    public BaseResponse<List<Projectmanager>> getManagerList(){
        return ResultUtil.success(managerService.list());
    }
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteManager(@RequestBody JsonNode index){
        long id = index.get("id").longValue();
        if(id < 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean b =  managerService.removeById(id);
        return ResultUtil.success(b);
    }

}
