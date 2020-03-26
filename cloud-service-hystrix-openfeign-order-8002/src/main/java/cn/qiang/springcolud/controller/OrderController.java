package cn.qiang.springcolud.controller;

import cn.qiang.springcloud.entities.Payment;
import cn.qiang.springcloud.entities.common.ResultMessage;
import cn.qiang.springcolud.servicerest.RestPaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@DefaultProperties //hystrix降级处理
public class OrderController {

    private static final String PAYMENT_URL = "http://cloud-service-payment";

    @Autowired
    private RestTemplate restTemplate;

    @Resource
    private RestPaymentService restPaymentService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/customer/payment/get/{id}")
    public ResultMessage getPayment(@PathVariable("id") Long id) {

        //ResultMessage customerMessage = restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, ResultMessage.class);
        //return customerMessage;
        log.info(id.toString());

        ResultMessage payment = restPaymentService.getPayment(id);
        return payment;
    }

    @GetMapping("/customer/payment/create")
    public ResultMessage create(Payment payment) {
        System.out.println(payment.getPayNo());
        ResultMessage resultMessage = restTemplate.postForObject(PAYMENT_URL + "/payment/create/", payment, ResultMessage.class);

        return resultMessage;
    }

    @GetMapping("/discovery/all")
    public Object getDisCovery() {
        List<String> services = discoveryClient.getServices();

        for (String service : services) {
            System.out.println(service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-SERVICE-PAYMENT");
        for (ServiceInstance instance : instances) {
            System.out.println(instance.getUri());
        }

        return discoveryClient.getServices();
    }

    @GetMapping("/consumer/payment/zk/info")
    public ResultMessage getZkInfo() {

        ResultMessage customerMessage = restTemplate.getForObject(PAYMENT_URL + "/payment/zk/info", ResultMessage.class);

        return customerMessage;
    }

    @GetMapping("/consumer/payment/consul/info")
    public ResultMessage getConsulInfo() {

//        ResultMessage customerMessage = restTemplate.getForObject(PAYMENT_URL + "/payment/consul/info", ResultMessage.class);
//        return customerMessage;
        ResultMessage consulInfo = restPaymentService.getConsulInfo();
        return consulInfo;
    }

    @GetMapping("/consumer/payment/consul-entity/info")
    public Object getConsulInfoForEntity() {

        ResponseEntity<ResultMessage> forEntity = restTemplate.getForEntity(PAYMENT_URL + "/payment/consul/info", ResultMessage.class);

        return forEntity;
    }
}
