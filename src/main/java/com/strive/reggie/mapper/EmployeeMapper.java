package com.strive.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.strive.reggie.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lzp moonlight
 * @create 2022-12-01 21:39
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
