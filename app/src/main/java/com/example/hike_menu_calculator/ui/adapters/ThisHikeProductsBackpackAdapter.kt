package com.example.hike_menu_calculator.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hike_menu_calculator.R
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeProducts
import com.example.hike_menu_calculator.databinding.ThisHikeBackpackProductsItemBinding

class ThisHikeProductsBackpackAdapter(
    private val data: List<ThisHikeProducts>,
    private val onClick: (ThisHikeProducts) -> Unit,
) :
    RecyclerView.Adapter<ThisHikeProductsBackpackViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ThisHikeProductsBackpackViewHolder {

        val binding = ThisHikeBackpackProductsItemBinding.inflate(LayoutInflater.from(parent.context))
        return ThisHikeProductsBackpackViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ThisHikeProductsBackpackViewHolder, position: Int) {
        val response = data
        val item = response?.getOrNull(position)
        with(holder.binding) {
            if (item != null) {
                textViewNameProducts.text = item.name
            }
            if (item != null) {
                textViewNameParticipants.text = item.name
            }
            if (item != null) {
                textViewWeightPackage.text = "Вес упаковки: " + item.packageWeight.toString() + " г."
            }
            if (item != null) {
                textViewWeightAll.text = "Вес на весь поход: " + item.weightOnTheHike.toString() + " г."
            }
            if (item != null) {
                textViewWeightRemains.text = "Текущий остаток: " + item.remainingWeight.toString() + " г."
            }

            item?.let {
                Glide
                    .with(imageViewProducts.context)
                    .load(R.drawable.hamburger)
                    .into(imageViewProducts)
            }
        }

        holder.binding.root.setOnClickListener {
            item?.let {
                onClick(item)
            }
        }
    }
}

class ThisHikeProductsBackpackViewHolder(val binding: ThisHikeBackpackProductsItemBinding) :
    RecyclerView.ViewHolder(binding.root)