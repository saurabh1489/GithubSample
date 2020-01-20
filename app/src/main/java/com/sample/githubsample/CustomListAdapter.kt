package com.sample.githubsample

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomListAdapter(private val dataset: Array<String>) :
    RecyclerView.Adapter<CustomListAdapter.ViewHolder>() {
    private var mExpandPosition = -1

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val textView = view.findViewById<TextView>(R.id.item_text)
        val detailTextView = view.findViewById<TextView>(R.id.item_detail_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return ViewHolder(rootView)
    }

    override fun getItemCount() = dataset.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val isExpanded = position == mExpandPosition
        holder.detailTextView.visibility = if (isExpanded) VISIBLE else GONE
        holder.textView.text = dataset[position]
        holder.view.isActivated = isExpanded
        holder.view.setOnClickListener { v ->
            mExpandPosition = if (isExpanded) -1 else position
            notifyItemChanged(position)
        }
    }
}