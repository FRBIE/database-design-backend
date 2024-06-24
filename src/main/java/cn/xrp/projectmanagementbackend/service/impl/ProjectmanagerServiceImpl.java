package cn.xrp.projectmanagementbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.xrp.projectmanagementbackend.model.Projectmanager;
import cn.xrp.projectmanagementbackend.service.ProjectmanagerService;
import cn.xrp.projectmanagementbackend.mapper.ProjectmanagerMapper;
import org.springframework.stereotype.Service;

/**
* @author x
* @description 针对表【projectmanager(负责人信息表)】的数据库操作Service实现
* @createDate 2024-06-24 17:11:27
*/
@Service
public class ProjectmanagerServiceImpl extends ServiceImpl<ProjectmanagerMapper, Projectmanager>
    implements ProjectmanagerService{

}




