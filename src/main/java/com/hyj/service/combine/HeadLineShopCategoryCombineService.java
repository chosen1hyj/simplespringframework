package com.hyj.service.combine;

import com.hyj.entity.dto.MainPageInfoDTO;
import com.hyj.entity.dto.Result;

/**
 * @description:
 * @author: Chosen1
 * @date: 2022/2/4 17:18
 */
public interface HeadLineShopCategoryCombineService {
    Result<MainPageInfoDTO> getMainPageInfo();
}
