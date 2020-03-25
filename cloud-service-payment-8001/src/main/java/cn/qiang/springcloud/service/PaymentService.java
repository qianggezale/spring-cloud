package cn.qiang.springcloud.service;

import cn.qiang.springcloud.entities.Payment;

import java.util.List;

public interface PaymentService {

    int create(Payment payment);

    List<Payment> getAllPayment();

    Payment getPayment(Long id);
}
