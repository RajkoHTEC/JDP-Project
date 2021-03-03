package com.htec.jdp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.page_1 -> { setFragment("Fragment 1"); true}
                R.id.page_2 -> {setFragment("Fragment 2 "); true}
                R.id.page_3 -> {setFragment("Fragment 3"); true}
                else -> false
            }
        }

        if(savedInstanceState == null) {
            bottomNavigation.selectedItemId = R.id.page_2
            setFragment("Fragment 2")
        }
    }

    private fun setFragment(name : String) {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fragment_container, ExampleFragment::class.java, bundleOf(ExampleFragment.TYPE_ARG_KEY to name))
        }
    }
}