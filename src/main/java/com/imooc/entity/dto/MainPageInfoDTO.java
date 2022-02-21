package com.imooc.entity.dto;

import com.imooc.entity.bo.HeadLine;
import com.imooc.entity.bo.ShopCategory;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: Chosen1
 * @date: 2022/2/4 17:16
 */
@Data
public class MainPageInfoDTO {

    private List<HeadLine> headLineList;
    private List<ShopCategory> shopCategoryList;

}
