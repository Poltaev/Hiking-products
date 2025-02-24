package com.example.hike_menu_calculator.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hike_menu_calculator.R
import com.example.hike_menu_calculator.dataBase.products.ListTypeOfProducts
import com.example.hike_menu_calculator.dataBase.products.ProductStorage
import com.example.hike_menu_calculator.databinding.CreateStorageProductItemBinding
import com.example.hike_menu_calculator.databinding.TypeListProductItemBinding
import com.google.android.gms.auth.api.signin.internal.Storage

class CreateStorageProductsAdapter(
    private val data: List<ProductStorage>,
    private val onClick: (ProductStorage) -> Unit
) :
    RecyclerView.Adapter<CreateStorageProductsViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CreateStorageProductsViewHolder {

        val binding = CreateStorageProductItemBinding.inflate(LayoutInflater.from(parent.context))
        return CreateStorageProductsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CreateStorageProductsViewHolder, position: Int) {
        val response = data
        val item = response?.getOrNull(position)
        with(holder.binding) {
            if (item != null) {
                textViewNameTypeListProducts.text = item.name
            }

            item?.let {
                Glide
                    .with(imageViewTypeProductsList.context)
                    .load(R.drawable.omelette)
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

class CreateStorageProductsViewHolder(val binding: CreateStorageProductItemBinding) :
    RecyclerView.ViewHolder(binding.root)