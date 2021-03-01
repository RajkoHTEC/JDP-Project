package com.htec.jdp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle.State.*

private const val TAG = "ExampleFragment"

open class ExampleFragment(private val someDependency : SomeDependency) : Fragment(R.layout.example_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val test = someDependency.data

        val someInt = requireArguments().getInt("INT")
        val someFloat = requireArguments().getFloat("FLOAT")
        val someString = requireArguments().getString("STRING")

        view.findViewById<TextView>(R.id.textView).text = someString
        Log.d(TAG, "onViewCreated: ")

        if(lifecycle.currentState == CREATED) {

        }
        if(viewLifecycleOwner.lifecycle.currentState == CREATED) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }
}