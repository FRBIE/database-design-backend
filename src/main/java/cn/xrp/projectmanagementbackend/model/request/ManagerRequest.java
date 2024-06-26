package cn.xrp.projectmanagementbackend.model.request;

import cn.xrp.projectmanagementbackend.model.Projectmanager;
import lombok.Data;

import java.util.List;
@Data
public class ManagerRequest {
    private Projectmanager manager;
    private List<Integer> checkdUnitIDList;
}
