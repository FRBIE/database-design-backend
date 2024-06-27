package cn.xrp.projectmanagementbackend.model.request;

import cn.xrp.projectmanagementbackend.model.Projectmanager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagerSearchRequest extends Projectmanager {
    private String unitName;
}
