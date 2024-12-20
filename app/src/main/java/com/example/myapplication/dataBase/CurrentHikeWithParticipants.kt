package com.example.myapplication.dataBase

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class CurrentHikeWithParticipants(
    @Embedded
    val currentHike: CurrentHike,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            CurrentHikeParticipants::class,
            parentColumn = "current_hike_id",
            entityColumn = "participants_id"
        )
    )
    val participants: List<Participants>
)
