package cn.qiang.springcolud.servicerest.impl;

import cn.qiang.springcloud.entities.common.ResultMessage;
import cn.qiang.springcolud.servicerest.RestPaymentService;
import org.springframework.stereotype.Component;

@Component
public class RestPaymentServiceImpl implements RestPaymentService {

    @Override
    public ResultMessage getPayment(Long id) {
        return new ResultMessage(300, "getPayment 降级", null);
    }

    @Override
    public ResultMessage getConsulInfo() {
        return new ResultMessage(300, "getConsulInfo 降级", null);
    }
}
