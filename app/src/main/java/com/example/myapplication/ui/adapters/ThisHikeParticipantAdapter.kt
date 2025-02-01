package com.example.myapplication.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.dataBase.thisHike.ThisHikeParticipants
import com.example.myapplication.databinding.BackpackPaticipantItemBinding

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

class ThisHikeParticipantViewHolder(val binding: BackpackPaticipantItemBinding) :
    RecyclerView.ViewHolder(binding.root)