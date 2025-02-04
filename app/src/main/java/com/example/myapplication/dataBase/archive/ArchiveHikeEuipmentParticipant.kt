package com.example.myapplication.dataBase.archive

import androidx.room.Embedded
import androidx.room.Relation

data class ArchiveHikeEuipmentParticipant(
    @Embedded
    val participants: ArchiveParticipants,
    @Relation(
        entity = ArchiveEquipment::class,
        parentColumn = "id",
        entityColumn = "participantId"
    )
    val listEquipment: List<ArchiveEquipment>,
)
