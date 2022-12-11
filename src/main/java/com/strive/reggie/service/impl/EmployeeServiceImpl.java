package com.strive.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.strive.reggie.entity.Employee;
import com.strive.reggie.mapper.EmployeeMapper;
import com.strive.reggie.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * @author lzp moonlight
 * @create 2022-12-01 21:41
 */
@Service
public class EmployeeServiceImpl  extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService{
}
