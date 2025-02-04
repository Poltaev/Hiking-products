package com.example.hike_menu_calculator.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hike_menu_calculator.R
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeEquipment
import com.example.hike_menu_calculator.databinding.ThisHikeEquipmentInformationItemBinding

class ThisHikeEquipmentAdapter(
    private val data: List<ThisHikeEquipment>,
    private val dataNameParticipant: List<String>,
    private val onClick: (ThisHikeEquipment) -> Unit
) :
    RecyclerView.Adapter<ThisHikeEquipmentViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ThisHikeEquipmentViewHolder {

        val binding = ThisHikeEquipmentInformationItemBinding.inflate(LayoutInflater.from(parent.context))
        return ThisHikeEquipmentViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ThisHikeEquipmentViewHolder, position: Int) {
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
        holder.binding.root.setOnClickListener {
            item?.let {
                onClick(item)
            }
        }
    }
}

class ThisHikeEquipmentViewHolder(val binding: ThisHikeEquipmentInformationItemBinding) :
    RecyclerView.ViewHolder(binding.root)