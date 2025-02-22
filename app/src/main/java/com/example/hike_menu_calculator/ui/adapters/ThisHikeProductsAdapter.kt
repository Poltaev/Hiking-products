package com.example.hike_menu_calculator.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hike_menu_calculator.R
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeProducts
import com.example.hike_menu_calculator.databinding.ThisHikeProductsInformationItemBinding

class ThisHikeProductsAdapter(
    private val data: List<ThisHikeProducts>,
    private val dataNameParticipant: List<String>,
    private val onClick: (ThisHikeProducts) -> Unit
) :
    RecyclerView.Adapter<ThisHikeProductsViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ThisHikeProductsViewHolder {

        val binding = ThisHikeProductsInformationItemBinding.inflate(LayoutInflater.from(parent.context))
        return ThisHikeProductsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ThisHikeProductsViewHolder, position: Int) {
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
                textViewWeightAll.text = "Общий вес: " + item.weightOnTheHike.toString() + " г."
            }
            if (item != null) {
                textViewWeightOneMeal.text = "1 прием пищи: " + item.theWeightOfOneMeal.toString() + " г."
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

class ThisHikeProductsViewHolder(val binding: ThisHikeProductsInformationItemBinding) :
    RecyclerView.ViewHolder(binding.root)