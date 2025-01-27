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
import com.example.myapplication.databinding.CreateHikeParticipantItemBinding
import com.example.myapplication.databinding.EquipmentInformationItemBinding
import com.example.myapplication.databinding.ListAddProductItemBinding
import com.example.myapplication.databinding.ListProductItemBinding
import com.example.myapplication.databinding.ParticipantInformationItemBinding
import com.example.myapplication.databinding.TypeListProductForAddProductItemBinding
import com.example.myapplication.databinding.TypeListProductItemBinding

class CreateHikeParticipantsAdapter(
    private val data: List<Participants>,
    private val onClick: (Participants) -> Unit,
) :
    RecyclerView.Adapter<CreateHikeParticipantsViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CreateHikeParticipantsViewHolder {

        val binding = CreateHikeParticipantItemBinding.inflate(LayoutInflater.from(parent.context))
        return CreateHikeParticipantsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CreateHikeParticipantsViewHolder, position: Int) {
        val response = data
        val item = response?.getOrNull(position)
        with(holder.binding) {
            if (item != null) {
                textViewNameParticipants.text = item.firstName + " " + item.lastName
            }
            if (item != null) {
                textViewAge.text = item.age + " лет"
            }
            if (item != null) {
                textViewGender.text = item.gender
            }
            if (item != null) {
                textViewMaxWeight.text = item.maximumPortableWeight.toString() + " г. max"
            }
            if (item != null) {
                textViewWeightOfPersonalItem.text = item.weightOfPersonalItems.toString() + " г. личных вещей"
            }

            if (item != null) {
                if (item.participationInTheCampaign) {
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
                if (item.gender == "Мужской"){
                    Glide
                        .with(imageViewParticipants.context)
                        .load(R.drawable.man1)
                        .into(imageViewParticipants)
                } else {
                    Glide
                        .with(imageViewParticipants.context)
                        .load(R.drawable.woman)
                        .into(imageViewParticipants)
                }
            }
        }

        holder.binding.root.setOnClickListener {
            item?.let {
                onClick(item)
            }
        }
    }
}

class CreateHikeParticipantsViewHolder(val binding: CreateHikeParticipantItemBinding) :
    RecyclerView.ViewHolder(binding.root)