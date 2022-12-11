package com.strive.reggie.mapper;

import com.strive.reggie.entity.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author niwei
* @description 针对表【orders(订单表)】的数据库操作Mapper
* @createDate 2022-12-07 22:42:25
* @Entity com.strive.reggie.entity.Orders
*/
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {

}




