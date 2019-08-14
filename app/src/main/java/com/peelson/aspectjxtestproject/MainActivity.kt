package com.peelson.aspectjxtestproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.peelson.aspextjx4peelson.aspectjxlog4peelson.DebugLog
import com.peelson.aspextjx4peelson.test4aspectjx.PrintMessageClass

class MainActivity : AppCompatActivity() {

    @DebugLog()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        testFun()
    }

    @DebugLog()
    override fun onPause() {
        super.onPause()
    }

    @DebugLog()
    override fun onRestart() {
        super.onRestart()
    }

    @DebugLog()
    override fun onResume() {
        super.onResume()
    }

    @DebugLog()
    override fun onDestroy() {
        super.onDestroy()
    }

    @DebugLog()
    fun testFun() {
        val messagePrinter = PrintMessageClass()
        messagePrinter.printMessage("nqh")
    }
}
