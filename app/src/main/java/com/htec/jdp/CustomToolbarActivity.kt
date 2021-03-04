package com.htec.jdp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CustomToolbarActivity : AppCompatActivity() {
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_toolbar)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        tvResult = findViewById(R.id.d9_tvResult)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.custom_toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.d9_toolbar_action_1 -> {
                tvResult.text = getText(R.string.title_1)
                true
            }
            else -> false
        }
    }
}