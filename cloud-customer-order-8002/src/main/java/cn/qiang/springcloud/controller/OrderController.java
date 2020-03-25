package cn.qiang.springcloud.controller;

import cn.qiang.springcloud.entities.Payment;
import cn.qiang.springcloud.entities.common.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {

    private static final String PAYMENT_URL = "http://localhost:8001";

    @Autowired
    private RestTemplate restTemplate;

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
}
