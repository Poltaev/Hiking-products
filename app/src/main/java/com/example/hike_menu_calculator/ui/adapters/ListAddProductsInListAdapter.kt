package com.example.hike_menu_calculator.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hike_menu_calculator.R
import com.example.hike_menu_calculator.dataBase.products.ListTypeOfProducts
import com.example.hike_menu_calculator.databinding.TypeListProductForAddProductItemBinding

class ListAddProductsInListAdapter(
    private val data: List<ListTypeOfProducts>,
    private val listIdProducts: List<Int>,
    private val onClick: (ListTypeOfProducts) -> Unit,
) :
    RecyclerView.Adapter<ListAddProductsInListViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ListAddProductsInListViewHolder {

        val binding = TypeListProductForAddProductItemBinding.inflate(LayoutInflater.from(parent.context))
        return ListAddProductsInListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ListAddProductsInListViewHolder, position: Int) {
        val response = data
        val item = response?.getOrNull(position)
        with(holder.binding) {
            if (item != null) {
                textViewNameTypeListProducts.text = item.typeOfMeal + " " + item.name
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

class ListAddProductsInListViewHolder(val binding: TypeListProductForAddProductItemBinding) :
    RecyclerView.ViewHolder(binding.root)