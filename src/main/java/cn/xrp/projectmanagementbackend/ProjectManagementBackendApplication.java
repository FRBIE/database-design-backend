package cn.xrp.projectmanagementbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("cn.xrp.projectmanagementbackend.mapper")
@EnableTransactionManagement
public class ProjectManagementBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectManagementBackendApplication.class, args);
    }

}
