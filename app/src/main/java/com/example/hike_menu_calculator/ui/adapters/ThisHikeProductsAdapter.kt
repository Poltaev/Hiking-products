package com.example.hike_menu_calculator.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hike_menu_calculator.R
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeProducts
import com.example.hike_menu_calculator.databinding.ThisHikeProductsInformationItemBinding

class ThisHikeProductsAdapter(
    var data: List<ThisHikeProducts>,
    private val dataNameParticipant: List<String>,
    private val onClick: (ThisHikeProducts) -> Unit,
    private val onClickCollected: (ThisHikeProducts) -> Unit,
    private val onClickHandOver: (ThisHikeProducts) -> Unit
) :
    RecyclerView.Adapter<ThisHikeProductsViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ThisHikeProductsViewHolder {

        val binding =
            ThisHikeProductsInformationItemBinding.inflate(LayoutInflater.from(parent.context))
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
                textViewWeightPackage.text =
                    "Вес упаковки: " + item.packageWeight.toString() + " г."
            }
            if (item != null) {
                textViewWeightOnePerson.text =
                    "Вес порции: " + item.weightForPerson.toString() + " г."
            }
            if (item != null) {
                textViewWeightAll.text = "Общий вес: " + item.weightOnTheHike.toString() + " г."
            }
            if (item != null) {
                textViewWeightOneMeal.text =
                    "1 прием пищи: " + item.theWeightOfOneMeal.toString() + " г."
            }
            if (item != null) {
                textViewComment.text =
                    item.comment
            }
            if (item != null) {
                if (item.fullyAssembled) {
                    buttonCollected.setBackgroundColor(
                        ContextCompat.getColor(
                            holder.binding.buttonCollected.context,
                            R.color.transparent_green_bright
                        )
                    )
                } else {
                    buttonCollected.setBackgroundColor(
                        ContextCompat.getColor(
                            holder.binding.buttonCollected.context,
                            R.color.transparent_red
                        )
                    )
                }
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
        holder.binding.buttonCollected.setOnClickListener {
            item?.let {
                onClickCollected(item)
            }
        }
        holder.binding.imageButtonHandOver.setOnClickListener {
            item?.let {
                onClickHandOver(item)
            }
        }
    }
}

class ThisHikeProductsViewHolder(val binding: ThisHikeProductsInformationItemBinding) :
    RecyclerView.ViewHolder(binding.root)