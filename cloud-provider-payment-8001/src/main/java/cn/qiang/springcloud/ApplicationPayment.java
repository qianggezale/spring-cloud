package cn.qiang.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"cn.qiang.springcloud.dao"})
public class ApplicationPayment {
    public static void main(String[] args) {

        SpringApplication.run(ApplicationPayment.class, args);
    }
}
