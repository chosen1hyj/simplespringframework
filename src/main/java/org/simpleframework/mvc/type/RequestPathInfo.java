package org.simpleframework.mvc.type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 存储http请求路径和方法
 * @author: Chosen1
 * @date: 2022/2/7 20:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestPathInfo {

    //http请求方法
    private String httpMethod;
    //http请求路径
    private String httpPath;
}
