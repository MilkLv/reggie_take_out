package com.strive.reggie.dto;

import com.strive.reggie.entity.Setmeal;
import com.strive.reggie.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
