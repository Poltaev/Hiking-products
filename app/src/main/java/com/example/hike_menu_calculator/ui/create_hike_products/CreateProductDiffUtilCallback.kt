package com.example.hike_menu_calculator.ui.create_hike_products


import androidx.recyclerview.widget.DiffUtil
import com.example.hike_menu_calculator.dataBase.Participants
import com.example.hike_menu_calculator.dataBase.products.Products

class CreateProductDiffUtilCallback(
    private val oldElement: List<Products>,
    private val newElement: List<Products>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldElement.size
    override fun getNewListSize(): Int = newElement.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldElement[oldItemPosition].id == newElement[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldElement[oldItemPosition].weWillUseItInTheCurrentCampaign == newElement[newItemPosition].weWillUseItInTheCurrentCampaign


}
