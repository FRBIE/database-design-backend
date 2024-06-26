package cn.xrp.projectmanagementbackend.service.impl;


import cn.xrp.projectmanagementbackend.common.ErrorCode;
import cn.xrp.projectmanagementbackend.exception.BusinessException;
import cn.xrp.projectmanagementbackend.mapper.ManagerUnitMapper;
import cn.xrp.projectmanagementbackend.mapper.UnitMapper;
import cn.xrp.projectmanagementbackend.model.ManagerUnit;
import cn.xrp.projectmanagementbackend.model.Unit;
import cn.xrp.projectmanagementbackend.service.ManagerUnitService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.xrp.projectmanagementbackend.service.UnitService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author x
* @description 针对表【unit(单位表)】的数据库操作Service实现
* @createDate 2024-06-24 17:04:14
*/
@Service
public class UnitServiceImpl extends ServiceImpl<UnitMapper, Unit>
    implements UnitService{
    @Resource
    private ManagerUnitMapper managerUnitMapper;
    @Resource
    private UnitMapper unitMapper;
    @Override
    public boolean addUnit(Unit unit) {
        String unitName = unit.getUnitName();
        Unit tmpunit = this.getOne(new LambdaQueryWrapper<Unit>().eq(Unit::getUnitName, unitName));
        if(tmpunit != null && tmpunit.getUnitName().equals(unit.getUnitName())){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"该单位名称已存在");
        }
        return save(unit);
    }

    /**
     *  删除单位，要先删除负责人-单位关系表中的所有对应数据，再删除单位表的数据
     */
    @Override
    public boolean deleteUnit(long unitId) {
        try {
            //删除关系表中的数据
            LambdaQueryWrapper<ManagerUnit> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(ManagerUnit::getUnit_id,unitId);
            managerUnitMapper.delete(wrapper);
            //删除单位表数据
            unitMapper.deleteById(unitId);
//            List<ManagerUnit> managerUnits = managerUnitMapper.selectList(wrapper);
//
//
//            for (ManagerUnit managerUnit :
//                    managerUnits) {
//                managerUnitMapper.deleteById(managerUnit.getUnit_id());
//            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"删除失败,请重试");
        }
        //删除单位表的数据
        return removeById(unitId);
    }
}




