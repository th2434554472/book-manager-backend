package io.hailing.book_manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ser.Serializers;
import io.hailing.book_manager.common.BaseContext;
import io.hailing.book_manager.entity.pojo.Payment;
import io.hailing.book_manager.entity.pojo.Receipt;
import io.hailing.book_manager.entity.pojo.Store;
import io.hailing.book_manager.entity.vo.ReceiptVO;
import io.hailing.book_manager.mapper.ReceiptMapper;
import io.hailing.book_manager.service.PaymentService;
import io.hailing.book_manager.service.ReceiptService;
import io.hailing.book_manager.service.StoreService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceiptServiceImpl extends ServiceImpl<ReceiptMapper, Receipt> implements ReceiptService {

    @Autowired
    private ReceiptMapper receiptMapper;
    @Autowired
    private StoreService storeService;
    @Autowired
    private PaymentService paymentService;

    /**
     * 保存收据
     * @param receiptVO
     */
    @Override
    public void saveReceipt(ReceiptVO receiptVO) {
        Receipt receipt = saveOrUpdate(receiptVO);
        receiptMapper.insert(receipt);
    }

    /**
     * 更新收据
     * @param receiptVO
     */
    @Override
    public void updateReceipt(ReceiptVO receiptVO) {
        Receipt receipt = saveOrUpdate(receiptVO);
        receiptMapper.updateById(receipt);
    }

    /**
     * 分页查询收据
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public Page<ReceiptVO> pageInfo(Long page, Long pageSize) {
        Page<Receipt> pageInfo = new Page<>(page, pageSize);
        Long userId = BaseContext.getCurrentId();
        Page<ReceiptVO> receiptVOPage = receiptMapper.pageQuery(pageInfo,userId);
        return receiptVOPage;
    }

    /**
     * 根据id查询收据
     * @param id
     * @return
     */
    @Override
    public ReceiptVO getReceiptVOById(Long id) {
        Receipt receipt = receiptMapper.selectById(id);
        ReceiptVO receiptVO = new ReceiptVO();

        return null;
    }

    private Receipt saveOrUpdate(ReceiptVO receiptVO) {
        Receipt receipt = new Receipt();
        BeanUtils.copyProperties(receiptVO, receipt);
        Long userId = BaseContext.getCurrentId();
        receipt.setUserId(userId);
        // 查询店铺
        String storeName = receiptVO.getStore();
        LambdaQueryWrapper<Store> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Store::getName, storeName);
        Store store = storeService.getOne(queryWrapper);
        // 查询支付方式
        String paymentName = receiptVO.getPayment();
        LambdaQueryWrapper<Payment> paymentQueryWrapper = new LambdaQueryWrapper<>();
        paymentQueryWrapper.eq(Payment::getName, paymentName);
        Payment payment = paymentService.getOne(paymentQueryWrapper);
        receipt.setStoreId(store.getId());
        receipt.setPaymentId(payment.getId());
        return receipt;
    }
}
