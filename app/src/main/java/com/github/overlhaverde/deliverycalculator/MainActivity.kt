package com.github.overlhaverde.deliverycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.ovelhaverde.deliverycalculator.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_App)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}