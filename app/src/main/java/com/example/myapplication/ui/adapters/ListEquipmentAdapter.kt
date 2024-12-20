package com.example.myapplication.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.dataBase.Equipment
import com.example.myapplication.dataBase.Participants
import com.example.myapplication.databinding.EquipmentInformationItemBinding
import com.example.myapplication.databinding.ParticipantInformationItemBinding

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
                textViewWeight.text = item.weight.toString()
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