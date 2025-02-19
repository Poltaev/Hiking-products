package com.example.hike_menu_calculator.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hike_menu_calculator.R
import com.example.hike_menu_calculator.dataBase.Equipment
import com.example.hike_menu_calculator.databinding.CreateHikeEquipmentItemBinding

class CreateHikeEquipmentAdapter(
     var data: List<Equipment>,
    private val onClick: (Equipment) -> Unit,
) :
    RecyclerView.Adapter<CreateHikeEquipmentViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CreateHikeEquipmentViewHolder {

        val binding = CreateHikeEquipmentItemBinding.inflate(LayoutInflater.from(parent.context))
        return CreateHikeEquipmentViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CreateHikeEquipmentViewHolder, position: Int) {
        val response = data
        val item = response?.getOrNull(position)
        with(holder.binding) {
            if (item != null) {
                textViewNameEquipment.text = item.name
            }
            if (item != null) {
                textViewWeight.text = "Вес:" + item.weight.toString() + " г."
            }


            if (item != null) {
                if (item.equipmentInTheCampaign) {
                    buttonBackground.setBackgroundColor(
                        ContextCompat.getColor(
                            holder.binding.buttonBackground.context,
                            R.color.transparent_green
                        )
                    )
                } else {
                    buttonBackground.setBackgroundColor(
                        ContextCompat.getColor(
                            holder.binding.buttonBackground.context,
                            R.color.transparent
                        )
                    )
                }
            }

            item?.let {
                Glide
                    .with(imageViewEquipment.context)
                    .load(R.drawable.tent)
                    .into(imageViewEquipment)
            }
        }

        holder.binding.root.setOnClickListener {
            item?.let {
                onClick(item)
            }
        }
    }
}

class CreateHikeEquipmentViewHolder(val binding: CreateHikeEquipmentItemBinding) :
    RecyclerView.ViewHolder(binding.root)