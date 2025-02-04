package com.example.hike_menu_calculator.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hike_menu_calculator.R
import com.example.hike_menu_calculator.dataBase.archive.ArchiveEquipment
import com.example.hike_menu_calculator.databinding.ArchiveHikeBackpackEquipmentItemBinding

class ArchiveHikeEquipmentBackpackAdapter(
    private val data: List<ArchiveEquipment>
) :
    RecyclerView.Adapter<ArchiveHikeEquipmentBackpackViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ArchiveHikeEquipmentBackpackViewHolder {

        val binding = ArchiveHikeBackpackEquipmentItemBinding.inflate(LayoutInflater.from(parent.context))
        return ArchiveHikeEquipmentBackpackViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ArchiveHikeEquipmentBackpackViewHolder, position: Int) {
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

    }
}

class ArchiveHikeEquipmentBackpackViewHolder(val binding: ArchiveHikeBackpackEquipmentItemBinding) :
    RecyclerView.ViewHolder(binding.root)