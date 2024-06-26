package cn.xrp.projectmanagementbackend.model;

import cn.xrp.projectmanagementbackend.model.Project;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
@NoArgsConstructor
@Data
public class ProjectDTO extends Project {
    /**
     * 负责人姓名
     */
    public String managerName;

    public ProjectDTO(Project project, String managerName) {
        this.setProjectID(project.getProjectID());
        this.setProjectLevel(project.getProjectLevel());
        this.setBudget(project.getBudget());
        this.setProjectName(project.getProjectName());
        this.setManagerID(project.getManagerID());
        this.setStartDate(project.getStartDate());
        this.managerName = managerName;
    }


    public ProjectDTO(Integer projectID, String projectName, String projectLevel, Integer managerID, Date startDate, BigDecimal budget, String managerName) {
        this.setProjectID(projectID);
        this.setProjectLevel(projectLevel);
        this.setBudget(budget);
        this.setProjectName(projectName);
        this.setManagerID(managerID);
        this.setStartDate(startDate);
        this.managerName = managerName;
    }

}
