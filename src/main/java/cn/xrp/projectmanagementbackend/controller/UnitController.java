package cn.xrp.projectmanagementbackend.controller;


import cn.xrp.projectmanagementbackend.common.BaseResponse;
import cn.xrp.projectmanagementbackend.common.ErrorCode;
import cn.xrp.projectmanagementbackend.common.ResultUtil;
import cn.xrp.projectmanagementbackend.exception.BusinessException;
import cn.xrp.projectmanagementbackend.model.Unit;
import cn.xrp.projectmanagementbackend.service.UnitService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
    public BaseResponse<List<Unit>> getUnitList(){
        return ResultUtil.success(unitService.list());
    }
    @PostMapping("/add")
    public BaseResponse<Boolean> addUnit(@RequestBody Unit unit){

        boolean res = unitService.addUnit(unit);
        return ResultUtil.success(res);
    }
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUnit(@RequestBody JsonNode index){
        long unitId = index.get("id").longValue();
        if(unitId < 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean b =  unitService.deleteUnit(unitId);
        return ResultUtil.success(b);
    }
    @PostMapping("/edit")
    public BaseResponse<Boolean> editUnit(@RequestBody Unit unit){
        int unitID = unit.getUnitID();
        if(unitID < 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"单位ID不存在");
        }
        boolean res = unitService.updateById(unit);
        return ResultUtil.success(res);
    }


    @PostMapping("/search")
    public BaseResponse<List<Unit>> searchProject(@RequestBody Unit unit){
        LambdaQueryWrapper<Unit> wrapper = new LambdaQueryWrapper<>();
        if(unit.getUnitID() != null && unit.getUnitID()> 0){
            wrapper.eq(Unit::getUnitID,unit.getUnitID());
        }
        if(unit.getUnitName() != null && !unit.getUnitName().equals("")){
            wrapper.like(Unit::getUnitName,unit.getUnitName());
        }
        if(unit.getAddress() != null && !unit.getAddress().equals("")){
            wrapper.like(Unit::getAddress,unit.getAddress());
        }
        List<Unit> unitList = unitService.list(wrapper);
        return ResultUtil.success(unitList);
    }

}
