package com.peelson.aspextjx4peelson.aspectjxlog4peelson

import android.icu.lang.UCharacter.GraphemeClusterBreak.V
import androidx.annotation.IntDef


/**
 *  @Description
 *  @author peelson
 *  @date 2019/08/09
 */

@Target(AnnotationTarget.TYPE, AnnotationTarget.FUNCTION, AnnotationTarget.CONSTRUCTOR)
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class DebugLog(
    val showLog: Boolean = true,
    @LogLevel val logLevel: Int = D,
    val showSpendTime: Boolean = true,
    val tag: String = "",
    val message: String = ""
)

/**
 * Debug log level
 */
const val V = 0
const val D = 1
const val W = 2
const val E = 3

@IntDef(V, D, W, E)
@Retention(AnnotationRetention.SOURCE)
annotation class LogLevel

@DebugLog(true, D, false)
fun Any.ValueLog() {
}

