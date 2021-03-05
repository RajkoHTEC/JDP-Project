package com.htec.jdp

import android.app.Dialog
import android.content.Context
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class CustomDialog(context: Context): Dialog(context) {
    private var editUsername: EditText
    private var editPassword: EditText
    private var buttonCancel: Button
    private var buttonSubmit: Button

    private val listener = context as IDialogResponse

    init {
        setContentView(R.layout.login_dialog)
        editUsername = findViewById(R.id.editUsername)
        editPassword = findViewById(R.id.editPassword)
        buttonCancel = findViewById(R.id.btnCancel)
        buttonSubmit = findViewById(R.id.btnLogin)

        buttonCancel.setOnClickListener {
            this.dismiss()
            listener.onDialogCancel()
        }

        buttonSubmit.setOnClickListener {
            if(editUsername.text.isEmpty() || editPassword.text.isEmpty()) {
                Toast.makeText(context.applicationContext, "Enter fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            this.dismiss()
            listener.onDialogSubmit(editUsername.text.toString(), editPassword.text.toString())
        }
    }
}