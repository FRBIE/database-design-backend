package cn.xrp.projectmanagementbackend.service.impl;

import cn.xrp.projectmanagementbackend.common.ErrorCode;
import cn.xrp.projectmanagementbackend.common.ResultUtil;
import cn.xrp.projectmanagementbackend.exception.BusinessException;
import cn.xrp.projectmanagementbackend.mapper.ProjectmanagerMapper;
import cn.xrp.projectmanagementbackend.model.Projectmanager;
import cn.xrp.projectmanagementbackend.model.response.ProjectDTO;
import cn.xrp.projectmanagementbackend.service.ProjectmanagerService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.BeanUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.xrp.projectmanagementbackend.model.Project;
import cn.xrp.projectmanagementbackend.service.ProjectService;
import cn.xrp.projectmanagementbackend.mapper.ProjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    private ProjectmanagerService projectmanagerService;
    @Resource
    private ProjectMapper projectMapper;
    @Resource
    private ProjectmanagerMapper projectmanagerMapper;
    @Override
    public List<ProjectDTO> getProjectList() {
        //这里其实在mapper层用@Result注解声明映射关系更好，减少数据库压力
//        List<Project> projectList = projectMapper.getProjectList();
//        LambdaQueryWrapper<Projectmanager> wrapper = new LambdaQueryWrapper<>();
//
//        List<ProjectResponse> projectResponseList = projectList.stream().map(project ->
//                new ProjectResponse(project,(projectmanagerMapper.selectOne(wrapper.eq(Projectmanager::getManagerID,project.getManagerID()))).getManagerName()) // 假设managerName在project对象中存在
//        ).collect(Collectors.toList());

        return projectMapper.getProjectList();
    }

    @Override
    public Integer addProject(ProjectDTO projectDTO, String managerName) {
        String projectName = projectDTO.getProjectName();
        Project project = this.getOne(new LambdaQueryWrapper<Project>().eq(Project::getProjectName, projectName));
        if(project != null && project.getProjectName().equals(projectDTO.getProjectName()) ){
             throw new BusinessException(ErrorCode.PARAMS_ERROR,"该项目名称已存在");
        }

        Integer managerID = null;
        Projectmanager projectmanager = projectmanagerMapper.selectOne(new LambdaQueryWrapper<Projectmanager>().eq(Projectmanager::getManagerName, managerName));
        if(null == projectmanager){
           throw new BusinessException(ErrorCode.PARAMS_ERROR,"该负责人不存在");
        }
        managerID = projectmanager.getManagerID();
       // System.out.println("负责人ID为: " + managerID);

        project = new Project();
        project.setProjectID(projectDTO.getProjectID());
        project.setProjectName(projectDTO.getProjectName());
        project.setProjectLevel(projectDTO.getProjectLevel());
        project.setManagerID(managerID);
        project.setStartDate(projectDTO.getStartDate());
        project.setBudget(projectDTO.getBudget());
        return  projectMapper.insert(project);

    }

    @Override
    public Integer updateProject(ProjectDTO projectDTO) {
        //先查看负责人是否存在
        String managerName = projectDTO.getManagerName();
        Projectmanager manager = projectmanagerService.getOne(new LambdaQueryWrapper<Projectmanager>().eq(Projectmanager::getManagerName, managerName));
        if(manager == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"该负责人不存在");
        }
        Project project = new Project();
        project.setProjectID(projectDTO.getProjectID());
        project.setProjectName(projectDTO.getProjectName());
        project.setProjectLevel(projectDTO.getProjectLevel());
        project.setManagerID(manager.getManagerID()); //前端传的是负责人姓名，这里负责人ID从数据库中取得
        project.setStartDate(projectDTO.getStartDate());
        project.setBudget(projectDTO.getBudget());
        return projectMapper.updateById(project);
    }

    @Override
    public List<ProjectDTO> search(LambdaQueryWrapper<Project> wrapper) {
        List<Project> list = list(wrapper);
        List<ProjectDTO> projectDTOList = new ArrayList<>();
        for (Project project : list) {
            Integer managerID = project.getManagerID();
            Projectmanager manager = projectmanagerService.getById(managerID);
            String managerName = manager.getManagerName();
            projectDTOList.add(new ProjectDTO(project,managerName));
        }
        return projectDTOList;
    }
}




