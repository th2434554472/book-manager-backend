package io.hailing.book_manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.hailing.book_manager.entity.pojo.Receipt;
import io.hailing.book_manager.entity.vo.ReceiptVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReceiptMapper extends BaseMapper<Receipt> {
    Page<ReceiptVO> pageQuery(Page<Receipt> pageInfo,Long userId);
}
