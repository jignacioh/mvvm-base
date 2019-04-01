package com.arch.core.arquetype.live_data

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.arch.core.arquetype.R
import kotlinx.android.synthetic.main.activity_live_data.*
import kotlin.random.Random

class LiveDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)

        val colorChangerViewModel = ViewModelProviders.of(this).get(ColorChangerViewModel::class.java)
        colorChangerViewModel.colorResource.observe(this, object : Observer<Int> {
            override fun onChanged(t: Int?) {
                mainContent.setBackgroundColor(t!!)
            }
        })

        btnChangeColor.setOnClickListener {
            //mainContent.setBackgroundColor(generateRandomcolor())
            colorChangerViewModel.colorResource.value = generateRandomcolor()
        }
    }

    fun generateRandomcolor() : Int{
        val rdn = Random
        return Color.argb(255, rdn.nextInt(), rdn.nextInt(), rdn.nextInt())
    }
}
