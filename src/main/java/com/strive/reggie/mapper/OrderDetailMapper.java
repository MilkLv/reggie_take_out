package com.strive.reggie.mapper;

import com.strive.reggie.entity.OrderDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author niwei
* @description 针对表【order_detail(订单明细表)】的数据库操作Mapper
* @createDate 2022-12-07 22:42:33
* @Entity com.strive.reggie.entity.OrderDetail
*/
@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {

}




