package cn.qiang.springcloud.service.impl;

import cn.qiang.springcloud.dao.PaymentDao;
import cn.qiang.springcloud.entities.Payment;
import cn.qiang.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PaymentImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public List<Payment> getAllPayment() {
        return paymentDao.getAllPayment();
    }

    @Override
    public Payment getPayment(Long id) {
        return paymentDao.getPayment(id);
    }
}
