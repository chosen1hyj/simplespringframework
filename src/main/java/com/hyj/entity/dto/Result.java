package com.hyj.entity.dto;

import lombok.Data;

/**
 * @description:
 * @author: Chosen1
 * @date: 2021/10/4 17:08
 */

@Data
public class Result<T> {

    private int code;
    private String msg;
    private T data;
}
