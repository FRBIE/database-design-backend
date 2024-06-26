package cn.xrp.projectmanagementbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.xrp.projectmanagementbackend.model.ManagerUnit;
import cn.xrp.projectmanagementbackend.service.ManagerUnitService;
import cn.xrp.projectmanagementbackend.mapper.ManagerUnitMapper;
import org.springframework.stereotype.Service;

/**
* @author x
* @description 针对表【manager_unit(负责人-单位关系表)】的数据库操作Service实现
* @createDate 2024-06-26 16:11:15
*/
@Service
public class ManagerUnitServiceImpl extends ServiceImpl<ManagerUnitMapper, ManagerUnit>
    implements ManagerUnitService{

}




