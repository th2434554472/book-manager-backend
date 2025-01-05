package io.hailing.book_manager.service.impl;

import io.hailing.book_manager.entity.pojo.Category;
import io.hailing.book_manager.mapper.CategoryMapper;
import io.hailing.book_manager.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2023-10-23
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
