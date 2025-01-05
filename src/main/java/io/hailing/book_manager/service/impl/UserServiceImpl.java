package io.hailing.book_manager.service.impl;

import io.hailing.book_manager.entity.pojo.User;
import io.hailing.book_manager.mapper.UserMapper;
import io.hailing.book_manager.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
