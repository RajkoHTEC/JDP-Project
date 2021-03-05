package com.htec.jdp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import java.lang.ClassCastException

class CustomDialogFragment : DialogFragment() {
    private lateinit var editUsername: EditText
    private lateinit var editPassword: EditText
    private lateinit var buttonCancel: Button
    private lateinit var buttonSubmit: Button
    private lateinit var listener: IDialogResponse

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as IDialogResponse
        } catch (e : ClassCastException) {
            throw ClassCastException(context.toString() + "must implement IDialogResponse")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       return inflater.inflate(R.layout.login_dialog, null).also {
           editUsername = it.findViewById(R.id.editUsername)
           editPassword = it.findViewById(R.id.editPassword)
           buttonCancel = it.findViewById(R.id.btnCancel)
           buttonSubmit = it.findViewById(R.id.btnLogin)

           buttonCancel.setOnClickListener {
               this.dismiss()
               listener.onDialogCancel()
           }

           buttonSubmit.setOnClickListener {
               if(editUsername.text.isEmpty() || editPassword.text.isEmpty()) {
                   Toast.makeText(context?.applicationContext, "Enter fields", Toast.LENGTH_SHORT).show()
                   return@setOnClickListener
               }
               this.dismiss()
               listener.onDialogSubmit(editUsername.text.toString(), editPassword.text.toString())
           }
       }
    }
}