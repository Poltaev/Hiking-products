package com.example.myapplication.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.dataBase.Equipment
import com.example.myapplication.dataBase.Participants
import com.example.myapplication.dataBase.products.ListTypeOfProducts
import com.example.myapplication.dataBase.products.Products
import com.example.myapplication.databinding.EquipmentInformationItemBinding
import com.example.myapplication.databinding.ListProductItemBinding
import com.example.myapplication.databinding.ParticipantInformationItemBinding
import com.example.myapplication.databinding.TypeListProductItemBinding

class ListProductsAdapter(
    private val data: List<Products>,
    private val onClick: (Products) -> Unit
) :
    RecyclerView.Adapter<ListProductsViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListProductsViewHolder {

        val binding = ListProductItemBinding.inflate(LayoutInflater.from(parent.context))
        return ListProductsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ListProductsViewHolder, position: Int) {
        val response = data
        val item = response?.getOrNull(position)
        with(holder.binding) {
            if (item != null) {
                textViewNameProducts.text = item.name
            }
            if (item != null) {
                textViewWeightServing.text = "Вес порции: " + item.weightForPerson.toString() + " г"
            }
            if (item != null) {
                textViewWeightPackage.text = "Вес упаковки: " + item.packageWeight.toString() + " г"
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

class ListProductsViewHolder(val binding: ListProductItemBinding) :
    RecyclerView.ViewHolder(binding.root)