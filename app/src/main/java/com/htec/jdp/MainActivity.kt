package com.htec.jdp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import com.htec.jdp.LoremIpsum.Companion.setSelectableColor

class MainActivity : AppCompatActivity() {
    private lateinit var textView : TextView
    private var shouldOverrideSelectedText = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById<TextView>(R.id.tvContent).apply {
            text = LoremIpsum.getText()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)

        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query != null) {
                    textView.text = LoremIpsum.getSelected(query, if(shouldOverrideSelectedText) textView.text.toString() else textView.text)
                    searchView.clearFocus()
                } else {
                    textView.text = LoremIpsum.getText()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_refresh) {
            textView.text = LoremIpsum.getText()
            return true
        }
        when(item.itemId) {
            R.id.action_override_color -> {shouldOverrideSelectedText = !shouldOverrideSelectedText; item.isChecked = shouldOverrideSelectedText; return true}
            R.id.action_setBlueColor -> {item.isChecked = true; setSelectableColor(Color.BLUE);  return true}
            R.id.action_setRedColor -> {item.isChecked = true; setSelectableColor(Color.RED); return true}
            R.id.action_setYellowColor -> {item.isChecked = true; setSelectableColor(Color.YELLOW); return true}
            R.id.action_startNewActivity -> {startNextActivity();  return true;}
        }
        return super.onOptionsItemSelected(item)
    }

    private fun startNextActivity() {
        startActivity(Intent(this, CustomToolbarActivity::class.java))
    }
}