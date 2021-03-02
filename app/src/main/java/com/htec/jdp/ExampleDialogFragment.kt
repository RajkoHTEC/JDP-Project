package com.htec.jdp

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.lang.IllegalStateException

class ExampleDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder
                    .setMessage("This is dialog fragment example")
                    .setPositiveButton("Yes", { dialog, id ->
                    })
                    .setNegativeButton("No", { dialog, id ->

                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}