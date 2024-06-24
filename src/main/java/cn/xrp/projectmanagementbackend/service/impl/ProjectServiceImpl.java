package cn.xrp.projectmanagementbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.xrp.projectmanagementbackend.model.Project;
import cn.xrp.projectmanagementbackend.service.ProjectService;
import cn.xrp.projectmanagementbackend.mapper.ProjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author x
* @description 针对表【project(项目信息表)】的数据库操作Service实现
* @createDate 2024-06-21 21:16:01
*/
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project>
    implements ProjectService{

    @Resource
    private ProjectMapper projectMapper;
    @Override
    public List<Project> getProjectList() {
        return projectMapper.getProjectList();
    }
}




