package com.example.batru.materialdesign.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.batru.materialdesign.R
import com.example.batru.materialdesign.models.Infomation

class VivzAdapter(private var context: Context, private var data: ArrayList<Infomation>) :
        RecyclerView.Adapter<VivzAdapter.ItemHolder>() {

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val current: Infomation = data[position]
        holder.tvText.text = current.getTitle()
        holder.imgIcon.setImageResource(current.getIconId())
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ItemHolder {
        val view: View = LayoutInflater.from(context)
                .inflate(R.layout.custom_row, parent, false)
        return ItemHolder(view)
    }

    inner class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imgIcon: ImageView = view.findViewById(R.id.listIcon)
        var tvText: TextView = view.findViewById(R.id.listText)
    }
}