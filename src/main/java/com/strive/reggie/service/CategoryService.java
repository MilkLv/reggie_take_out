package com.strive.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.strive.reggie.entity.Category;

/**
 * @author lzp moonlight
 * @create 2022-12-03 21:01
 */

public interface CategoryService extends IService<Category> {

    /**
     * 删除
     *
     * @param id id
     */
    void remove(Long id);
}
