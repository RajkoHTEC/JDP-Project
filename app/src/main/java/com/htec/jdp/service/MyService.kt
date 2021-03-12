package com.htec.jdp.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log

private const val TAG = "MyService"

class MyService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        val sp = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val data = sp.getInt(KEY_NAME, -1)
        Log.d(TAG, "onCreate: $data")
    }

    companion object {
        const val PREF_NAME = "prefName"
        const val KEY_NAME = "key1"
    }
}