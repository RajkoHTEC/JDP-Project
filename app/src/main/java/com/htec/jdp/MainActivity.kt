package com.htec.jdp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.commit
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) {
            setFragment("Welcome fragment")
        }

        drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        findViewById<MaterialToolbar>(R.id.topAppBar).setNavigationOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        val navigationView = findViewById<NavigationView>(R.id.navigationView)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            drawerLayout.closeDrawer(GravityCompat.START)

            when(menuItem.itemId) {
                R.id.item1 -> setFragment("Item 1")
                R.id.item2 -> setFragment("Item 2")
                R.id.item3 -> setFragment("Item 3")
                R.id.itemNewActivity -> {
                    menuItem.isChecked = false
                    newActivity()
                }
            }

            true
        }
    }

    override fun onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }

    private fun setFragment(name : String) {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fragment_container, ExampleFragment::class.java, bundleOf(ExampleFragment.TYPE_ARG_KEY to name))
        }
    }

    private fun newActivity() {
        startActivity(Intent(this, BottomNavigationActivity::class.java))
    }
}