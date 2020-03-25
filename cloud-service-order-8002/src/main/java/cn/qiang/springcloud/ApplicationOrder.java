package cn.qiang.springcloud;

import cn.qiang.ribbonrule.RibbonRuleConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//启动Ribbon配置的新规则
@RibbonClient(name = "cloud-service-payment", configuration = RibbonRuleConfig.class)
@SpringBootApplication
//@EnableEurekaClient
@EnableDiscoveryClient //该注解用于使用 zookeeper和consul为注册中心时使用
public class ApplicationOrder {
    public static void main(String[] args) {

        SpringApplication.run(ApplicationOrder.class, args);
    }
}
