package com.example.hike_menu_calculator.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hike_menu_calculator.R
import com.example.hike_menu_calculator.dataBase.products.Products
import com.example.hike_menu_calculator.databinding.ListAddProductItemBinding

class ListAddProductsAdapter(
    private val data: List<Products>,
    private val listIdProducts: List<Int>,
    private val onClick: (Products) -> Unit,
) :
    RecyclerView.Adapter<ListAddProductsViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ListAddProductsViewHolder {

        val binding = ListAddProductItemBinding.inflate(LayoutInflater.from(parent.context))
        return ListAddProductsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ListAddProductsViewHolder, position: Int) {
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
            if (item != null) {
                if (listIdProducts.contains(item.id)) {
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

class ListAddProductsViewHolder(val binding: ListAddProductItemBinding) :
    RecyclerView.ViewHolder(binding.root)