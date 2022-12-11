package com.strive.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.strive.reggie.common.BaseContext;
import com.strive.reggie.common.CustomException;
import com.strive.reggie.entity.*;
import com.strive.reggie.service.*;
import com.strive.reggie.mapper.OrdersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
* @author niwei
* @description 针对表【orders(订单表)】的数据库操作Service实现
* @create 2022-12-07 22:42:25
*/
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders>
    implements OrdersService{

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private UserService userService;

    @Autowired
    private AddressBookService addressBookService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void submit(Orders orders) {
        //获得当前用户的id
        Long userId = BaseContext.getCurrentId();
        //查询当前用户的购物车数据
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,userId);
        List<ShoppingCart> shoppingCarts = shoppingCartService.list(queryWrapper);
        if(shoppingCarts == null ||shoppingCarts.size() == 0){
            throw new CustomException("购物车为空不能下单");
        }
        //查询用户数据
        User user = userService.getById(userId);
        //查询地址数据
        Long addressBookId = orders.getAddressBookId();
        AddressBook addressBook = addressBookService.getById(addressBookId);
        if(addressBook == null ){
            throw new CustomException("地址信息有误，不能下单");
        }
        //订单号
        long orderId = IdWorker.getId();
        //原子操作，保证多线程操作计算安全
        AtomicInteger amount = new AtomicInteger(0);
        List<OrderDetail> orderDetails= shoppingCarts.stream().map((item)->{
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(orderId);
            orderDetail.setNumber(item.getNumber());
            orderDetail.setDishFlavor(item.getDishFlavor());
            orderDetail.setDishId(item.getDishId());
            orderDetail.setSetmealId(item.getSetmealId());
            orderDetail.setName(item.getName());
            orderDetail.setImage(item.getImage());
            orderDetail.setAmount(item.getAmount());
            //addAndGet累加  multiply：乘
            amount.addAndGet(item.getAmount().multiply(new BigDecimal(item.getNumber())).intValue());
            return orderDetail;
        }).collect(Collectors.toList());


        orders.setId(orderId);
        orders.setOrderTime(LocalDateTime.now());
        orders.setCheckoutTime(LocalDateTime.now());
        orders.setStatus(2);
        //总金额
        orders.setAmount(new BigDecimal(amount.get()));
        orders.setUserId(userId);
        orders.setNumber(String.valueOf(orderId));
        orders.setUserName(user.getName());
        orders.setConsignee(addressBook.getConsignee());
        orders.setPhone(addressBook.getPhone());
        orders.setAddress((addressBook.getProvinceName() == null ? "" : addressBook.getProvinceName())
                + (addressBook.getCityName() == null ? "" : addressBook.getCityName())
                + (addressBook.getDistrictName() == null ? "" : addressBook.getDistrictName())
                + (addressBook.getDetail() == null ? "" : addressBook.getDetail()));

        //向订单表插入数据，一条数据
        this.save(orders);
        //向订单明细表插入数据，多条数据 saveBatch:批量插入
        orderDetailService.saveBatch(orderDetails);

        //清空购物车
        shoppingCartService.remove(queryWrapper);
    }

    /**
     * 后端管理订单页面
     * @param page      页面
     * @param pageSize  页面大小
     * @param number    数量
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    @Override
    public Page<Orders> page(int page, int pageSize, Long number, String beginTime, String endTime) {
        Page<Orders> pageInfo = new Page<>(page,pageSize);
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        if (number!=null){
            queryWrapper.like(Orders::getNumber,number);
        }
        if (beginTime!=null){
            queryWrapper.ge(Orders::getOrderTime,beginTime);
        }
        if (endTime!=null){
            queryWrapper.le(Orders::getOrderTime,endTime);
        }
        queryWrapper.orderByDesc(Orders::getOrderTime);
        this.page(pageInfo,queryWrapper);
        return pageInfo;
    }

    /**
     * 订单派送以及完成状态更新
     * @param orders 订单
     */
    @Override
    public Orders status(Orders orders) {
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Orders::getId,orders.getId());
        Orders one = this.getOne(queryWrapper);
        one.setStatus(orders.getStatus());
        this.updateById(one);
        return one;
    }

    /**
     * 再来一单功能
     * @param orders 订单
     */
    @Override
    public void againSubmit(Orders orders) {
        LambdaQueryWrapper<OrderDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderDetail::getOrderId,orders.getId());

        //获取订单中所有的菜品
        List<OrderDetail> orderDetails = orderDetailService.list(queryWrapper);
        //把原来地购物车清空
        shoppingCartService.clean();
        //获取用户id
        Long userId = BaseContext.getCurrentId();

        //因为菜品详细表和购物车表内容很像，所有容易相互赋值
       List<ShoppingCart> shoppingCartList= orderDetails.stream().map((item)->{
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setUserId(userId);
            shoppingCart.setCreateTime(LocalDateTime.now());
            Long dishId = item.getDishId();
            if (dishId!=null){
                //菜品
                shoppingCart.setDishId(dishId);
            }else {
                //套餐
                shoppingCart.setSetmealId(item.getSetmealId());
            }
            shoppingCart.setImage(item.getImage());
            shoppingCart.setName(item.getName());
            shoppingCart.setDishFlavor(item.getDishFlavor());
            shoppingCart.setNumber(item.getNumber());
            shoppingCart.setAmount(item.getAmount());
            return shoppingCart;
        }).collect(Collectors.toList());

        //把携带数据的购物车批量插入购物车表
        shoppingCartService.saveBatch(shoppingCartList);

    }


}




