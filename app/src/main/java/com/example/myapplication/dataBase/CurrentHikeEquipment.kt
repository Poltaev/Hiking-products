package com.example.myapplication.dataBase

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "current_hike_equipment",
    primaryKeys = ["current_hike_id","equipment_id"]
)
data class CurrentHikeEquipment(
    @ColumnInfo(name = "current_hike_id")
    val currentHikeId: Int,
    @ColumnInfo(name = "equipmentCurrentHikeWithParticipants_id")
    val equipmentId: Int
)
