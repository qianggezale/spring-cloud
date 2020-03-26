package cn.qiang.springcloud.controller;

import cn.qiang.springcloud.entities.Payment;
import cn.qiang.springcloud.entities.common.ResultMessage;
import cn.qiang.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String port;

    @GetMapping("/payment/get/all")
    public ResultMessage getAllPayment() {
        ResultMessage resultMessage = new ResultMessage();
        List<Payment> allPayment = paymentService.getAllPayment();
        if (allPayment != null) {
            resultMessage.setCode(200);
            resultMessage.setMsg("成功,条数：" + allPayment.size());
            resultMessage.setData(allPayment);
        } else {
            resultMessage.setCode(404);
            resultMessage.setMsg("失败");
        }
        return resultMessage;
    }

    @GetMapping("/payment/get/{id}")
    @HystrixCommand(fallbackMethod = "getPaymentFallBack", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public ResultMessage getPayment(@PathVariable("id") Long id) {

        //int i = 9 / 0;
        log.info(id.toString());
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            //e.printStackTrace();
//        }
        ResultMessage resultMessage = new ResultMessage();
        Payment payment = paymentService.getPayment(id);
        if (payment != null) {
            resultMessage.setCode(200);
            resultMessage.setMsg("成功，端口：" + port);
            resultMessage.setData(payment);
        } else {
            resultMessage.setCode(404);
            resultMessage.setMsg("失败");
        }
        return resultMessage;
    }

    public ResultMessage getPaymentFallBack(@PathVariable("id") Long id) {
        ResultMessage resultMessage = new ResultMessage(300, "服务端降级处理", null);
        return resultMessage;
    }

    @PostMapping("/payment/create")
    public ResultMessage create(@RequestBody Payment payment) {

        //System.out.println(payment.getPayNo());
        ResultMessage resultMessage = new ResultMessage();
        Integer num = paymentService.create(payment);
        if (num > 0) {
            resultMessage.setCode(200);
            resultMessage.setMsg("成功");
            resultMessage.setData(num);
        } else {
            resultMessage.setCode(404);
            resultMessage.setMsg("失败");
        }
        return resultMessage;
    }

    @GetMapping("/payment/zk/info")
    public ResultMessage getZkInfo() {

        return new ResultMessage(200, "OK,端口：" + port, null);
    }

    @GetMapping("/payment/consul/info")
    public ResultMessage getConsulInfo() {

        return new ResultMessage(200, "OK,端口：" + port, null);
    }

    //hystrix 熔断
    @HystrixCommand(fallbackMethod = "getPaymentBreakFallBack", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), //确定断路器是否用于跟踪运行状况和短路请求（如果跳闸）
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "20"), //熔断触发的最小个数/10s 默认值：20
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), //熔断多少秒后去尝试请求 默认值：5000
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "20"), //失败率达到多少百分比后熔断 默认值：50
    })
    @GetMapping("/payment/getbreak/{id}")
    public ResultMessage getPaymentBreak(@PathVariable("id") Long id) {
        //log.info(id.toString());
        if (id < 0) {
            throw new RuntimeException("id 太小了！");
        }
        ResultMessage resultMessage = new ResultMessage();
        Payment payment = paymentService.getPayment(id);
        if (payment != null) {
            resultMessage.setCode(200);
            resultMessage.setMsg("成功，端口：" + port);
            resultMessage.setData(payment);
        } else {
            resultMessage.setCode(404);
            resultMessage.setMsg("失败");
        }
        return resultMessage;
    }

    public ResultMessage getPaymentBreakFallBack(@PathVariable("id") Long id) {
        ResultMessage resultMessage = new ResultMessage(300, "服务端降级处理-break", null);
        return resultMessage;
    }
}
