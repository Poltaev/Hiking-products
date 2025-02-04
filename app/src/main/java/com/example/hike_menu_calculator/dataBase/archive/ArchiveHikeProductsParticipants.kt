package com.example.hike_menu_calculator.dataBase.archive

import androidx.room.ColumnInfo
import androidx.room.Entity


@Entity(
    tableName = "ArchiveHikeProductsParticipants",
    primaryKeys = ["participant_id","archive_products_id"]
)
data class ArchiveHikeProductsParticipants(
    @ColumnInfo(name = "participant_id")
    val participantId : Int,
    @ColumnInfo(name = "archive_products_id")
    val productsId : Int
)
