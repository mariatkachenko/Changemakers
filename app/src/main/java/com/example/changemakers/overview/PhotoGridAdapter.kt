package com.example.changemakers.overview

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.changemakers.network.CmProperty
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.changemakers.databinding.GridViewItemBinding
import android.view.LayoutInflater

class PhotoGridAdapter( private val onClickListener: OnClickListener ) :
    ListAdapter<CmProperty,
            PhotoGridAdapter.CmPropertyViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoGridAdapter.CmPropertyViewHolder {
        return CmPropertyViewHolder(GridViewItemBinding.inflate(
            LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CmPropertyViewHolder, position: Int) {
        val CmProperty = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(CmProperty)
        }
        holder.bind(CmProperty)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<CmProperty>() {
        override fun areItemsTheSame(oldItem: CmProperty,
                                     newItem: CmProperty): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: CmProperty,
                                        newItem: CmProperty): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class CmPropertyViewHolder(private var binding:
                                 GridViewItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(CmProperty: CmProperty) {
            binding.property = CmProperty
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener: (CmProperty:CmProperty) -> Unit) {
        fun onClick(CmProperty:CmProperty) = clickListener(CmProperty)
    }

}