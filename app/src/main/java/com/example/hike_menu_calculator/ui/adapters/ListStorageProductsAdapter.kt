package com.example.hike_menu_calculator.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hike_menu_calculator.R
import com.example.hike_menu_calculator.dataBase.products.ListTypeOfProducts
import com.example.hike_menu_calculator.dataBase.products.ProductStorage
import com.example.hike_menu_calculator.databinding.TypeListProductItemBinding
import com.google.android.gms.auth.api.signin.internal.Storage

class ListStorageProductsAdapter(
    private val data: List<ProductStorage>,
    private val onClick: (ProductStorage) -> Unit
) :
    RecyclerView.Adapter<ListStorageProductsViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListStorageProductsViewHolder {

        val binding = TypeListProductItemBinding.inflate(LayoutInflater.from(parent.context))
        return ListStorageProductsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ListStorageProductsViewHolder, position: Int) {
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

class ListStorageProductsViewHolder(val binding: TypeListProductItemBinding) :
    RecyclerView.ViewHolder(binding.root)