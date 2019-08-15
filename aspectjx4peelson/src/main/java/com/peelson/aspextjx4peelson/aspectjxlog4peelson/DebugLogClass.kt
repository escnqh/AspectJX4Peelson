package com.peelson.aspextjx4peelson.aspectjxlog4peelson

import android.icu.lang.UCharacter.GraphemeClusterBreak.V
import android.support.annotation.IntDef
import android.util.Log
import com.peelson.aspextjx4peelson.aspectjxlog4peelson.DebugLogConfig.GLOBAL_TAG


/**
 *  @Description 注解@DebugLog及其他方法
 *  @author peelson
 *  @date 2019/08/09
 */

@Target(AnnotationTarget.TYPE, AnnotationTarget.FUNCTION, AnnotationTarget.CONSTRUCTOR)
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class DebugLog(
    // 单个注解粒度配置
    // 补充日志信息（再次过滤）
    val tag: String = "",
    // 当前日志级别
    @LogLevel val logLevel: Int = D,
    // 是否显示当前日志
    val showLog: Boolean = true,
    // 是否打印当前耗时
    val showSpendTime: Boolean = true
)

/**
 * Debug log level
 */
const val I = 0
const val V = 1
const val D = 2
const val W = 3
const val E = 4

@IntDef(I, V, D, W, E)
@Retention(AnnotationRetention.SOURCE)
annotation class LogLevel

/**
 * 单个对象打印值日志，受全局配置影响，Any的扩展函数，可以在任意地方调用
 */
fun Any.debugValueLog(name: String, value: String, @LogLevel level: Int = D) {
    if (DebugLogConfig.showLog) {
        realLog(level, "$name = $value")
    }
}

/**
 * 先调用此方法可以短路打印日志，节省性能
 */
fun Any.shouldShowDebugLog(): Any? {
    return if (DebugLogConfig.showLog) this else null
}

/**
 * 真实地在打Log
 */
fun realLog(@LogLevel level: Int = D, message: String) {
    when (level) {
        I -> {
            Log.i(GLOBAL_TAG, message)
        }
        V -> {
            Log.v(GLOBAL_TAG, message)
        }
        D -> {
            Log.d(GLOBAL_TAG, message)
        }
        W -> {
            Log.w(GLOBAL_TAG, message)
        }
        E -> {
            Log.e(GLOBAL_TAG, message)
        }
    }
}

