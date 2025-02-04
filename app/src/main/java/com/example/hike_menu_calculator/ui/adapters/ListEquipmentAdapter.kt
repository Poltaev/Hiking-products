package com.example.hike_menu_calculator.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hike_menu_calculator.R
import com.example.hike_menu_calculator.dataBase.Equipment
import com.example.hike_menu_calculator.databinding.EquipmentInformationItemBinding

class ListEquipmentAdapter(
    private val data: List<Equipment>,
    private val onClick: (Equipment) -> Unit
) :
    RecyclerView.Adapter<EquipmentInformationViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EquipmentInformationViewHolder {

        val binding = EquipmentInformationItemBinding.inflate(LayoutInflater.from(parent.context))
        return EquipmentInformationViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: EquipmentInformationViewHolder, position: Int) {
        val response = data
        val item = response?.getOrNull(position)
        with(holder.binding) {
            if (item != null) {
                textViewNameEquipment.text = item.name
            }
            if (item != null) {
                textViewWeight.text =  "Вес снаряжения: " + item.weight.toString() + " грамм"
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

class EquipmentInformationViewHolder(val binding: EquipmentInformationItemBinding) :
    RecyclerView.ViewHolder(binding.root)