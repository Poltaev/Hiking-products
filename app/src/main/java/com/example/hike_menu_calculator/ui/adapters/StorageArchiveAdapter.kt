package com.example.hike_menu_calculator.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hike_menu_calculator.R
import com.example.hike_menu_calculator.dataBase.archive.ArchiveHike
import com.example.hike_menu_calculator.databinding.ListArchiveHikeItemBinding

class StorageArchiveAdapterAdapter(
    private val data: List<ArchiveHike>,
    private val onClick: (ArchiveHike) -> Unit
) :
    RecyclerView.Adapter<StorageArchiveAdapterViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StorageArchiveAdapterViewHolder {

        val binding = ListArchiveHikeItemBinding.inflate(LayoutInflater.from(parent.context))
        return StorageArchiveAdapterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: StorageArchiveAdapterViewHolder, position: Int) {
        val response = data
        val item = response?.getOrNull(position)
        with(holder.binding) {
            if (item != null) {
                textViewNameAchiveHike.text = item.name
            }

            item?.let {
                Glide
                    .with(imageViewAchiveHike.context)
                    .load(R.drawable.hiking)
                    .into(imageViewAchiveHike)
            }
        }

        holder.binding.root.setOnClickListener {
            item?.let {
                onClick(item)
            }
        }
    }
}

class StorageArchiveAdapterViewHolder(val binding: ListArchiveHikeItemBinding) :
    RecyclerView.ViewHolder(binding.root)