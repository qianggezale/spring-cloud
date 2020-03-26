package cn.qiang.springcloud;


import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan({"cn.qiang.springcloud.dao"})
@EnableFeignClients //Feign调用开启
@EnableDiscoveryClient //该注解用于使用 zookeeper和consul为注册中心时使用
//@EnableHystrix //开启服务降级熔断限流
@EnableCircuitBreaker //降级 目前是与EnableHystrix降级功能一样
public class ApplicationHystrixOpenfeignPayment {
    public static void main(String[] args) {

        SpringApplication.run(ApplicationHystrixOpenfeignPayment.class, args);
    }

    /*
     *监控启动bean
     */
    @Bean
    public ServletRegistrationBean getServlet() {

        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);  //系统启动时加载顺序
        registrationBean.addUrlMappings("/hystrix.stream");//路径
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}
