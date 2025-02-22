package com.example.hike_menu_calculator.ui.this_hike_list_of_products


import androidx.recyclerview.widget.DiffUtil
import com.example.hike_menu_calculator.dataBase.Equipment
import com.example.hike_menu_calculator.dataBase.Participants
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeProducts

class ThisHikeListOfProductDiffUtilCallback(
    private val oldElement: List<ThisHikeProducts>,
    private val newElement: List<ThisHikeProducts>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldElement.size
    override fun getNewListSize(): Int = newElement.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldElement[oldItemPosition].id == newElement[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldElement[oldItemPosition].fullyAssembled == newElement[newItemPosition].fullyAssembled


}
