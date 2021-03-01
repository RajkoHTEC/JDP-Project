package com.htec.jdp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory

class MyFragmentFactory (private val someDependency: SomeDependency) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment =
        when (loadFragmentClass(classLoader, className)) {
            ExampleFragment::class.java -> ExampleFragment(someDependency)
            else -> super.instantiate(classLoader, className)
        }
}