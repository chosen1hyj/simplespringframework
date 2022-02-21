package com.hyj.service.combine;

import com.hyj.entity.dto.MainPageInfoDTO;
import com.hyj.entity.dto.Result;

/**
 * @description:
 * @author: Chosen1
 * @date: 2021/10/4 17:18
 */
public interface HeadLineShopCategoryCombineService {
    Result<MainPageInfoDTO> getMainPageInfo();
}
