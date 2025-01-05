package io.hailing.book_manager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.hailing.book_manager.entity.pojo.Store;
import io.hailing.book_manager.mapper.StoreMapper;
import io.hailing.book_manager.service.StoreService;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store> implements StoreService {
}
