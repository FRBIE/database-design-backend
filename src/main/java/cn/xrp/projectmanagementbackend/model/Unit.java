package cn.xrp.projectmanagementbackend.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 单位表
 * @TableName unit
 */
@TableName(value ="unit")
@Data
public class Unit implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer unitID;

    /**
     * 单位名称
     */
    private String unitName;

    /**
     * 地址
     */
    private String address;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}