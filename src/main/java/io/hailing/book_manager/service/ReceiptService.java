package io.hailing.book_manager.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import io.hailing.book_manager.entity.pojo.Receipt;
import io.hailing.book_manager.entity.vo.ReceiptVO;

public interface ReceiptService extends IService<Receipt> {
    void saveReceipt(ReceiptVO receiptVO);

    void updateReceipt(ReceiptVO receiptVO);

    Page<ReceiptVO> pageInfo(Long page, Long pageSize);

    ReceiptVO getReceiptVOById(Long id);
}
