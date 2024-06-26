package cn.xrp.projectmanagementbackend.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public class ProjectManagerDTO extends Projectmanager{
    /**
     * 所属单位ID:单位名称 List
     */
    public List<HashMap<Integer, String>> unitNameList;
    public BigDecimal totalAmount;
    public ProjectManagerDTO(Projectmanager projectmanager, List<HashMap<Integer, String>> unitNameList) {
        super(projectmanager);
        this.unitNameList = unitNameList;
    }
    public ProjectManagerDTO(Projectmanager projectmanager, List<HashMap<Integer, String>> unitNameList,BigDecimal totalAmount) {
        super(projectmanager);
        this.unitNameList = unitNameList;
        this.totalAmount = totalAmount;
    }
}
