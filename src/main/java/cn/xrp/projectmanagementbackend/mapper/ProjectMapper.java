package cn.xrp.projectmanagementbackend.mapper;

import cn.xrp.projectmanagementbackend.model.Project;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author x
* @description 针对表【project(项目信息表)】的数据库操作Mapper
* @createDate 2024-06-21 21:16:01
* @Entity cn.xrp.usercenterbackend.model.Project
*/
public interface ProjectMapper extends BaseMapper<Project> {
    @Select("select  * from project_management.project;")
    public List<Project> getProjectList();

}




