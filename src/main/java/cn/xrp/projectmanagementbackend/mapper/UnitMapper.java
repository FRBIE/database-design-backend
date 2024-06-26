package cn.xrp.projectmanagementbackend.mapper;

import cn.xrp.projectmanagementbackend.model.Unit;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
* @author x
* @description 针对表【unit(单位表)】的数据库操作Mapper
* @createDate 2024-06-26 16:18:00
* @Entity cn.xrp.projectmanagementbackend.model.Unit
*/
public interface UnitMapper extends BaseMapper<Unit> {
    void DeleteUnit(@Param("t_UnitID") Integer unitID);
}




