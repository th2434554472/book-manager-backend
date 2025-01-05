package io.hailing.book_manager.controller;

import io.hailing.book_manager.common.Result;
import io.hailing.book_manager.entity.pojo.Payment;
import io.hailing.book_manager.service.PaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
@CrossOrigin
@Slf4j
@Api(tags = "支付管理", value = "/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/list")
    @ApiOperation("查询所有支付方式")
    public Result list() {
        List<Payment> list = paymentService.list();
        return Result.success(list);
    }

    @PostMapping("/save")
    @ApiOperation("新增支付方式")
    public Result savePayment(Payment payment) {
        paymentService.save(payment);
        return Result.success();
    }
}
