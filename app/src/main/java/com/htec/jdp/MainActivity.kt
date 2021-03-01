package com.htec.jdp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val bundle = bundleOf("INT" to 10, "FLOAT" to 1.5f, "STRING" to "Fragment 1")
    private val bundle2 = bundleOf("INT" to 10, "FLOAT" to 1.5f, "STRING" to "\nFragment 2")
    private val bundle3 = bundleOf("INT" to 10, "FLOAT" to 1.5f, "STRING" to "\n\nFragment 3")
    private val bundle4 = bundleOf("INT" to 10, "FLOAT" to 1.5f, "STRING" to "\n\n\nFragment 4")

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = MyFragmentFactory(SomeDependency("Hello from Activity!"))
        super.onCreate(savedInstanceState)
        if(savedInstanceState == null) {

            //addAllFragments()
            //addAndPushToBackstackAllFragments()
            //replaceAllFragments()
            replaceAndPushToBackstackAllFragments()
        }
    }

    //With onBackPress closes all fragments, only 2 fragments are on resume
    private fun addAllFragments() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add(R.id.fragment_container_view, ExampleFragment::class.java, bundle)
            add(R.id.fragment_container_view, ExampleFragment::class.java, bundle2)
            add(R.id.fragment_container_view, ExampleFragment::class.java, bundle3)
            add(R.id.fragment_container_view, ExampleFragment::class.java, bundle4)
        }
    }

    //All fragments are shown, they closes one by one
    private fun addAndPushToBackstackAllFragments() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add(R.id.fragment_container_view, ExampleFragment::class.java, bundle)
        }
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add(R.id.fragment_container_view, ExampleFragment::class.java, bundle2)
            addToBackStack("")
        }
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add(R.id.fragment_container_view, ExampleFragment::class.java, bundle3)
            addToBackStack("")
        }
    }

    //shows only last
    private fun replaceAllFragments() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fragment_container_view, ExampleFragment::class.java, bundle)
            replace(R.id.fragment_container_view, ExampleFragment::class.java, bundle2)
            replace(R.id.fragment_container_view, ExampleFragment::class.java, bundle3)
        }
    }

    //shows only last fragment
    private fun replaceAndPushToBackstackAllFragments() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fragment_container_view, ExampleFragment::class.java, bundle)
        }
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fragment_container_view, ExampleFragment::class.java, bundle2)
            addToBackStack("")
        }
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fragment_container_view, ExampleFragment::class.java, bundle3)
            addToBackStack("")
        }
    }
}