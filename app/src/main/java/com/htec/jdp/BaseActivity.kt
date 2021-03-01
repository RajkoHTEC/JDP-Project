package com.htec.jdp

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


abstract class BaseActivity(private val TAG: String) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnStandardActivity).setOnClickListener {
            startActivity(Intent(this@BaseActivity, StandardActivity::class.java))
        }
        findViewById<Button>(R.id.btnSingleTopActivity).setOnClickListener {
            startActivity(Intent(this@BaseActivity, SingleTopActivity::class.java))
        }
        findViewById<Button>(R.id.btnSingleTaskActivity).setOnClickListener {
            startActivity(Intent(this@BaseActivity, SingleTaskActivity::class.java))
        }
        findViewById<Button>(R.id.btnSingleInstanceActivity).setOnClickListener {
            startActivity(Intent(this@BaseActivity, SingleInstanceActivity::class.java))
        }
        findViewById<Button>(R.id.btnSingleInstanceWithAffinityActivity).setOnClickListener {
            startActivity(Intent(this@BaseActivity, SingleInstanceWithAffinity::class.java))
        }

        Log.d(TAG, "onCreate: ")
    }

    override fun onStart() {
        Log.d(TAG, "onStart: ")
        super.onStart()
    }

    override fun onResume() {
        Log.d(TAG, "onResume: ")
        super.onResume()

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            val activityManager = getSystemService(ACTIVITY_SERVICE) as ActivityManager
            val tasks = activityManager.appTasks

            var newTitle : String = TAG
            for (task in tasks) {
                newTitle += "[${task.taskInfo.numActivities}]"
            }
            title = newTitle
        } else {
            title = TAG
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d(TAG, "onNewIntent: ")
        Toast.makeText(baseContext, "On New Intent!", Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        Log.d(TAG, "onPause: ")
        super.onPause()
    }

    override fun onRestart() {
        Log.d(TAG, "onRestart: ")
        super.onRestart()
    }

    override fun onStop() {
        Log.d(TAG, "onStop: ")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: ")
        super.onDestroy()
    }
}