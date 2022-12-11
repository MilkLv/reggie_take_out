package com.strive.reggie.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.strive.reggie.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author niwei
* @description 针对表【orders(订单表)】的数据库操作Service
* @createDate 2022-12-07 22:42:25
*/
public interface OrdersService extends IService<Orders> {

    /**
     * 提交订单
     *
     * @param orders 订单
     */
    void submit(Orders orders);

    /**
     * 后台管理订单页面
     *
     * @param page      页面
     * @param pageSize  页面大小
     * @param number    数量
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @return {@link Page}<{@link Orders}>
     */
    Page<Orders> page(int page,int pageSize,Long number,String beginTime, String endTime);

    /**
     * 订单派送以及完成按钮
     * @param orders
     */
    Orders status(Orders orders);

    /**
     * 再来一单
     * @param orders
     */
    void againSubmit(Orders orders);
}
