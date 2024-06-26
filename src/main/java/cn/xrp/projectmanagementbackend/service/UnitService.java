package cn.xrp.projectmanagementbackend.service;

import cn.xrp.projectmanagementbackend.mapper.ProjectmanagerMapper;
import cn.xrp.projectmanagementbackend.model.Projectmanager;
import cn.xrp.projectmanagementbackend.model.Unit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author x
* @description 针对表【unit(单位表)】的数据库操作Service
* @createDate 2024-06-24 17:04:14
*/
public interface UnitService extends IService<Unit> {

    boolean addUnit(Unit unit);

    boolean deleteUnit(long id);

    /**
    * @author x
    * @description 针对表【projectmanager(负责人信息表)】的数据库操作Service实现
    * @createDate 2024-06-26 16:17:52
    */
    @Service
    class ProjectmanagerServiceImpl extends ServiceImpl<ProjectmanagerMapper, Projectmanager>
        implements ProjectService.ProjectmanagerService {

    }

    /**
    * @author x
    * @description 针对表【unit(单位表)】的数据库操作Service
    * @createDate 2024-06-26 16:18:00
    */
}
