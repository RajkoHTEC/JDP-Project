package com.htec.jdp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

class ExampleFragment(val someDependency : SomeDependency) : Fragment(R.layout.example_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val test = someDependency.data

        val someInt = requireArguments().getInt("INT")
        val someFloat = requireArguments().getFloat("FLOAT")
        val someString = requireArguments().getString("STRING")
    }
}