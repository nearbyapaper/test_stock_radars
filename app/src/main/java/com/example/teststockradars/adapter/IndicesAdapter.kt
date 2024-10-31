package com.example.teststockradars.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.teststockradars.R
import com.example.teststockradars.model.DataItem

class IndicesAdapter(
    private val context: Context,
    private val list: List<DataItem>
) : RecyclerView.Adapter<IndicesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.index_item, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvChangeValue: TextView = itemView.findViewById(R.id.tvChangeValue)
        val tvChangePercent: TextView = itemView.findViewById(R.id.tvChangePercent)
        val tvIndexName: TextView = itemView.findViewById(R.id.tvIndexName)
        val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        val layoutIndex: ConstraintLayout = itemView.findViewById(R.id.layoutIndex)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.tvChangeValue.text = item.change.toString()
        holder.tvChangePercent.text = item.percentChange.toString()
        holder.tvIndexName.text = item.shortName
        holder.tvPrice.text = item.price.toString()

        val bg = if (item.change.toString().startsWith("-")) {
            ContextCompat.getDrawable(context,R.drawable.bg_index_red)
        } else {
            ContextCompat.getDrawable(context,R.drawable.bg_index_green)
        }
        holder.layoutIndex.background = bg
    }

    override fun getItemCount(): Int = list.size
}