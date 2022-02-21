package com.hyj.service.solo;

import com.hyj.entity.bo.ShopCategory;
import com.hyj.entity.dto.Result;

import java.util.List;

/**
 * @description:
 * @author: Chosen1
 * @date: 2022/2/4 17:09
 */
public interface ShopCategoryService {

    Result<Boolean> addShopCategory(ShopCategory shopCategory);
    Result<Boolean> removeShopCategory(int shopCategoryId);
    Result<Boolean> modifyShopCategory(ShopCategory shopCategory);
    Result<ShopCategory> queryShopCategoryById(int shopCategoryId);
    Result<List<ShopCategory>> queryShopCategory(ShopCategory shopCategoryCondition, int pageIndex, int pageSize);


}
