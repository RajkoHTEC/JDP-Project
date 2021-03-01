package com.htec.jdp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState == null) {

            val bundle = bundleOf("INT" to 10, "FLOAT" to 1.5f, "STRING" to "some string")

            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add(R.id.fragment_container_view, ExampleFragment::class.java, bundle)
            }
        }
    }
}