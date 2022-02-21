package com.hyj.service.solo;

import com.hyj.entity.bo.HeadLine;
import com.hyj.entity.dto.Result;

import java.util.List;

/**
 * @description:
 * @author: Chosen1
 * @date: 2021/10/4 17:10
 */
public interface HeadLineService {

    Result<Boolean> addHeadLine(HeadLine headLine);
    Result<Boolean> removeHeadLine(int headLineId);
    Result<Boolean> modifyHeadLine(HeadLine headLine);
    Result<HeadLine> queryHeadLineById(int headLineId);
    Result<List<HeadLine>> queryHeadLine(HeadLine headLineCondition, int pageIndex, int pageSize);

}
