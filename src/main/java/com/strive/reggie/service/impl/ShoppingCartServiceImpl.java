package com.strive.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.strive.reggie.common.BaseContext;
import com.strive.reggie.entity.ShoppingCart;
import com.strive.reggie.service.ShoppingCartService;
import com.strive.reggie.mapper.ShoppingCartMapper;
import org.springframework.stereotype.Service;

/**
* @author niwei
* @description 针对表【shopping_cart(购物车)】的数据库操作Service实现
* @createDate 2022-12-07 19:58:23
*/
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart>
        implements ShoppingCartService{

    /**
     * 清空购物车
     */
    @Override
    public void clean() {
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, BaseContext.getCurrentId());
        this.remove(queryWrapper);
    }
}




