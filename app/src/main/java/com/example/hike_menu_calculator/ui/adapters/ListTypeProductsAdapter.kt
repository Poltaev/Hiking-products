package com.example.hike_menu_calculator.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hike_menu_calculator.R
import com.example.hike_menu_calculator.dataBase.products.ListTypeOfProducts
import com.example.hike_menu_calculator.databinding.TypeListProductItemBinding

class ListTypeProductsAdapter(
    private val data: List<ListTypeOfProducts>,
    private val onClick: (ListTypeOfProducts) -> Unit
) :
    RecyclerView.Adapter<TypeProductsViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TypeProductsViewHolder {

        val binding = TypeListProductItemBinding.inflate(LayoutInflater.from(parent.context))
        return TypeProductsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: TypeProductsViewHolder, position: Int) {
        val response = data
        val item = response?.getOrNull(position)
        with(holder.binding) {
            if (item != null) {
                textViewNameTypeListProducts.text = item.typeOfMeal + " " + item.name
            }

            item?.let {
                Glide
                    .with(imageViewTypeProductsList.context)
                    .load(R.drawable.list_food)
                    .into(imageViewTypeProductsList)
            }
        }

        holder.binding.root.setOnClickListener {
            item?.let {
                onClick(item)
            }
        }
    }
}

class TypeProductsViewHolder(val binding: TypeListProductItemBinding) :
    RecyclerView.ViewHolder(binding.root)