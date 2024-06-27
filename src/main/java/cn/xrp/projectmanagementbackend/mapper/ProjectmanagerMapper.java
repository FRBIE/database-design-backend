package cn.xrp.projectmanagementbackend.mapper;

import cn.xrp.projectmanagementbackend.model.ProjectDTO;
import cn.xrp.projectmanagementbackend.model.ProjectManagerDTO;
import cn.xrp.projectmanagementbackend.model.Projectmanager;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author x
* @description 针对表【projectmanager(负责人信息表)】的数据库操作Mapper
* @createDate 2024-06-26 17:08:10
* @Entity cn.xrp.projectmanagementbackend.model.Projectmanager
*/
public interface ProjectmanagerMapper extends BaseMapper<Projectmanager> {
    Integer getTotalBudgetByManagerID(@Param("ManagerID") Integer managerID);
}




