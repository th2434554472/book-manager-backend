package io.hailing.book_manager.service.impl;

import io.hailing.book_manager.entity.Book;
import io.hailing.book_manager.mapper.BookMapper;
import io.hailing.book_manager.service.BookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2023-10-19
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

}
