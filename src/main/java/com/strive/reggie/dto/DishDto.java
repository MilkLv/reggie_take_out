package com.strive.reggie.dto;

import com.strive.reggie.entity.Dish;
import com.strive.reggie.entity.DishFlavor;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据传输对象
 * @author lv
 */
@Data
public class DishDto extends Dish {

    /**
     * 菜品所对应的口味数据
     */
    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
