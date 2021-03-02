package com.htec.jdp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {
    private var randomNumber : Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       if(savedInstanceState != null) {
            with(savedInstanceState) {
                randomNumber = getInt(STATE_RANDOM_NUMBER)
            }
        } else {
           randomNumber = Random().nextInt()
        }
        findViewById<TextView>(R.id.tvRandomNumber).text = "Random number: $randomNumber"
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

       /*savedInstanceState.run {
           randomNumber = getInt(STATE_RANDOM_NUMBER)
       }*/
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(STATE_RANDOM_NUMBER, randomNumber)
        super.onSaveInstanceState(outState)
    }

    companion object {
        const val STATE_RANDOM_NUMBER = "randomNumber"
    }
}