package cn.qiang.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
//开启配置中心 直接刷新总服务即可 例如：http://localhost:5010/actuator/bus-refresh
//定点同步中心下的某一个客户端修改配置 例如：http://localhost:5010/actuator/bus-refresh/cloud-config-bus-client:5020
//http://链接/actuator/bus-refresh/spring.application.name:server.port
@EnableConfigServer
public class AppConfigBusServer {
    public static void main(String[] args) {

        SpringApplication.run(AppConfigBusServer.class, args);
    }
}
