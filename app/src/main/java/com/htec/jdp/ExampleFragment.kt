package com.htec.jdp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment

class ExampleFragment : Fragment(R.layout.example_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       val name = requireArguments().getString(TYPE_ARG_KEY)
        view.findViewById<TextView>(R.id.textView).text = name
    }

    companion object {
        const val TYPE_ARG_KEY = "Type"
    }
}