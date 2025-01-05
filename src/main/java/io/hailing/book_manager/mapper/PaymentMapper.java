package io.hailing.book_manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.hailing.book_manager.entity.pojo.Payment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper extends BaseMapper<Payment> {
}
