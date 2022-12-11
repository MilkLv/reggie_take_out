package com.strive.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.strive.reggie.dto.DishDto;
import com.strive.reggie.entity.Dish;

/**
 * @author lzp moonlight
 * @create 2022-12-03 22:13
 */
public interface DishService extends IService<Dish> {

    /**
     * 保存与味道
     * 新增菜品，同时插入菜品对应的口味数据，需要同时操作两张表：dish、dish_flavor
     *
     * @param dishDto 数据传输对象
     */
     void saveWithFlavor(DishDto dishDto);


    /**
     * 通过id查询菜品信息与对应的口味信息
     *
     * @param id id
     * @return {@link DishDto}
     */
    DishDto getByIdWithFlavor(Long id);

    /**
     * 更新菜品信息，同时更新对应的口味信息
     * @param dishDto
     */
    void updateWithFlavor(DishDto dishDto);
}
