package com.strive.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.strive.reggie.entity.User;
import com.strive.reggie.mapper.UserMapper;
import com.strive.reggie.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author lzp moonlight
 * @create 2022-12-05 21:17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
