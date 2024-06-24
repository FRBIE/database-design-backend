package cn.xrp.projectmanagementbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.xrp.projectmanagementbackend.mapper")
public class ProjectManagementBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectManagementBackendApplication.class, args);
    }

}
