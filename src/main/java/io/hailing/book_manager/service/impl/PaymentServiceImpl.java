package io.hailing.book_manager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.hailing.book_manager.entity.pojo.Payment;
import io.hailing.book_manager.mapper.PaymentMapper;
import io.hailing.book_manager.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper,Payment> implements PaymentService {
}
