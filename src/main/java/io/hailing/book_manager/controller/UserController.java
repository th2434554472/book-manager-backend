package io.hailing.book_manager.controller;

import io.hailing.book_manager.common.R;
import io.hailing.book_manager.entity.User;
import io.hailing.book_manager.entity.dto.UserLoginDTO;
import io.hailing.book_manager.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "用户接口")
@RequestMapping("/user")
@CrossOrigin
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param userLoginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation("登录")
    public R login(@RequestBody UserLoginDTO userLoginDTO){
        log.info("用户登录：{}",userLoginDTO);
        //TODO 密码加密
        //根据用户名查询数据库
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,userLoginDTO.getUsername());
        User user = userService.getOne(queryWrapper);
        //如果没有查询到则返回登录失败结果
        if(user == null){
            return R.error().message("用户不存在");
        }
        //密码比对，如果不一致则返回登录失败
        if(!user.getPassword().equals(userLoginDTO.getPassword())){
            return R.error().message("密码错误");
        }
        //登录成功
        return R.ok().data("token","admin");
    }

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping("/info")
    @ApiOperation("获取用户信息")
    public R getInfo(){
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
