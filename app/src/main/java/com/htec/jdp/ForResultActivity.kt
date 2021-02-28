package com.htec.jdp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

private const val TAG = "ForResultActivity"

class AActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_for_result)

        if(intent.extras != null)
        {
            Log.d(TAG, "onCreate: ${intent?.extras?.keySet().toString()}")
            with(intent.extras) {
                this?.keySet()?.forEach {
                    Log.d(TAG, "[${it}] -> ${this[it].toString()}")
                }
            }
            Log.d(TAG, "onCreate: ")

            val parcelizeTest = intent?.extras?.get("PARCEL") as ParcelizeTest?
            Log.d(TAG, "onCreate: Parcel value1: ${parcelizeTest?.test1} value2: ${parcelizeTest?.test2}")
        }

        findViewById<Button>(R.id.btnReturnCancel).setOnClickListener {
            val returnIntent = Intent()
            setResult(RESULT_CANCELED, returnIntent)
            finish()
        }

        findViewById<Button>(R.id.btnReturnOk).setOnClickListener {
            val returnIntent = Intent()
            returnIntent.putExtra("result", intent?.extras?.getString("NAME"))
            setResult(RESULT_OK, returnIntent)
            finish()
        }
    }
}