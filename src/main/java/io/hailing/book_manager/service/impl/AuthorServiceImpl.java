package io.hailing.book_manager.service.impl;

import io.hailing.book_manager.entity.pojo.Author;
import io.hailing.book_manager.mapper.AuthorMapper;
import io.hailing.book_manager.service.AuthorService;
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
public class AuthorServiceImpl extends ServiceImpl<AuthorMapper, Author> implements AuthorService {

}
