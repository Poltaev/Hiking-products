package com.example.hike_menu_calculator.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hike_menu_calculator.R
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeProducts
import com.example.hike_menu_calculator.databinding.ThisHikeMenuProductsItemBinding

class ThisHikeProductsMenuAdapter(
    private val data: List<ThisHikeProducts>,
    private val dataNameParticipant: List<String>,
    private val onClick: (ThisHikeProducts) -> Unit
) :
    RecyclerView.Adapter<ThisHikeProductsMenuViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ThisHikeProductsMenuViewHolder {

        val binding = ThisHikeMenuProductsItemBinding.inflate(LayoutInflater.from(parent.context))
        return ThisHikeProductsMenuViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ThisHikeProductsMenuViewHolder, position: Int) {
        val response = data
        val item = response?.getOrNull(position)
        val responseNamePartisipant = dataNameParticipant
        val item1 = responseNamePartisipant?.getOrNull(position)
        with(holder.binding) {
            if (item != null) {
                textViewNameProducts.text = item.name
            }
            if (item != null) {
                textViewNameParticipants.text = "Несет: " + item1
            }
            if (item != null) {
                textViewWeightPackage.text = "Вес упаковки: " + item.packageWeight.toString() + " г."
            }
            if (item != null) {
                textViewWeightOnePerson.text = "Вес порции: " + item.weightForPerson.toString() + " г."
            }
            if (item != null) {
                textViewWeightAll.text = "Вес на весь поход: " + item.weightOnTheHike.toString() + " г."
            }
            if (item != null) {
                textViewWeightRemains.text = "Текущий остаток: " + item.remainingWeight.toString() + " г."
            }
            if (item != null) {
                textViewWeightOneMeal.text = "Расход на 1 прием пищи: " + item.theWeightOfOneMeal.toString() + " г."
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

class ThisHikeProductsMenuViewHolder(val binding: ThisHikeMenuProductsItemBinding) :
    RecyclerView.ViewHolder(binding.root)