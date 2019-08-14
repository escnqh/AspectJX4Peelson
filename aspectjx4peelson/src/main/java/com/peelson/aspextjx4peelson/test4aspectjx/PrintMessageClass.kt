package com.peelson.aspextjx4peelson.test4aspectjx

import android.util.Log

/**
 *  @Description
 *  @author peelson
 *  @date 2019/08/08
 */
class PrintMessageClass {
    private val TAG = "PrintMessageClass"
    fun printMessage(s: String) {
        Log.i(TAG, s)
    }
}