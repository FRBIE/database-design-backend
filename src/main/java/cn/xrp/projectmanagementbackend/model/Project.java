package cn.xrp.projectmanagementbackend.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 项目信息表
 * @TableName project
 */
@TableName(value ="project")
@Data
public class Project implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer projectID;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 项目级别
     */
    private String projectLevel;

    /**
     * 负责人ID
     */
    private Integer managerID;

    /**
     * 立项日期
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date startDate;

    /**
     * 经费额
     */
    private BigDecimal budget;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}