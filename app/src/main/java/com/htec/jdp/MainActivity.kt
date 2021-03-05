package com.htec.jdp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), IDialogResponse{
    private lateinit var linearLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearLayout = findViewById(R.id.linearLayout)

        findViewById<Button>(R.id.btnShowToast).setOnClickListener { testToast() }
        findViewById<Button>(R.id.btnShowSnackBar).setOnClickListener { testSnackBar()}
        findViewById<Button>(R.id.btnShowAlertDialog).setOnClickListener { showAlertDialog() }
        findViewById<Button>(R.id.btnShowDialogFragment).setOnClickListener { ExampleDialogFragment().show(supportFragmentManager, "")}
        findViewById<Button>(R.id.btnShowDialogFragmentWithList).setOnClickListener { ExampleListDialogFragment().show(supportFragmentManager, "") }
        findViewById<Button>(R.id.btnShowBottomSheetDialog).setOnClickListener { showOptionsBottomSheetDialog() }
        findViewById<Button>(R.id.btnShowCustomDialog).setOnClickListener { showCustomDialog()}
        findViewById<Button>(R.id.btnShowCustomDialogFragment).setOnClickListener{ showCustomDialogFragment()}
    }

    private fun showCustomDialogFragment() {
        supportFragmentManager.let {
            CustomDialogFragment().show(it, "")
        }
    }

    private fun showCustomDialog() {
        CustomDialog(this).show()
    }

    private fun showOptionsBottomSheetDialog() {
        supportFragmentManager.let {
            OptionsBottomSheetFragment.newInstance(Bundle()).apply {
                show(it, tag)
            }
        }
    }

    private fun showAlertDialog() {
        AlertDialog.Builder(this)
                .setTitle("Stand-alone Alert dialog")
                .setMessage("Testing dialog without DialogFragment")
                .create().show()
    }

    private fun testSnackBar() {
        Snackbar.make(linearLayout, "Showing snackbar", Snackbar.LENGTH_LONG).setAction("UNDO", View.OnClickListener {  }).show()
    }

    private fun testToast() {
        Toast.makeText(applicationContext, "Hello from toast!", Toast.LENGTH_SHORT).show()
      /*  val inflater = layoutInflater
        val container : ViewGroup = findViewById(R.id.custom_toast_container)
        val layout : View = inflater.inflate(R.layout.custom_toast, container)
        val text: TextView = layout.findViewById(R.id.text)
        text.text = "This is a custom toast"
        with(Toast(applicationContext)) {
            setGravity(Gravity.CENTER_VERTICAL, 0, 0)
            duration = Toast.LENGTH_LONG
            view = layout
            show()
        }*/
    }

    override fun onDialogSubmit(username: String, password: String) {
        if(username == "milan" && password == "123") {
            Toast.makeText(applicationContext, "Login Succ", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(applicationContext, "Login fail", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDialogCancel() {

    }
}