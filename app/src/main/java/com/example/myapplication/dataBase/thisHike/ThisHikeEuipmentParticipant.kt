package com.example.myapplication.dataBase.thisHike

import androidx.room.Embedded
import androidx.room.Relation

data class ThisHikeEuipmentParticipant(
    @Embedded
    val participants: ThisHikeParticipants,
    @Relation(
        entity = ThisHikeEquipment::class,
        parentColumn = "id",
        entityColumn = "participantsId"
    )
    val listEquipment: List<ThisHikeEquipment>,
)
