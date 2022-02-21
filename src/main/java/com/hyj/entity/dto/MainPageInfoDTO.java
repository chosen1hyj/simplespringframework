package com.hyj.entity.dto;

import com.hyj.entity.bo.HeadLine;
import com.hyj.entity.bo.ShopCategory;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: Chosen1
 * @date: 2021/10/4 17:16
 */
@Data
public class MainPageInfoDTO {

    private List<HeadLine> headLineList;
    private List<ShopCategory> shopCategoryList;

}
