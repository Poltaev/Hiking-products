package com.example.hike_menu_calculator.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hike_menu_calculator.R
import com.example.hike_menu_calculator.dataBase.Participants
import com.example.hike_menu_calculator.databinding.CreateHikeParticipantItemBinding

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