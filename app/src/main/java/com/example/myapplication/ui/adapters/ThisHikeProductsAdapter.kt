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
import com.example.myapplication.databinding.ThisHikeProductsInformationItemBinding
import com.example.myapplication.databinding.TypeListProductForAddProductItemBinding
import com.example.myapplication.databinding.TypeListProductItemBinding

class ThisHikeProductsAdapter(
    private val data: List<ThisHikeProducts>
) :
    RecyclerView.Adapter<ThisHikeProductsViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ThisHikeProductsViewHolder {

        val binding = ThisHikeProductsInformationItemBinding.inflate(LayoutInflater.from(parent.context))
        return ThisHikeProductsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ThisHikeProductsViewHolder, position: Int) {
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

    }
}

class ThisHikeProductsViewHolder(val binding: ThisHikeProductsInformationItemBinding) :
    RecyclerView.ViewHolder(binding.root)