package com.htec.jdp

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.lang.IllegalStateException

class ExampleListDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder
                    .setTitle("Choose your option")
                    .setItems( arrayOf("Option1", "Option2", "Option3"), DialogInterface.OnClickListener { dialog, which ->

                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}