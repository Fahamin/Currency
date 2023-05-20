package com.convert.usd.aud.currency.adapter

import LatestDataResponse
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.convert.usd.aud.currency.R
import com.convert.usd.aud.currency.model.RVmodel

class ExchangeAdapter(var list: MutableList<RVmodel>) :
    RecyclerView.Adapter<ExchangeAdapter.myHolder>() {

    class myHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvFrom: TextView = itemView.findViewById(R.id.tv_From)
        var tvto: TextView = itemView.findViewById(R.id.tv_To)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_view, parent, false)
        return myHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: myHolder, position: Int) {
        holder.tvFrom.text = "1 " + list[position].base
        holder.tvto.text = list[position].rate + " " + list[position].cname
    }
}