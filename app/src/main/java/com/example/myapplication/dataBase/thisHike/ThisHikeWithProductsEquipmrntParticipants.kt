package com.example.myapplication.dataBase.thisHike

import androidx.room.Embedded
import androidx.room.Relation

data class ThisHikeWithProductsEquipmrntParticipants(
    @Embedded
    val hike: ThisHike,
    @Relation(
        entity = ThisHikeParticipants::class,
        parentColumn = "id",
        entityColumn = "hikeId"
    )
    val listParticipants: List<ThisHikeParticipants>,
    @Relation(
        entity = ThisHikeEquipment::class,
        parentColumn = "id",
        entityColumn = "hikeId"
    )
    val listEquipment: List<ThisHikeEquipment>,
    @Relation(
        entity = ThisHikeMealIntakeSheet::class,
        parentColumn = "id",
        entityColumn = "hikeId"
    )
    val listMealIntakeSheet: List<ThisHikeMealIntakeSheet>
)
