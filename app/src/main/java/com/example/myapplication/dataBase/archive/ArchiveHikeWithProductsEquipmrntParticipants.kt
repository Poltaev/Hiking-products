package com.example.myapplication.dataBase.archive

import androidx.room.Embedded
import androidx.room.Relation

data class ArchiveHikeWithProductsEquipmrntParticipants(
    @Embedded
    val hike: ArchiveHike,
    @Relation(
        entity = ArchiveParticipants::class,
        parentColumn = "id",
        entityColumn = "hikeId"
    )
    val listParticipants: List<ArchiveParticipants>,
    @Relation(
        entity = ArchiveEquipment::class,
        parentColumn = "id",
        entityColumn = "hikeId"
    )
    val listEquipment: List<ArchiveEquipment>,
    @Relation(
        entity = ArchiveHikeMealIntakeSheet::class,
        parentColumn = "id",
        entityColumn = "hikeId"
    )
    val listMealIntakeSheet: List<ArchiveHikeMealIntakeSheet>
)
