package com.example.hike_menu_calculator.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hike_menu_calculator.R
import com.example.hike_menu_calculator.dataBase.archive.ArchiveParticipants
import com.example.hike_menu_calculator.databinding.ArchiveBackpackPaticipantItemBinding

class ArchiveHikeParticipantAdapter(
    private val data: List<ArchiveParticipants>,
    private val onClick: (ArchiveParticipants) -> Unit
) :
    RecyclerView.Adapter<ArchiveHikeParticipantViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ArchiveHikeParticipantViewHolder {

        val binding = ArchiveBackpackPaticipantItemBinding.inflate(LayoutInflater.from(parent.context))
        return ArchiveHikeParticipantViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ArchiveHikeParticipantViewHolder, position: Int) {
        val response = data
        val item = response?.getOrNull(position)
        with(holder.binding) {
            if (item != null) {
                textViewNameParticipant.text = item.firstName + " " + item.lastName
            }
            if (item != null) {
                textViewMaxWeight.text = "Max вес: " + item.maximumPortableWeight.toString() + " г."
            }
            if (item != null) {
                textViewWeightOfPersonalItem.text =
                    "Личные вещи: " + item.weightOfPersonalItems.toString() + " г."
            }
            if (item != null) {
                textViewCurrentWeight.text =
                    "Текущий вес: " + item.weightWithLoad.toString() + " г."
                if (item.weightWithLoad < item.maximumPortableWeight - 2000) textViewCurrentWeight.setTextColor(
                    ContextCompat.getColor(
                        holder.binding.textViewCurrentWeight.context,
                        R.color.green
                    )
                )
                if (item.weightWithLoad >= item.maximumPortableWeight - 2000 && item.weightWithLoad < item.maximumPortableWeight) textViewCurrentWeight.setTextColor(
                    ContextCompat.getColor(
                        holder.binding.textViewCurrentWeight.context,
                        R.color.yellow
                    )
                )
                if (item.weightWithLoad >= item.maximumPortableWeight) textViewCurrentWeight.setTextColor(
                    ContextCompat.getColor(
                        holder.binding.textViewCurrentWeight.context,
                        R.color.red
                    )
                )
            }

            item?.let {
                if (item.gender == "Мужской") {
                    Glide
                        .with(imageViewParticipant.context)
                        .load(R.drawable.man1)
                        .into(imageViewParticipant)
                } else {
                    Glide
                        .with(imageViewParticipant.context)
                        .load(R.drawable.woman)
                        .into(imageViewParticipant)
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

class ArchiveHikeParticipantViewHolder(val binding: ArchiveBackpackPaticipantItemBinding) :
    RecyclerView.ViewHolder(binding.root)