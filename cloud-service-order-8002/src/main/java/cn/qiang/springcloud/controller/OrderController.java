package cn.qiang.springcloud.controller;

import cn.qiang.springcloud.entities.Payment;
import cn.qiang.springcloud.entities.common.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class OrderController {

    private static final String PAYMENT_URL = "http://cloud-service-payment";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/customer/payment/get/{id}")
    public ResultMessage getPayment(@PathVariable("id") Long id) {

        ResultMessage customerMessage = restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, ResultMessage.class);

        return customerMessage;
    }

    @GetMapping("/customer/payment/create")
    public ResultMessage create(Payment payment) {
        System.out.println(payment.getPayNo());
        ResultMessage resultMessage = restTemplate.postForObject(PAYMENT_URL + "/payment/create/", payment, ResultMessage.class);

        return resultMessage;
    }

    @GetMapping("/discovery/all")
    public Object getDisCovery() {
//        List<String> services = discoveryClient.getServices();
//
//        for (String service : services) {
//            System.out.println(service);
//        }
//        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-SERVICE-PAYMENT");
//        for (ServiceInstance instance : instances) {
//            System.out.println(instance.getUri());
//        }

        return discoveryClient.getServices();
    }

    @GetMapping("/consumer/payment/zk/info")
    public ResultMessage getZkInfo() {

        ResultMessage customerMessage = restTemplate.getForObject(PAYMENT_URL + "/payment/zk/info", ResultMessage.class);

        return customerMessage;
    }
}
