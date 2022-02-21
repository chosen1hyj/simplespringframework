package org.simpleframework.aop.aspect;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.simpleframework.aop.PointcutLocator;

/**
 * @description:
 * @author: Chosen1
 * @date: 2022/2/6 20:05
 */
@AllArgsConstructor
@Getter
public class AspectInfo {

    private int orderIndex;
    private DefaultAspect aspectObject;
    private PointcutLocator pointcutLocator;
}
