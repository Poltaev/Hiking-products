package com.example.hike_menu_calculator.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hike_menu_calculator.R
import com.example.hike_menu_calculator.dataBase.Participants
import com.example.hike_menu_calculator.databinding.ParticipantInformationItemBinding

class ListParticipantsAdapter(
    private val data: List<Participants>,
    private val onClick: (Participants) -> Unit
) :
    RecyclerView.Adapter<ParticipantInformationViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ParticipantInformationViewHolder {

        val binding = ParticipantInformationItemBinding.inflate(LayoutInflater.from(parent.context))
        return ParticipantInformationViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ParticipantInformationViewHolder, position: Int) {
        val response = data
        val item = response?.getOrNull(position)
        with(holder.binding) {
            if (item != null) {
                textViewNameParticipants.text = item.firstName + " " +item.lastName
            }
            if (item != null) {
                textViewAge.text = item.age.toString() + " лет"
            }
            if (item != null) {
                textViewGender.text = item.gender
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

class ParticipantInformationViewHolder(val binding: ParticipantInformationItemBinding) :
    RecyclerView.ViewHolder(binding.root)