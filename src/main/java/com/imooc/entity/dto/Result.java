package com.imooc.entity.dto;

import lombok.Data;

/**
 * @description:
 * @author: Chosen1
 * @date: 2022/2/4 17:08
 */

@Data
public class Result<T> {

    private int code;
    private String msg;
    private T data;
}
