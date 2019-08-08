package com.peelson.logwithaspectjx4peelson

import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut

/**
 *  @Description
 *  @author peelson
 *  @date 2019/07/24
 */

@Aspect
class TestAspect {

    @Pointcut("call(* ccom.peelson.logwithaspectjx4peelson.TestClass.testfun(String))")
    fun testfun() {
        print("before test function")
    }

    @Before("testfun()")
    fun beforeTestFun() {

    }
}