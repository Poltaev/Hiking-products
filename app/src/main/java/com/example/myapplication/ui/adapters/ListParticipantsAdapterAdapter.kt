package com.example.myapplication.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.dataBase.Participants
import com.example.myapplication.databinding.ParticipantInformationItemBinding

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
                textViewNameParticipants.text = item.firstName + item.lastName
            }
            if (item != null) {
                textViewAge.text = item.age.toString() + "лет"
            }
            if (item != null) {
                textViewGender.text = item.age.toString()
            }
//            item?.let {
//                Glide
//                    .with(imageViewParticipants.context)
//                    .load(it.photo)
//                    .into(imageViewParticipants)
//            }
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