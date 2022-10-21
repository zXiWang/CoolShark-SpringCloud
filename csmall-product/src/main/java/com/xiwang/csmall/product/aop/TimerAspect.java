package com.xiwang.csmall.product.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TimerAspect {

    // 在AOP中，有多种Advice（通知）
    // @Around：包裹，可以实现在连接点之前和之后均自定义代码
    // @Before：在连接点之前执行
    // @After：在连接点之后执行，无论是正常返回还是抛出异常都会执行
    // @AfterReturning：在连接点返回之后执行
    // @AfterThrowing：在连接点抛出异常之后执行
    // 仅当使用@Around时，方法才可以自行处理ProceedingJoinPointer
    // 各Advice的执行大概是：
    // @Around
    // try {
    //   @Before
    //   连接点方法
    //   @AfterReturning
    // } catch (Throwable e) {
    //   @AfterThrowing
    // } finally {
    //   @After
    // }
    // @Around
    // ---------------------------------------------------
    // 关于ProceedingJoinPoint
    // 必须调用proceed()方法，表示执行表达式匹配到的方法
    // 调用proceed()方法必须获取返回值，且作为当前方法的返回值，表示返回表达式匹配的方法的返回值
    // 调用proceed()方法时的异常必须抛出，不可以使用try...catch进行捕获并处理
    // ---------------------------------------------------
    // 关于execution表达式：用于匹配在何时执行AOP相关代码
    // 表达式中的星号：匹配任意内容，只能匹配1次
    // 表达式中的2个连续的小数点：匹配任意内容，可以匹配0~n次，只能用于包名和参数列表部分
    // 表达式中的包是根包，会自动包含其子孙包中的匹配项

    @Around("execution(* com.xiwang.csmall.product.service.*.*(..))")
    //                 ↑ 无论方法的返回值类型是什么
    //                                                     ↑ 无论是哪个类
    //                                                       ↑ 无论是哪个方法
    //                                                          ↑ 2个小数点表示任何参数列表
    public Object timer(ProceedingJoinPoint pjp) throws Throwable {
        log.debug("执行了TimerAspect中的方法……");


        long start = System.currentTimeMillis();
        Object result = pjp.proceed(); // 执行连接点方法，获取返回结果
        long end = System.currentTimeMillis();

        log.debug("【{}】类型的对象调用了【{}】方法，方法的参数值为【{}】",
                pjp.getTarget().getClass().getName(),
                pjp.getSignature().getName(),
                pjp.getArgs());
        log.debug("执行耗时：{}毫秒", end - start);

        return result; // 返回调用pjp.proceed()时的结果
    }
}
