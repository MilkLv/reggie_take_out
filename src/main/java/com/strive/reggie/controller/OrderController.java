package com.strive.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.strive.reggie.common.BaseContext;
import com.strive.reggie.common.R;
import com.strive.reggie.entity.Orders;
import com.strive.reggie.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lzp moonlight
 * @create 2022-12-07 22:44
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrdersService ordersService;

    /**
     * 用户下单
     * @param orders
     * @return
     */
    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders){
        log.info("{}",orders);
        ordersService.submit(orders);
        return R.success("下单成功");
    }

    /**
     * 查看订单分页
     * @return
     */
    @GetMapping("/userPage")
    public R<Page> userPage(int page,int pageSize){
        Page<Orders> pageInfo = new Page<>(page, pageSize);
        Long userId = BaseContext.getCurrentId();
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Orders::getUserId,userId)
                .orderByDesc(Orders::getOrderTime);
        ordersService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }

    /**
     * 后端管理订单页面
     * @param page 页面
     * @param pageSize 页面大小
     * @param number 订单编号
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,Long number,String beginTime, String endTime){
        Page<Orders> pageInfo = ordersService.page(page, pageSize, number, beginTime, endTime);
        return R.success(pageInfo);
    }

    /**
     * 订单派送，订单完成
     * 后端操作
     * @return
     */
    @PutMapping
    public R<Orders> status(@RequestBody Orders orders){
        Orders order = ordersService.status(orders);
        return R.success(order);
    }

    /**
     * 再来一单
     * @param orders 订单
     * @return
     */
    @PostMapping("/again")
    public R<String> againSubmit(@RequestBody Orders orders){
        ordersService.againSubmit(orders);
        return R.success("操作成功");
    }

}
