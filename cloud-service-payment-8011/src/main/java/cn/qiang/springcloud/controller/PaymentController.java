package cn.qiang.springcloud.controller;

import cn.qiang.springcloud.entities.Payment;
import cn.qiang.springcloud.entities.common.ResultMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import cn.qiang.springcloud.service.PaymentService;

import javax.annotation.Resource;
import java.util.List;

@RestController
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
    public ResultMessage getPayment(@PathVariable("id") Long id) {
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
}
