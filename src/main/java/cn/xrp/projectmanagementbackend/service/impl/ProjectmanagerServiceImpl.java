package cn.xrp.projectmanagementbackend.service.impl;

import cn.xrp.projectmanagementbackend.common.BaseResponse;
import cn.xrp.projectmanagementbackend.common.ErrorCode;
import cn.xrp.projectmanagementbackend.common.ResultUtil;
import cn.xrp.projectmanagementbackend.exception.BusinessException;
import cn.xrp.projectmanagementbackend.mapper.ManagerUnitMapper;
import cn.xrp.projectmanagementbackend.mapper.UnitMapper;
import cn.xrp.projectmanagementbackend.model.ManagerUnit;
import cn.xrp.projectmanagementbackend.model.ProjectManagerDTO;
import cn.xrp.projectmanagementbackend.model.Unit;
import cn.xrp.projectmanagementbackend.model.request.ManagerRequest;
import cn.xrp.projectmanagementbackend.model.request.ManagerSearchRequest;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.xrp.projectmanagementbackend.model.Projectmanager;
import cn.xrp.projectmanagementbackend.service.ProjectmanagerService;
import cn.xrp.projectmanagementbackend.mapper.ProjectmanagerMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author x
* @description 针对表【projectmanager(负责人信息表)】的数据库操作Service实现
* @createDate 2024-06-26 17:08:10
*/
@Service
public class ProjectmanagerServiceImpl extends ServiceImpl<ProjectmanagerMapper, Projectmanager>
    implements ProjectmanagerService{
    @Resource
    private ManagerUnitMapper managerUnitMapper;
    @Resource
    private UnitMapper unitMapper;
    @Resource
    private ProjectmanagerMapper projectmanagerMapper;

    @Override
    public List<ProjectManagerDTO> getManagerList() {
        List<Projectmanager> projectmanagerList = list();

        List<ProjectManagerDTO> result = projectmanagerList.stream().map(projectmanager -> {
            // 查到该负责人所属单位的ID集合
            List<ManagerUnit> managerUnits = managerUnitMapper.selectList(new LambdaQueryWrapper<ManagerUnit>().eq(ManagerUnit::getManager_id, projectmanager.getManagerID()));
            List<Integer> unitIDList = managerUnits.stream().map(managerUnit -> managerUnit.getUnit_id()).collect(Collectors.toList());


            // 将单位ID集合转为单位名称集合
            List<HashMap<Integer, String>> unitNameList = unitIDList.stream().map(id -> {
                String unitName = unitMapper.selectById(id).getUnitName();
                HashMap<Integer, String> map = new HashMap<>();
                map.put(id, unitName);
                return map;
            }).collect(Collectors.toList());

            return new ProjectManagerDTO(projectmanager, unitNameList);
        }).collect(Collectors.toList());
        return result;
    }

    @Override
    @Transactional
    public BaseResponse addManager(Projectmanager manager, List<Integer> checkdUnitIDList) {

        try {
            String managerName = manager.getManagerName();
            LambdaQueryWrapper<Projectmanager> wrapper = new LambdaQueryWrapper<Projectmanager>().eq(Projectmanager::getManagerName, managerName);
            Projectmanager tmpManager = this.getOne(wrapper);
            if(tmpManager != null && tmpManager.getManagerName().equals(manager.getManagerName())){
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"该负责人已存在");
            }
            //往manager表中插入数据
            save(manager);
            //已插入的数据
            Projectmanager newProjectmanager = this.getOne(wrapper);
            //往关系表插入数据
            checkdUnitIDList.forEach(unitID -> {
                ManagerUnit managerUnit = new ManagerUnit(newProjectmanager.getManagerID(),unitID);
                managerUnitMapper.insert(managerUnit);
            });
        }catch (Exception e){
            return ResultUtil.error(ErrorCode.SYSTEM_ERROR,"插入失败");
        }
        return ResultUtil.success("插入成功");
    }

    @Override
    @Transactional
    public BaseResponse<Boolean> deleteManager(long managerId) {
        try {
            //1.先删除负责人-单位关系表中的数据
            LambdaQueryWrapper<ManagerUnit> wrapper = new LambdaQueryWrapper<ManagerUnit>().eq(ManagerUnit::getManager_id, managerId);
            managerUnitMapper.delete(wrapper);
            //2.再删除projectManager表的数据
            projectmanagerMapper.deleteById(managerId);

        }catch (Exception e){
            return ResultUtil.error(ErrorCode.SYSTEM_ERROR,"删除失败");
        }
        return ResultUtil.success(true);


    }

    @Override
    public BaseResponse<Boolean> editManager(ManagerRequest managerRequest) {
        Projectmanager manager = managerRequest.getManager();
        //已选所属单位ID列表,如1,2,3
        List<Integer> checkdUnitIDList = managerRequest.getCheckdUnitIDList();
        try {
            //修改负责人信息表
            projectmanagerMapper.updateById(manager);
            // 2. 修改负责人-单位表
            // 删除旧关系
            LambdaQueryWrapper<ManagerUnit> wrapper = new LambdaQueryWrapper<ManagerUnit>().eq(ManagerUnit::getManager_id, manager.getManagerID());
            managerUnitMapper.delete(wrapper);

            // 插入新关系
            checkdUnitIDList.forEach(unitID -> {
                ManagerUnit managerUnit = new ManagerUnit(manager.getManagerID(), unitID);
                managerUnitMapper.insert(managerUnit);
            });
        }catch (Exception e) {
            return ResultUtil.error(ErrorCode.SYSTEM_ERROR, "更新失败");
        }

        return ResultUtil.success(true);

    }

    @Override
    public List<ProjectManagerDTO> searchManager(ManagerSearchRequest managerSearchRequest) {
        String unitName = managerSearchRequest.getUnitName();
        String managerName = managerSearchRequest.getManagerName();
        Integer managerID = managerSearchRequest.getManagerID();
        Integer age = managerSearchRequest.getAge();

        //1.先查manager
        LambdaQueryWrapper<Projectmanager> wrapper = new LambdaQueryWrapper<>();
        if(null != managerName && !managerName.equals("")){
            wrapper.like(Projectmanager::getManagerName, managerName);
        }
        if(null != managerID && !managerID.equals("")){
            wrapper.eq(Projectmanager::getManagerID, managerID);
        }
        if( null != age &&!age.equals("")){
            wrapper.eq(Projectmanager::getAge, age);
        }
        List<Projectmanager> tmpList = projectmanagerMapper.selectList(wrapper);
        //2.再查unitName对应的unitID
        if(null != unitName &&!unitName.equals("")){
            LambdaQueryWrapper<Unit> wrapper1 = new LambdaQueryWrapper<Unit>();
            wrapper1.like(Unit::getUnitName,unitName);
            List<Unit> unitList = unitMapper.selectList(wrapper1);
            List<Object> unitIDList = unitList.stream().map(unit ->
                new Integer(unit.getUnitID())
            ).collect(Collectors.toList());

            //3.查出该unitList所拥有的负责人IDList
            if(unitIDList.size()>0){ //负责人存在
                LambdaQueryWrapper<ManagerUnit> wrapper2 = new LambdaQueryWrapper<>();
                wrapper2.in(ManagerUnit::getUnit_id,unitIDList);
                List<ManagerUnit> managerUnitList = managerUnitMapper.selectList(wrapper2);
                //返回符合条件的ManagerUnit的manager_id
                List<Integer> managerIDList = managerUnitList.stream()
                        .map(ManagerUnit::getManager_id)
                        .collect(Collectors.toList());
                //根据此负责人IDList，判断tmpList中是否IN
                tmpList = tmpList.stream()
                        .filter(pm -> managerIDList.contains(pm.getManagerID()))
                        .collect(Collectors.toList());
            }else{
                //负责人不存在
                return null;
            }


        }
        //返回结果处理
        List<ProjectManagerDTO> result = tmpList.stream().map(projectmanager -> {
            // 查到该负责人所属单位的ID集合
            List<ManagerUnit> managerUnits = managerUnitMapper.selectList(new LambdaQueryWrapper<ManagerUnit>().eq(ManagerUnit::getManager_id, projectmanager.getManagerID()));
            List<Integer> unitIDList = managerUnits.stream().map(managerUnit -> managerUnit.getUnit_id()).collect(Collectors.toList());

            // 将单位ID集合转为单位名称集合
            List<HashMap<Integer, String>> unitNameList = unitIDList.stream().map(id -> {
                String tmpName = unitMapper.selectById(id).getUnitName();
                HashMap<Integer, String> map = new HashMap<>();
                map.put(id, tmpName);
                return map;
            }).collect(Collectors.toList());

            return new ProjectManagerDTO(projectmanager, unitNameList);
        }).collect(Collectors.toList());
        return result;
    }
}




