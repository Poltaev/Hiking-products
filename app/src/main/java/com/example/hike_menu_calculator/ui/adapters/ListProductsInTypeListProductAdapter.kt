package com.example.hike_menu_calculator.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hike_menu_calculator.R
import com.example.hike_menu_calculator.dataBase.products.Products
import com.example.hike_menu_calculator.databinding.ListTypeWithProductItemBinding

class ListProductsInTypeListProductAdapter(
    private val data: List<Products>,
    private val onClick: (Products) -> Unit
) :
    RecyclerView.Adapter<ListTypeWithProductViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListTypeWithProductViewHolder {

        val binding = ListTypeWithProductItemBinding.inflate(LayoutInflater.from(parent.context))
        return ListTypeWithProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ListTypeWithProductViewHolder, position: Int) {
        val response = data
        val item = response?.getOrNull(position)
        with(holder.binding) {
            if (item != null) {
                textViewNameProducts.text = item.name
            }
            if (item != null) {
                textViewWeightServing.text = "Вес порции:" + item.weightForPerson.toString() + " г"
            }
            if (item != null) {
                textViewWeightPackage.text = "Вес пачки:" + item.packageWeight.toString() + " г"
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

class ListTypeWithProductViewHolder(val binding: ListTypeWithProductItemBinding) :
    RecyclerView.ViewHolder(binding.root)