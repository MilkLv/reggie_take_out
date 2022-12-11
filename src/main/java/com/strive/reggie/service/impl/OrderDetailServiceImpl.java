package com.strive.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.strive.reggie.entity.OrderDetail;
import com.strive.reggie.service.OrderDetailService;
import com.strive.reggie.mapper.OrderDetailMapper;
import org.springframework.stereotype.Service;

/**
* @author niwei
* @description 针对表【order_detail(订单明细表)】的数据库操作Service实现
* @createDate 2022-12-07 22:42:33
*/
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail>
    implements OrderDetailService{

}




