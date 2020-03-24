package cn.qiang.springcloud.dao;

import cn.qiang.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PaymentDao {

    int create(Payment payment);

    List<Payment> getAllPayment();

    Payment getPayment(Long id);
}
