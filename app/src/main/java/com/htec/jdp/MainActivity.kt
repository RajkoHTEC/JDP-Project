package com.htec.jdp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerLayout)

        findViewById<AppCompatImageView>(R.id.im_hamburger).setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }

    fun closeDrawer(item : SideNavItem) {
        drawerLayout.closeDrawer(GravityCompat.START)
        when(item.id) {
            1-> {
                itemOne()
            }
            2-> {
                itemTwo()
            }
        }
    }

    private fun itemTwo() {

    }

    private fun itemOne() {

    }
}