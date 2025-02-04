package com.example.hike_menu_calculator.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hike_menu_calculator.R
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeMealIntakeSheet
import com.example.hike_menu_calculator.databinding.MenuListProductItemBinding

class ThisHikeMenuAdapter(
    private val data: List<ThisHikeMealIntakeSheet>,
    private val onClick: (ThisHikeMealIntakeSheet) -> Unit,
) :
    RecyclerView.Adapter<ThisHikeMenuViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ThisHikeMenuViewHolder {

        val binding = MenuListProductItemBinding.inflate(LayoutInflater.from(parent.context))
        return ThisHikeMenuViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ThisHikeMenuViewHolder, position: Int) {
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

class ThisHikeMenuViewHolder(val binding: MenuListProductItemBinding) :
    RecyclerView.ViewHolder(binding.root)