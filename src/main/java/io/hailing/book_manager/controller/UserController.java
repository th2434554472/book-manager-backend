package io.hailing.book_manager.controller;

import io.hailing.book_manager.common.*;
import io.hailing.book_manager.entity.pojo.User;
import io.hailing.book_manager.entity.dto.UserLoginDTO;
import io.hailing.book_manager.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.hailing.book_manager.utils.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Api(tags = "用户接口")
@RequestMapping("/user")
@CrossOrigin
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 用户登录
     * @param userLoginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation("登录")
    public Result login(@RequestBody UserLoginDTO userLoginDTO){
        log.info("用户登录：{}",userLoginDTO);
        //TODO 密码加密
        //根据用户名查询数据库
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,userLoginDTO.getUsername());
        User user = userService.getOne(queryWrapper);
        //如果没有查询到则返回登录失败结果
        if(user == null){
            return Result.error("用户名或密码错误");
        }
        //密码比对，如果不一致则返回登录失败
        if(!user.getPassword().equals(userLoginDTO.getPassword())){
            return Result.error("密码错误");
        }
        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getUserSecretKey(),
                jwtProperties.getUserTtl(),
                claims);
        //登录成功
        return Result.success(token);
    }

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping("/info")
    @ApiOperation("获取用户信息")
    public Result getInfo(){
        Long userId = BaseContext.getCurrentId();
        User user = userService.getById(userId);
        return Result.success(user);
    }
}
