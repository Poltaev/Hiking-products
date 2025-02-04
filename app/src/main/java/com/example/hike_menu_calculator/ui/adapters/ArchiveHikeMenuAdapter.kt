package com.example.hike_menu_calculator.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hike_menu_calculator.R
import com.example.hike_menu_calculator.dataBase.archive.ArchiveHikeMealIntakeSheet
import com.example.hike_menu_calculator.databinding.ArchiveMenuListProductItemBinding

class ArchiveHikeMenuAdapter(
    private val data: List<ArchiveHikeMealIntakeSheet>,
    private val onClick: (ArchiveHikeMealIntakeSheet) -> Unit,
) :
    RecyclerView.Adapter<ArchiveHikeMenuViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ArchiveHikeMenuViewHolder {

        val binding = ArchiveMenuListProductItemBinding.inflate(LayoutInflater.from(parent.context))
        return ArchiveHikeMenuViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ArchiveHikeMenuViewHolder, position: Int) {
        val response = data
        val item = response?.getOrNull(position)
        with(holder.binding) {
            if (item != null) {
                textViewNameTypeListProducts.text = item.name
            }

            item?.let {
                Glide
                    .with(imageViewTypeProductsList.context)
                    .load(R.drawable.list_food)
                    .into(imageViewTypeProductsList)
            }
        }

        holder.binding.root.setOnClickListener {
            item?.let {
                onClick(item)
            }
        }
    }
}

class ArchiveHikeMenuViewHolder(val binding: ArchiveMenuListProductItemBinding) :
    RecyclerView.ViewHolder(binding.root)