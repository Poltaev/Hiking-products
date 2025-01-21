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
import com.example.myapplication.dataBase.thisHike.ThisHikeEquipment
import com.example.myapplication.dataBase.thisHike.ThisHikeProducts
import com.example.myapplication.databinding.CreateHikeAddProductItemBinding
import com.example.myapplication.databinding.CreateHikeEquipmentItemBinding
import com.example.myapplication.databinding.CreateHikeParticipantItemBinding
import com.example.myapplication.databinding.EquipmentInformationItemBinding
import com.example.myapplication.databinding.ListAddProductItemBinding
import com.example.myapplication.databinding.ListProductItemBinding
import com.example.myapplication.databinding.ParticipantInformationItemBinding
import com.example.myapplication.databinding.ThisHikeBackpackEquipmentItemBinding
import com.example.myapplication.databinding.ThisHikeBackpackProductsItemBinding
import com.example.myapplication.databinding.ThisHikeMenuProductsItemBinding
import com.example.myapplication.databinding.ThisHikeProductsInformationItemBinding
import com.example.myapplication.databinding.TypeListProductForAddProductItemBinding
import com.example.myapplication.databinding.TypeListProductItemBinding

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