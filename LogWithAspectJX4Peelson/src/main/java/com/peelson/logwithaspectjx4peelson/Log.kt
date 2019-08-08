package com.peelson.logwithaspectjx4peelson

import androidx.annotation.IntDef

/**
 *  @Description
 *  @author peelson
 *  @date 2019/07/23
 */

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class Log


@IntDef(V, D, W, E)
@Retention(AnnotationRetention.SOURCE)
annotation class DebugLevel

const val V = 0x1
const val D = 0x2
const val W = 0x3
const val E = 0x4