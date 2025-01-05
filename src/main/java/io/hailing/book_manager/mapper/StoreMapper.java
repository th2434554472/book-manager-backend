package io.hailing.book_manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.hailing.book_manager.entity.pojo.Store;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StoreMapper extends BaseMapper<Store> {
}
