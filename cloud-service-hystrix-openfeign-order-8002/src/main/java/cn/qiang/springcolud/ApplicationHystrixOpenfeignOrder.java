package cn.qiang.springcolud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients //Feign调用开启
@EnableDiscoveryClient //该注解用于使用 zookeeper和consul为注册中心时使用
@EnableHystrix //开启服务降级熔断限流
@EnableCircuitBreaker //降级
public class ApplicationHystrixOpenfeignOrder {
    public static void main(String[] args) {

        SpringApplication.run(ApplicationHystrixOpenfeignOrder.class, args);
    }
}
