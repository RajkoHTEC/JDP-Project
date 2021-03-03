package com.htec.jdp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SideNavFragment : Fragment() {
    private val sideNavAdapter: SideNavAdapter = SideNavAdapter { position, item ->
        onItemClick(position, item)
    }

    private fun onItemClick(position: Int, item: SideNavItem) {
        Toast.makeText(requireContext(), "${item.itemName}", Toast.LENGTH_SHORT).show()
        (activity as MainActivity).closeDrawer(item)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_side_nav, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews(view)
    }

    private fun setUpViews(v : View) {
        with(v.findViewById<RecyclerView>(R.id.rv_side_nav_options)) {
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            adapter = sideNavAdapter
        }
        sideNavAdapter.setNavItemsData(prepareNavItems())
    }

    private fun prepareNavItems(): List<SideNavItem> {
        val menuItemsList = ArrayList<SideNavItem>()
        menuItemsList.add(SideNavItem(1, "Fragment 1",  R.drawable.ic_baseline_camera_alt_24))
        menuItemsList.add(SideNavItem(1, "Fragment2",  R.drawable.ic_baseline_image_24))
        menuItemsList.add(SideNavItem(1, "Fragment3",  R.drawable.ic_baseline_slideshow_24))
        return menuItemsList
    }
}