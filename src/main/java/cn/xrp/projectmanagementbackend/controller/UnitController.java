package cn.xrp.projectmanagementbackend.controller;


import cn.xrp.projectmanagementbackend.common.BaseResponse;
import cn.xrp.projectmanagementbackend.common.ErrorCode;
import cn.xrp.projectmanagementbackend.common.ResultUtil;
import cn.xrp.projectmanagementbackend.exception.BusinessException;
import cn.xrp.projectmanagementbackend.model.Unit;
import cn.xrp.projectmanagementbackend.service.UnitService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 单位接口
 */
@RestController
@RequestMapping("/unit")
public class UnitController {
    @Resource
    private UnitService unitService;

    @GetMapping("/list")
    public BaseResponse<List<Unit>> getProjectList(){
        return ResultUtil.success(unitService.list());
    }
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUser(@RequestBody JsonNode index){
        long id = index.get("id").longValue();
        if(id < 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean b =  unitService.removeById(id);
        return ResultUtil.success(b);
    }

}
