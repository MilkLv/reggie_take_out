package com.strive.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.strive.reggie.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lzp moonlight
 * @create 2022-12-03 22:11
 */
@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
