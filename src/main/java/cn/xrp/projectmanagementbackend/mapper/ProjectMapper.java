package cn.xrp.projectmanagementbackend.mapper;

import cn.xrp.projectmanagementbackend.model.Project;
import cn.xrp.projectmanagementbackend.model.ProjectDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author x
* @description 针对表【project(项目信息表)】的数据库操作Mapper
* @createDate 2024-06-21 21:16:01
* @Entity cn.xrp.usercenterbackend.model.Project
*/
public interface ProjectMapper extends BaseMapper<Project> {
    @Select("SELECT project.*, projectmanager.ManagerName FROM project  JOIN projectmanager  ON project.ManagerID = projectmanager.ManagerID")
    @Results({
            @Result(property = "projectID", column = "projectID"),
            @Result(property = "projectName", column = "projectName"),
            @Result(property = "projectLevel", column = "projectLevel"),
            @Result(property = "managerID", column = "managerID"),
            @Result(property = "startDate", column = "startDate"),
            @Result(property = "budget", column = "budget"),
            @Result(property = "managerName", column = "ManagerName")
    })//Result底层是调用构造器
    public List<ProjectDTO> getProjectList();
}




