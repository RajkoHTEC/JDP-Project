package com.htec.jdp

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnTestCase1).setOnClickListener(testCase1())
        findViewById<Button>(R.id.btnTestCase2).setOnClickListener(testCase2())
        findViewById<Button>(R.id.btnTestCase3).setOnClickListener(testCase3())
    }

    private fun testCase1() = View.OnClickListener {
        val intentSend = Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_EMAIL, arrayOf("milan.rajkovic@htecgroup.com"))
        }
        try {
            startActivity(intentSend)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "Could not found Activity", Toast.LENGTH_SHORT).show()
        }
    }

    private fun testCase2() = View.OnClickListener {
        val intentPhone = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "+38162470061", null))

        val contactIntent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
        if (contactIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(contactIntent, PICK_CONTACT_REQUEST)
        }
    }

    //Start Activity A with bundle
    private fun testCase3() = View.OnClickListener{
        val activityA = Intent(this@MainActivity, AActivity::class.java)

        val parcelizeTest = ParcelizeTest(1, 10)

        activityA.apply {
            putExtra("NAME", "Milan Rajkovic")
            putExtra("AGE", 23)
            putExtra("WEIGHT", 62.5f)
            putExtra("CAR", Bundle().apply {
                putString("BRAND", "BMW")
                putString("MODEL", "320d")
            })
            putExtra("PARCEL", parcelizeTest)
        }
        startActivityForResult(activityA, FOR_RESULT_ACTIVITY_REQUEST)
    }

    companion object {
        internal const val PICK_CONTACT_REQUEST = 0
        internal const val FOR_RESULT_ACTIVITY_REQUEST = 1
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            PICK_CONTACT_REQUEST -> {
                if (resultCode == RESULT_OK) {
                    Log.d(TAG, "onActivityResult: Status: OK")
                }
            }
            FOR_RESULT_ACTIVITY_REQUEST -> {
                if (resultCode == RESULT_OK) {
                    val result: String? = data?.getStringExtra("result")
                    Log.d(TAG, "onActivityResult: Status: OK Data: $result")
                }
                if (resultCode == RESULT_CANCELED) {
                    Log.d(TAG, "onActivityResult: Status: Cancel")
                }
            }
        }
    }
}