package com.strive.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.strive.reggie.dto.SetmealDto;
import com.strive.reggie.entity.Setmeal;

import java.util.List;

/**
 * @author lzp moonlight
 * @create 2022-12-03 22:13
 */
public interface SetmealService extends IService<Setmeal> {

    /**
     * 新增套餐，同时需要保存套餐和菜品的关联关系
     *
     * @param setmealDto setmeal dto
     */
    void saveWithDish(SetmealDto setmealDto);

    /**
     * 删除套餐，同时需要删除套餐和菜品的关联数据 setmeal 和 setmeal_dish两张表的数据
     * @param ids 套餐id集合
     */
    void removeWithDish(List<Long> ids);
}
