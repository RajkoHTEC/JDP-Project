package com.htec.jdp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView

class SideNavAdapter(private val onItemClick: ((position: Int, item: SideNavItem) -> Unit)) : RecyclerView.Adapter<SideNavAdapter.SideNavVH>() {
    var menuItemsList = ArrayList<SideNavItem>()

    fun setNavItemsData( list: List<SideNavItem>){
        menuItemsList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): SideNavAdapter.SideNavVH {
        return SideNavVH(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_side_nav, viewGroup, false)
        )
    }

    inner class SideNavVH(inflate: View) : RecyclerView.ViewHolder(inflate) {

        fun setData(sideNavItem: SideNavItem) {
            itemView.setOnClickListener {
                sideNavItem.let {
                    onItemClick.invoke(adapterPosition, sideNavItem)
                }
            }
            itemView.findViewById<AppCompatImageView>(R.id.iv_nav_option).setImageResource(sideNavItem.resourceId)
            itemView.findViewById<AppCompatTextView>(R.id.tv_nav_text).text = sideNavItem.itemName
        }
    }

    override fun getItemCount(): Int {
        return menuItemsList.size
    }

    override fun onBindViewHolder(holder: SideNavVH, position: Int) {
        holder.setData(menuItemsList[position])
    }
}
