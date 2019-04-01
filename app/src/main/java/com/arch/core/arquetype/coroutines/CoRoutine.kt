package com.arch.core.arquetype.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.arch.core.arquetype.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicLong
import kotlin.concurrent.thread

class CoRoutine : AppCompatActivity(){

    val TAG = "Coroutines"
    lateinit var a : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_co_routine)

        a = findViewById(R.id.tvText)
        a.text = "Hello Var"

        Log.i(TAG, "Start CoRoutine !!!!")
/*
        GlobalScope.launch {
            delay(2000)
            Log.i(TAG, "Hello CoRoutine !!")
        }
*/
        /*val c = AtomicLong()
        for (i in 1..1_000_000L){
            thread (start = true) {
                c.addAndGet(i)
            }
            Log.i("Coroutine_"+i, c.get().toString())
        }*/
/*
        for (i in 0..9){
            Thread.sleep(1000)
            a.text = i.toString()
        }*/

        val deferred = (0..2).map { n ->
            GlobalScope.async {

                a.text = n.toString()
                Thread.sleep(3000)
                Log.i(TAG, n.toString())
            }
        }

        Thread.sleep(1000)
        Log.i(TAG, "Finish CoRoutine !!!!")
    }
}
