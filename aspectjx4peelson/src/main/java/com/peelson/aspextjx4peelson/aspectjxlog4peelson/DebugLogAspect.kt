package com.peelson.aspextjx4peelson.aspectjxlog4peelson

import android.os.Build
import android.os.Looper
import android.os.Trace
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.CodeSignature
import org.aspectj.lang.reflect.MethodSignature
import java.util.concurrent.TimeUnit

/**
 *  @Description 切入注解了[DebugLog]的方法，打印日志信息
 *  @author peelson
 *  @date 2019/08/09
 */

@Aspect
class DebugLogAspect {

    @Pointcut("within(@com.peelson.aspextjx4peelson.aspectjxlog4peelson.DebugLog *)")
    fun withinAnnotatedClass() {
    }

    @Pointcut("execution(!synthetic * *(..)) && withinAnnotatedClass()")
    fun methodInsideAnnotatedType() {
    }

    @Pointcut("execution(!synthetic *.new(..)) && withinAnnotatedClass()")
    fun constructorInsideAnnotatedType() {
    }

    @Pointcut("execution(@com.peelson.aspextjx4peelson.aspectjxlog4peelson.DebugLog * *(..)) || methodInsideAnnotatedType()")
    fun method() {
    }

    @Pointcut("execution(@com.peelson.aspextjx4peelson.aspectjxlog4peelson.DebugLog * *(..)) || constructorInsideAnnotatedType()")
    fun constructor() {
    }

    /**
     *
     */
    @Around("(method() || constructor()) && @annotation(debugLog)")
    @Throws(Throwable::class)
    fun logAndExecute(joinPoint: ProceedingJoinPoint, debugLog: DebugLog): Any? {
        if (DebugLogConfig.showLog && debugLog.showLog && debugLog.logLevel >= DebugLogConfig.defaultShowLevel) {
            enterMethod(joinPoint, debugLog)
            val showSpendTime = DebugLogConfig.showSpendTime && debugLog.showSpendTime
            val startNanos = if (showSpendTime) System.nanoTime() else 0
            val result = joinPoint.proceed()
            val stopNanos = if (showSpendTime) System.nanoTime() else 0
            val lengthMillis = if (showSpendTime) TimeUnit.NANOSECONDS.toMillis(stopNanos - startNanos) else 0
            exitMethod(joinPoint, debugLog, result, lengthMillis, showSpendTime)
            return result
        } else return joinPoint.proceed()
    }

    /**
     * 切入方法
     */
    fun enterMethod(joinPoint: JoinPoint, debugLog: DebugLog) {
        val codeSignature = joinPoint.signature as CodeSignature
        val methodName = codeSignature.name
        val parameterNames = codeSignature.parameterNames
        val parameterValues = joinPoint.args
        val stringBuilder = getLogInfo(methodName, parameterNames, parameterValues, debugLog)
        realLog(debugLog.logLevel, stringBuilder.toString())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            val section = stringBuilder.toString().substring(2)
            Trace.beginSection(section)
        }
    }

    /**
     * 切出方法
     */
    fun exitMethod(joinPoint: JoinPoint, debugLog: DebugLog, result: Any?, lengthMillis: Long, showSpendTime: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            Trace.endSection()
        }
        val methodName = joinPoint.signature.name.plus("()")
        val hasReturnType =
            joinPoint.signature is MethodSignature && (joinPoint.signature as MethodSignature).returnType != Void.TYPE
        val stringBuilder = StringBuilder("<------- ")
        if (debugLog.tag != "") stringBuilder.append(debugLog.tag.plus(": "))
        stringBuilder.append(methodName)
        if (showSpendTime) {
            stringBuilder.append(" [")
                .append(lengthMillis)
                .append("ms]")
        }
        if (hasReturnType) {
            stringBuilder.append("=").append(result)
        }
        realLog(debugLog.logLevel, stringBuilder.toString())
    }

    /**
     * 拼接日志信息
     */
    fun getLogInfo(
        methodName: String,
        parameterNames: Array<String>,
        parameterValues: Array<Any>,
        debugLog: DebugLog
    ): StringBuilder {
        val stringBuilder = StringBuilder("-------> ")
        if (debugLog.tag != "") stringBuilder.append(debugLog.tag.plus(": "))
        stringBuilder.append(methodName).append('(')
        val index = parameterNames.size
        for (i in 0 until index) {
            if (i > 0) stringBuilder.append(", ")
            stringBuilder.append(parameterNames[i]).append('=').append(parameterValues[i])
        }
        stringBuilder.append(')')
        if (Looper.myLooper() != Looper.getMainLooper()) {
            stringBuilder.append(" [Thread:\"").append(Thread.currentThread().name).append("\"]")
        }
        return stringBuilder
    }
}