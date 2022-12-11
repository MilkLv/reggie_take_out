package com.strive.reggie.mapper;

import com.strive.reggie.entity.ShoppingCart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author lzp
* @description 针对表【shopping_cart(购物车)】的数据库操作Mapper
* @createDate 2022-12-07 19:58:23
* @Entity com.strive.reggie.entity.ShoppingCart
*/
@Mapper
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {

}




