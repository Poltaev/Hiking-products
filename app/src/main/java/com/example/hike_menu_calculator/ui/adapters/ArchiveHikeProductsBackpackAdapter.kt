package com.example.hike_menu_calculator.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hike_menu_calculator.R
import com.example.hike_menu_calculator.dataBase.archive.ArchiveProducts
import com.example.hike_menu_calculator.databinding.ArchiveHikeBackpackProductsItemBinding

class ArchiveHikeProductsBackpackAdapter(
    private val data: List<ArchiveProducts>
) :
    RecyclerView.Adapter<ArchiveHikeProductsBackpackViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ArchiveHikeProductsBackpackViewHolder {

        val binding = ArchiveHikeBackpackProductsItemBinding.inflate(LayoutInflater.from(parent.context))
        return ArchiveHikeProductsBackpackViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ArchiveHikeProductsBackpackViewHolder, position: Int) {
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

    }
}

class ArchiveHikeProductsBackpackViewHolder(val binding: ArchiveHikeBackpackProductsItemBinding) :
    RecyclerView.ViewHolder(binding.root)