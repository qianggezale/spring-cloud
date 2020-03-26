package cn.qiang.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer //开启配置中心 直接刷新总服务即可 例如：http://localhost:5010/actuator/bus-refresh
public class AppConfigBusServer {
    public static void main(String[] args) {

        SpringApplication.run(AppConfigBusServer.class, args);
    }
}
