package cn.xrp.projectmanagementbackend.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 负责人信息表
 * @TableName projectmanager
 */
@TableName(value ="projectmanager")
@Data
public class Projectmanager implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer managerID;

    /**
     * 负责人姓名
     */
    private String managerName;

    /**
     * 年龄
     */
    private Integer age;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}