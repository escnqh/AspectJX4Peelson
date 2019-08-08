package com.peelson.aspextjx4peelson

import android.util.Log
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.*

/**
 *  @Description
 *  @author peelson
 *  @date 2019/08/08
 */
@Aspect
class MessageAspect {
    private val TAG = "MessageAspect"

    @Pointcut("call(* com.peelson.aspextjx4peelson.PrintMessageClass.printMessage(String))")
    fun cutPrintMessage() {
        Log.i(TAG, "cut function printMessage()")
    }

    @Before("cutPrintMessage()")
    fun beforePrintMessage() {
        Log.i(TAG, "before cut function printMessage()")
    }

    @Around("cutPrintMessage()")
    fun aroundPrintMessage(joinPoint: ProceedingJoinPoint) {
        Log.i(TAG, "around cut function printMessage()")
        joinPoint.proceed()
    }

    @After("cutPrintMessage()")
    fun afterPrintMessage() {
        Log.i(TAG, "after cut function printMessage()")
    }
}