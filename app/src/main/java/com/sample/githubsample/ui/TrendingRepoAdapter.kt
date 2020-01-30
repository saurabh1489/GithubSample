package com.sample.githubsample.ui

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sample.githubsample.AppExecutors
import com.sample.githubsample.R
import com.sample.githubsample.databinding.ItemListBinding
import com.sample.githubsample.ui.common.DataBoundListAdapter
import com.sample.githubsample.vo.Repo

class TrendingRepoAdapter(
    private val dataBindingComponent: DataBindingComponent,
    appExecutors: AppExecutors
) : DataBoundListAdapter<Repo, ItemListBinding>(
    appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<Repo>() {
        override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
            return oldItem.name == newItem.name
        }

    }
) {
    private var mExpandPosition = -1

    override fun createBinding(parent: ViewGroup): ItemListBinding {
        val binding = DataBindingUtil.inflate<ItemListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_list,
            parent,
            false,
            dataBindingComponent
        )
        return binding
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).hashCode().toLong()
    }

    override fun bind(binding: ItemListBinding, item: Repo, position: Int) {
        val isExpanded = position == mExpandPosition
        binding.repo = item
        binding.isExpanded = isExpanded
        binding.root.setOnClickListener { view ->
            mExpandPosition = if (isExpanded) -1 else position
            notifyDataSetChanged()
        }
    }

}