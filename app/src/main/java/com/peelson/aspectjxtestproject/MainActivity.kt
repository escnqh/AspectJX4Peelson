package com.peelson.aspectjxtestproject

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.peelson.aspextjx4peelson.aspectjxlog4peelson.D
import com.peelson.aspextjx4peelson.aspectjxlog4peelson.DebugLog
import com.peelson.aspextjx4peelson.aspectjxlog4peelson.debugValueLog
import com.peelson.aspextjx4peelson.aspectjxlog4peelson.shouldShowDebugLog
import com.peelson.aspextjx4peelson.test4aspectjx.PrintMessageClass

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        testFun()
    }


    @DebugLog("find me", D, true, true)
    fun testFun() {
        val s = "nqh"
        shouldShowDebugLog()?.debugValueLog("s", s.toString())
        val messagePrinter = PrintMessageClass()
        messagePrinter.printMessage(s)
    }
}
