package cn.qiang.springcolud.servicerest;

import cn.qiang.springcloud.entities.common.ResultMessage;
//import cn.qiang.springcolud.servicerest.impl.RestPaymentServiceImpl;
import cn.qiang.springcolud.servicerest.impl.RestPaymentServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "cloud-service-hystrix-payment", fallback = RestPaymentServiceImpl.class)
public interface RestPaymentService {

    @GetMapping("/payment/get/{id}")
    public ResultMessage getPayment(@PathVariable("id") Long id);

    @GetMapping("/payment/consul/info")
    public ResultMessage getConsulInfo();


}
