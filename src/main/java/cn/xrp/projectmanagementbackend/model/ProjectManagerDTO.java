package cn.xrp.projectmanagementbackend.model;

import java.util.HashMap;
import java.util.List;

public class ProjectManagerDTO extends Projectmanager{
    /**
     * 所属单位ID:单位名称 List
     */
    public List<HashMap<Integer, String>> unitNameList;

    public ProjectManagerDTO(Projectmanager projectmanager, List<HashMap<Integer, String>> unitNameList) {
        super(projectmanager);
        this.unitNameList = unitNameList;
    }
}
