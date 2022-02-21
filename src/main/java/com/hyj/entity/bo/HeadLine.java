package com.hyj.entity.bo;

import lombok.Data;

import java.util.Date;

/**
 * @description:
 * @author: Chosen1
 * @date: 2022/2/4 17:02
 */

@Data
public class HeadLine {

    private Long lineId;
    private String lineName;
    private String lineLink;
    private String lineImg;
    private Integer priority;
    private Integer enableStatus;
    private Date createTime;
    private Date lastEditTime;
}
