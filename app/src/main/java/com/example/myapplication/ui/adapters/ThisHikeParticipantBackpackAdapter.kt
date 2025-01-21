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
import com.example.myapplication.dataBase.thisHike.ThisHikeParticipants
import com.example.myapplication.dataBase.thisHike.ThisHikeProducts
import com.example.myapplication.databinding.BackpackPaticipantItemBinding
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

class ThisHikeParticipantAdapter(
    private val data: List<ThisHikeParticipants>,
    private val onClick: (ThisHikeParticipants) -> Unit,
) :
    RecyclerView.Adapter<ThisHikeParticipantViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ThisHikeParticipantViewHolder {

        val binding = BackpackPaticipantItemBinding.inflate(LayoutInflater.from(parent.context))
        return ThisHikeParticipantViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ThisHikeParticipantViewHolder, position: Int) {
        val response = data
        val item = response?.getOrNull(position)
        with(holder.binding) {
            if (item != null) {
                textViewNameParticipant.text = item.firstName + item.lastName
            }

            item?.let {
                Glide
                    .with(imageViewParticipant.context)
                    .load(R.drawable.tourism)
                    .into(imageViewParticipant)
            }
        }

        holder.binding.root.setOnClickListener {
            item?.let {
                onClick(item)
            }
        }
    }
}

class ThisHikeParticipantViewHolder(val binding: BackpackPaticipantItemBinding) :
    RecyclerView.ViewHolder(binding.root)