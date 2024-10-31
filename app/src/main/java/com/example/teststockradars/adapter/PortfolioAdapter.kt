package com.example.teststockradars.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.teststockradars.R
import com.example.teststockradars.model.PortfolioItem

class PortfolioAdapter(
    private val context: Context,
    private val list: List<PortfolioItem>
) : RecyclerView.Adapter<PortfolioAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.portfolio_item, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val ivIcon: ImageView = itemView.findViewById(R.id.ivIcon)
        val tvPendingPoint: TextView = itemView.findViewById(R.id.tvPendingPoint)
        val tvValueChangePoint: TextView = itemView.findViewById(R.id.tvValueChangePoint)
        val tvValuePendingPoint: TextView = itemView.findViewById(R.id.tvValuePendingPoint)
        val tvValueWithdrawAblePoint: TextView = itemView.findViewById(R.id.tvValueWithdrawAblePoint)
    }

    @SuppressLint("SetTextI18n", "DefaultLocale")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.tvName.text = item.title ?: ""
//        val changeValue = item.change.toString()
        val changeValue = String.format("%.2f", item.change)
        val changeValueText = if(changeValue.startsWith("-")){
            "($changeValue)"
        }else{
            "(+$changeValue)"
        }

        holder.tvValueChangePoint.text = changeValueText

        val textColor = if (changeValue.startsWith("-")) {
            ContextCompat.getColor(context, R.color.red)
        } else {
            ContextCompat.getColor(context, R.color.green)
        }
        holder.tvValueChangePoint.setTextColor(textColor)
//        holder.tvValueWithdrawAblePoint.text = item.withdrawablePoint.toString()
        holder.tvValueWithdrawAblePoint.text = String.format("%.2f", item.withdrawablePoint)

        Glide.with(context)
            .load(item.imagePlan)  // URL of the image
            .placeholder(R.drawable.ic_launcher_foreground) // Optional placeholder
            .error(R.drawable.ic_launcher_foreground)           // Optional error image
            .into(holder.ivIcon)                     // Your ImageView

        if (item.pendingPoint!! > 0) {
            holder.tvPendingPoint.visibility = View.VISIBLE
            holder.tvValuePendingPoint.visibility = View.VISIBLE
//            holder.tvValuePendingPoint.text = item.pendingPoint.toString()
            holder.tvValuePendingPoint.text = String.format("%.2f", item.pendingPoint)
        } else {
            holder.tvPendingPoint.visibility = View.GONE
            holder.tvValuePendingPoint.visibility = View.GONE
        }
    }


    override fun getItemCount(): Int = list.size

}