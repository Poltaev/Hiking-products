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
import com.example.myapplication.dataBase.thisHike.ThisHikeProducts
import com.example.myapplication.databinding.CreateHikeAddProductItemBinding
import com.example.myapplication.databinding.CreateHikeEquipmentItemBinding
import com.example.myapplication.databinding.CreateHikeParticipantItemBinding
import com.example.myapplication.databinding.EquipmentInformationItemBinding
import com.example.myapplication.databinding.ListAddProductItemBinding
import com.example.myapplication.databinding.ListProductItemBinding
import com.example.myapplication.databinding.ParticipantInformationItemBinding
import com.example.myapplication.databinding.ThisHikeBackpackProductsItemBinding
import com.example.myapplication.databinding.ThisHikeMenuProductsItemBinding
import com.example.myapplication.databinding.ThisHikeProductsInformationItemBinding
import com.example.myapplication.databinding.TypeListProductForAddProductItemBinding
import com.example.myapplication.databinding.TypeListProductItemBinding

class ThisHikeProductsBackpackAdapter(
    private val data: List<ThisHikeProducts>,
    private val onClick: (ThisHikeProducts) -> Unit,
) :
    RecyclerView.Adapter<ThisHikeProductsBackpackViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ThisHikeProductsBackpackViewHolder {

        val binding = ThisHikeBackpackProductsItemBinding.inflate(LayoutInflater.from(parent.context))
        return ThisHikeProductsBackpackViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ThisHikeProductsBackpackViewHolder, position: Int) {
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
                textViewWeightPackage.text = item.packageWeight.toString() + " г."
            }
            if (item != null) {
                textViewWeightOnePerson.text = item.weightForPerson.toString() + " г."
            }
            if (item != null) {
                textViewWeightAll.text = item.weightOnTheHike.toString() + " г."
            }
            if (item != null) {
                textViewWeightRemains.text = item.remainingWeight.toString() + " г."
            }

            item?.let {
                Glide
                    .with(imageViewProducts.context)
                    .load(R.drawable.hamburger)
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

class ThisHikeProductsBackpackViewHolder(val binding: ThisHikeBackpackProductsItemBinding) :
    RecyclerView.ViewHolder(binding.root)