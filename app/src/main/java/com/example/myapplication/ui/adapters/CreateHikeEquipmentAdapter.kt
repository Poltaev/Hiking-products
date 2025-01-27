package com.example.myapplication.ui.adapters

import android.app.Application
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.dataBase.Equipment
import com.example.myapplication.dataBase.Participants
import com.example.myapplication.dataBase.products.ListTypeOfProducts
import com.example.myapplication.dataBase.products.Products
import com.example.myapplication.databinding.CreateHikeEquipmentItemBinding
import com.example.myapplication.databinding.CreateHikeParticipantItemBinding
import com.example.myapplication.databinding.EquipmentInformationItemBinding
import com.example.myapplication.databinding.ListAddProductItemBinding
import com.example.myapplication.databinding.ListProductItemBinding
import com.example.myapplication.databinding.ParticipantInformationItemBinding
import com.example.myapplication.databinding.TypeListProductForAddProductItemBinding
import com.example.myapplication.databinding.TypeListProductItemBinding

class CreateHikeEquipmentAdapter(
    private val data: List<Equipment>,
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