package com.hang.common.aspect;


import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * @Aspect测试AOP面向切面
 * 从Spring 2.0开始，可以使用基于schema及@AspectJ的方式来实现AOP
 * 本文以一个简单的实例介绍了如何以@AspectJ方式在Spring中实现AOP
 * 由于@Aspect是基于注解的，因此要求支持注解的5.0版本以上的JDK
 */
@Aspect
@Component //把普通pojo实例化到spring容器中
public class HttpAspect {

    //Logger是Spring自带的一个日志框架
    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    /*
    * 定义一个公共方法 log()
    * @Pointcut 是指那些方法需要被执行"AOP"
    * 例子：
        1）execution(* *(..))
        表示匹配所有方法
        2）execution(public * com. savage.service.UserService.*(..))
        表示匹配com.savage.server.UserService中所有的公有方法
        3）execution(* com.savage.server..*.*(..))
        表示匹配com.savage.server包及其子包下的所有方法
    * 以下 只匹配 com.hang.index.controller.IndexController类中的所有方法
    * */
    @Pointcut("execution(public * com.hang.admin.controller..*.*(..))")
    public void admincheck(){
    }

    /*
    * @Before 在...之前...
    * 在运行Pointcut切点中类 之前 会调用该方法
    * @param JoinPoint 得到被执行的类。方法等  JoinPoint 记得先 import org.aspectj.lang.JoinPoint;
    * */
    @Before("admincheck()")
    public void doBefore() throws Throwable {

    }

    /*
    * @After 在...之后...
    * 在运行Pointcut切点中类 之后 会调用该方法
    * */
    @After("admincheck()")
    public void  doAfter(){
        //logger.info("After");
    }

    /*
    * 在类方法执行后打印类方法return的内容
    * 注：AfterReturning和before，after，around不同是可以获取植入函数的返回值
    * */
    /*@AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object){
        logger.info("response={}",object.toString());
    }*/






}
