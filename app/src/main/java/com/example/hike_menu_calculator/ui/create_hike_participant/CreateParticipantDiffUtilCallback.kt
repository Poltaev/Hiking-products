package com.example.hike_menu_calculator.ui.create_hike_participant


import androidx.recyclerview.widget.DiffUtil
import com.example.hike_menu_calculator.dataBase.Participants

class CreateParticipantDiffUtilCallback(
    private val oldElement: List<Participants>,
    private val newElement: List<Participants>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldElement.size
    override fun getNewListSize(): Int = newElement.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldElement[oldItemPosition].id == newElement[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldElement[oldItemPosition].participationInTheCampaign == newElement[newItemPosition].participationInTheCampaign


}
