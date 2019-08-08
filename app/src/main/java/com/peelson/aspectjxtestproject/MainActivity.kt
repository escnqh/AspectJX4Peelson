package com.peelson.aspectjxtestproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.peelson.aspextjx4peelson.PrintMessageClass

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        testFun()
    }

    fun testFun() {
        val messagePrinter = PrintMessageClass()
        messagePrinter.printMessage("nqh")
    }
}
