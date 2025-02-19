package com.example.hike_menu_calculator.ui.create_hike_equipment


import androidx.recyclerview.widget.DiffUtil
import com.example.hike_menu_calculator.dataBase.Equipment
import com.example.hike_menu_calculator.dataBase.Participants

class CreateEquipmentDiffUtilCallback(
    private val oldElement: List<Equipment>,
    private val newElement: List<Equipment>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldElement.size
    override fun getNewListSize(): Int = newElement.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldElement[oldItemPosition].id == newElement[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldElement[oldItemPosition].equipmentInTheCampaign == newElement[newItemPosition].equipmentInTheCampaign


}
