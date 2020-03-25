package cn.qiang.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan({"cn.qiang.springcloud.dao"})
//@EnableEurekaClient
@EnableDiscoveryClient //该注解用于使用 zookeeper和consul为注册中心时使用
public class ApplicationPayment {
    public static void main(String[] args) {

        SpringApplication.run(ApplicationPayment.class, args);
    }
}
