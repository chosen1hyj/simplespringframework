package com.hyj.entity.bo;

import lombok.Data;

import java.util.Date;

/**
 * @description:
 * @author: Chosen1
 * @date: 2021/10/4 17:02
 */

@Data
public class ShopCategory {

    private Long shopCategory;
    private String shopCategoryName;
    private String shopCategoryDesc;
    private String shopCategoryImg;
    private Integer priority;
    private Date createTime;
    private Date lastEditTime;
    private ShopCategory parent;
}
