package com.peelson.logwithaspectjx4peelson

import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut

/**
 *  @Description
 *  @author peelson
 *  @date 2019/07/23
 */
@Aspect
class LogAspect{

    @Pointcut("call(com.peelson.aspectjxtestproject.MainActivity.testFun(..))")
    fun logPrinter(){

    }

    @Around("logPrinter()")
    @Throws(Throwable::class)
    fun aroundPrint(){
        print("\n\n\nit's here!")
    }
}