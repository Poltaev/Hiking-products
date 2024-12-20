package com.example.myapplication.dataBase

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class CurrentHikeWithEquipment(
    @Embedded
    val currentHike: CurrentHike,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            CurrentHikeEquipment::class,
            parentColumn = "current_hike_id",
            entityColumn = "equipment_id"
        )
    )
    val equipment: List<Equipment>
)
