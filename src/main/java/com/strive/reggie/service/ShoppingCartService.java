package com.strive.reggie.service;

import com.strive.reggie.entity.ShoppingCart;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author niwei
* @description 针对表【shopping_cart(购物车)】的数据库操作Service
* @createDate 2022-12-07 19:58:23
*/
public interface ShoppingCartService extends IService<ShoppingCart> {

    /**
     * 清空购物车
     */
    void clean();
}
