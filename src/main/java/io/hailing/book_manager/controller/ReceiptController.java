package io.hailing.book_manager.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.hailing.book_manager.common.Result;
import io.hailing.book_manager.entity.pojo.Payment;
import io.hailing.book_manager.entity.pojo.Receipt;
import io.hailing.book_manager.entity.pojo.Store;
import io.hailing.book_manager.entity.vo.ReceiptVO;
import io.hailing.book_manager.service.PaymentService;
import io.hailing.book_manager.service.ReceiptService;
import io.hailing.book_manager.service.StoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/receipt")
@CrossOrigin
@Api(tags = "账单相关接口")
@Slf4j
public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private PaymentService paymentService;


    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page/{page}/{pageSize}")
    @ApiOperation("分页查询")
    public Result<Page> page(@PathVariable Long page, @PathVariable Long pageSize) {
        log.info("分页查询：{}", page, pageSize);
        Page<ReceiptVO> receiptVOPage = receiptService.pageInfo(page,pageSize);
        return Result.success(receiptVOPage);
    }

    /**
     * 新增账单
     * @param receiptVO
     * @return
     */
    @PostMapping
    @ApiOperation("新增账单")
    public Result save(@RequestBody ReceiptVO receiptVO) {
        log.info("新增账单:{}", receiptVO);
        receiptService.saveReceipt(receiptVO);
        return Result.success();
    }

    /**
     * 修改账单
     * @param receiptVO
     * @return
     */
    @PutMapping("/update")
    @ApiOperation("修改账单")
    public Result update(@RequestBody ReceiptVO receiptVO) {
        log.info("修改账单:{}", receiptVO);
        receiptService.updateReceipt(receiptVO);
        return Result.success();
    }

    /**
     * 查询账单
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("查询账单")
    public Result<ReceiptVO> getById(@PathVariable Long id) {
        log.info("查询账单:{}", id);
        Receipt receipt = receiptService.getById(id);
        ReceiptVO receiptVO = new ReceiptVO();
        BeanUtils.copyProperties(receipt, receiptVO);
        Store store = storeService.getById(receipt.getStoreId());
        Payment payment = paymentService.getById(receipt.getPaymentId());
        receiptVO.setStore(store.getName());
        receiptVO.setPayment(payment.getName());
        return Result.success(receiptVO);
    }

}
