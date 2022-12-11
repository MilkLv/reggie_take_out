package com.strive.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.strive.reggie.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lzp moonlight
 * @create 2022-12-05 21:16
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
