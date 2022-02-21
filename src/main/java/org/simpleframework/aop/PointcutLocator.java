package org.simpleframework.aop;

import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.ShadowMatch;

import java.lang.reflect.Method;

/**
 * @description: 解析Aspect表达式并且定位被织入的目标
 * @author: Chosen1
 * @date: 2022/2/7 15:36
 */
public class PointcutLocator {

    /**
     * pointcut解析器，直接给他赋上Aspectj的所有表达式，以便支持对众多表达式的支持
     */
    private PointcutParser pointcutParser = PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingContextClassloaderForResolution(
            PointcutParser.getAllSupportedPointcutPrimitives()
    );
    /**
     * 表达式解析器
     */
    private PointcutExpression pointcutExpression;

    public PointcutLocator(String expression){
        this.pointcutExpression = pointcutParser.parsePointcutExpression(expression);
    }

    public boolean roughMatches(Class<?> targetClass){
        //只能校验within
        //不能校验(execution, call, get, set),直接会返回true
        return pointcutExpression.couldMatchJoinPointsInType(targetClass);
    }

    public boolean accurateMatches(Method method){

        ShadowMatch shadowMatch = pointcutExpression.matchesMethodExecution(method);
        return shadowMatch.alwaysMatches();

    }

}
