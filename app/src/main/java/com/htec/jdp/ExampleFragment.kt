package com.htec.jdp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

class ExampleFragment : Fragment(R.layout.example_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val someInt = requireArguments().getInt("INT")
        val someFloat = requireArguments().getFloat("FLOAT")
        val someString = requireArguments().getString("STRING")
    }
}