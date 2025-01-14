package com.example.myapplication.dataBase.thisHike

import androidx.room.ColumnInfo
import androidx.room.Entity


@Entity(
    tableName = "ThisHikeProductsParticipants",
    primaryKeys = ["participant_id","this_products_id"]
)
data class ThisHikeProductsParticipants(
    @ColumnInfo(name = "participant_id")
    val participantId : Int,
    @ColumnInfo(name = "this_products_id")
    val productsId : Int
)
