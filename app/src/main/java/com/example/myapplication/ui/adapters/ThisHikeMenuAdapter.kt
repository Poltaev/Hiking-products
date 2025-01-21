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
import com.example.myapplication.dataBase.thisHike.ThisHikeMealIntakeSheet
import com.example.myapplication.dataBase.thisHike.ThisHikeProducts
import com.example.myapplication.databinding.CreateHikeAddProductItemBinding
import com.example.myapplication.databinding.CreateHikeEquipmentItemBinding
import com.example.myapplication.databinding.CreateHikeParticipantItemBinding
import com.example.myapplication.databinding.EquipmentInformationItemBinding
import com.example.myapplication.databinding.ListAddProductItemBinding
import com.example.myapplication.databinding.ListProductItemBinding
import com.example.myapplication.databinding.MenuListProductItemBinding
import com.example.myapplication.databinding.ParticipantInformationItemBinding
import com.example.myapplication.databinding.ThisHikeEquipmentInformationItemBinding
import com.example.myapplication.databinding.ThisHikeProductsInformationItemBinding
import com.example.myapplication.databinding.TypeListProductForAddProductItemBinding
import com.example.myapplication.databinding.TypeListProductItemBinding

class ThisHikeMenuAdapter(
    private val data: List<ThisHikeMealIntakeSheet>,
    private val onClick: (ThisHikeMealIntakeSheet) -> Unit,
) :
    RecyclerView.Adapter<ThisHikeMenuViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ThisHikeMenuViewHolder {

        val binding = MenuListProductItemBinding.inflate(LayoutInflater.from(parent.context))
        return ThisHikeMenuViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ThisHikeMenuViewHolder, position: Int) {
        val response = data
        val item = response?.getOrNull(position)
        with(holder.binding) {
            if (item != null) {
                textViewNameTypeListProducts.text = item.name
            }

            item?.let {
                Glide
                    .with(imageViewTypeProductsList.context)
                    .load(R.drawable.list_food)
                    .into(imageViewTypeProductsList)
            }
        }

        holder.binding.root.setOnClickListener {
            item?.let {
                onClick(item)
            }
        }
    }
}

class ThisHikeMenuViewHolder(val binding: MenuListProductItemBinding) :
    RecyclerView.ViewHolder(binding.root)