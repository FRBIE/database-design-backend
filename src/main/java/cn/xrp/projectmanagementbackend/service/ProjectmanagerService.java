package cn.xrp.projectmanagementbackend.service;

import cn.xrp.projectmanagementbackend.common.BaseResponse;
import cn.xrp.projectmanagementbackend.model.ProjectManagerDTO;
import cn.xrp.projectmanagementbackend.model.Projectmanager;
import cn.xrp.projectmanagementbackend.model.request.ManagerRequest;
import cn.xrp.projectmanagementbackend.model.request.ManagerSearchRequest;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author x
* @description 针对表【projectmanager(负责人信息表)】的数据库操作Service
* @createDate 2024-06-26 17:08:10
*/
public interface ProjectmanagerService extends IService<Projectmanager> {

    List<ProjectManagerDTO> getManagerList();

    BaseResponse addManager(Projectmanager manager, List<Integer> checkdUnitIDList);

    BaseResponse<Boolean> deleteManager(long managerId);

    BaseResponse<Boolean> editManager(ManagerRequest managerRequest);

    List<ProjectManagerDTO> searchManager(ManagerSearchRequest managerSearchRequest);
}
