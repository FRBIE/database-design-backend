package cn.xrp.projectmanagementbackend.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 负责人-单位关系表
 * @TableName manager_unit
 */
@TableName(value ="manager_unit")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagerUnit implements Serializable {
    /**
     * 
     */
    @TableId
    private Integer manager_id;

    private Integer unit_id;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}