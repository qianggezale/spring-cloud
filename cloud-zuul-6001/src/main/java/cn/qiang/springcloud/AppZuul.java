package cn.qiang.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy //开启zuul网关功能
public class AppZuul {
    public static void main(String[] args) {


        SpringApplication.run(AppZuul.class,args);
    }
}
