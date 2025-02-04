package com.example.hike_menu_calculator.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hike_menu_calculator.R
import com.example.hike_menu_calculator.dataBase.archive.ArchiveEquipment
import com.example.hike_menu_calculator.databinding.ArchiveHikeEquipmentInformationItemBinding

class ArchiveHikeEquipmentAdapter(
    private val data: List<ArchiveEquipment>,
    private val dataNameParticipant: List<String>
) :
    RecyclerView.Adapter<ArchiveHikeEquipmentViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ArchiveHikeEquipmentViewHolder {

        val binding = ArchiveHikeEquipmentInformationItemBinding.inflate(LayoutInflater.from(parent.context))
        return ArchiveHikeEquipmentViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ArchiveHikeEquipmentViewHolder, position: Int) {
        val response = data
        val item = response?.getOrNull(position)
        val responseNamePartisipant = dataNameParticipant
        val item1 = responseNamePartisipant?.getOrNull(position)

        with(holder.binding) {
            if (item != null) {
                textViewNameEquipment.text = item.name
            }
            if (item != null) {
                textViewNameParticipants.text = "Несет: " + item1
            }
            if (item != null) {
                textViewWeightPackage.text = "Вес: " + item.weight.toString() + " г."
            }


            item?.let {
                Glide
                    .with(imageViewEquipment.context)
                    .load(R.drawable.tent)
                    .into(imageViewEquipment)
            }
        }
    }
}

class ArchiveHikeEquipmentViewHolder(val binding: ArchiveHikeEquipmentInformationItemBinding) :
    RecyclerView.ViewHolder(binding.root)