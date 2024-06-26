package cn.xrp.projectmanagementbackend.service;

import cn.xrp.projectmanagementbackend.model.Project;
import cn.xrp.projectmanagementbackend.model.Projectmanager;
import cn.xrp.projectmanagementbackend.model.ProjectDTO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author x
* @description 针对表【project(项目信息表)】的数据库操作Service
* @createDate 2024-06-21 21:16:01
*/
public interface ProjectService extends IService<Project> {

    public List<ProjectDTO> getProjectList();

    Integer addProject(ProjectDTO projectDTO, String managerName);

    Integer updateProject(ProjectDTO projectDTO);

    List<ProjectDTO> search(LambdaQueryWrapper<Project> wrapper);

    /**
    * @author x
    * @description 针对表【projectmanager(负责人信息表)】的数据库操作Service
    * @createDate 2024-06-26 16:17:52
    */
    interface ProjectmanagerService extends IService<Projectmanager> {

    }
}
