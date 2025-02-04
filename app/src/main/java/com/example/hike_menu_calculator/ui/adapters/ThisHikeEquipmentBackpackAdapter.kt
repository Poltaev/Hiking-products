package com.example.hike_menu_calculator.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hike_menu_calculator.R
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeEquipment
import com.example.hike_menu_calculator.databinding.ThisHikeBackpackEquipmentItemBinding

class ThisHikeEquipmentBackpackAdapter(
    private val data: List<ThisHikeEquipment>,
    private val onClick: (ThisHikeEquipment) -> Unit,
) :
    RecyclerView.Adapter<ThisHikeEquipmentBackpackViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ThisHikeEquipmentBackpackViewHolder {

        val binding = ThisHikeBackpackEquipmentItemBinding.inflate(LayoutInflater.from(parent.context))
        return ThisHikeEquipmentBackpackViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ThisHikeEquipmentBackpackViewHolder, position: Int) {
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
                textViewWeight.text = item.weight.toString() + " г."
            }

            item?.let {
                Glide
                    .with(imageViewProducts.context)
                    .load(R.drawable.tent)
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

class ThisHikeEquipmentBackpackViewHolder(val binding: ThisHikeBackpackEquipmentItemBinding) :
    RecyclerView.ViewHolder(binding.root)