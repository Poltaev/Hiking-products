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
import com.example.myapplication.databinding.CreateHikeAddProductItemBinding
import com.example.myapplication.databinding.CreateHikeEquipmentItemBinding
import com.example.myapplication.databinding.CreateHikeParticipantItemBinding
import com.example.myapplication.databinding.EquipmentInformationItemBinding
import com.example.myapplication.databinding.ListAddProductItemBinding
import com.example.myapplication.databinding.ListProductItemBinding
import com.example.myapplication.databinding.ParticipantInformationItemBinding
import com.example.myapplication.databinding.TypeListProductForAddProductItemBinding
import com.example.myapplication.databinding.TypeListProductItemBinding

class CreateHikeProductsAdapter(
    private val data: List<Products>,
    private val onClick: (Products) -> Unit,
) :
    RecyclerView.Adapter<CreateHikeProductsViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CreateHikeProductsViewHolder {

        val binding = CreateHikeAddProductItemBinding.inflate(LayoutInflater.from(parent.context))
        return CreateHikeProductsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CreateHikeProductsViewHolder, position: Int) {
        val response = data
        val item = response?.getOrNull(position)
        with(holder.binding) {
            if (item != null) {
                textViewNameProducts.text = item.name
            }
            if (item != null) {
                textViewWeightServing.text = "Вес порции: " + item.weightForPerson.toString() + " г."
            }
            if (item != null) {
                textViewWeightPackage.text = "Вес упаковки: " +  item.packageWeight.toString() + " г."
            }

            if (item != null) {
                if (item.weWillUseItInTheCurrentCampaign) {
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

class CreateHikeProductsViewHolder(val binding: CreateHikeAddProductItemBinding) :
    RecyclerView.ViewHolder(binding.root)