package com.hyj.service.solo.impl;

import com.hyj.entity.bo.ShopCategory;
import com.hyj.entity.dto.Result;
import com.hyj.service.solo.ShopCategoryService;
import org.simpleframework.core.annotation.Service;

import java.util.List;

/**
 * @description:
 * @author: Chosen1
 * @date: 2022/2/4 17:16
 */

@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {
    @Override
    public Result<Boolean> addShopCategory(ShopCategory shopCategory) {
        return null;
    }

    @Override
    public Result<Boolean> removeShopCategory(int shopCategoryId) {
        return null;
    }

    @Override
    public Result<Boolean> modifyShopCategory(ShopCategory shopCategory) {
        return null;
    }

    @Override
    public Result<ShopCategory> queryShopCategoryById(int shopCategoryId) {
        return null;
    }

    @Override
    public Result<List<ShopCategory>> queryShopCategory(ShopCategory shopCategoryCondition, int pageIndex, int pageSize) {
        return null;
    }
}
