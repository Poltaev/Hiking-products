package com.example.myapplication.dataBase.thisHike

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class ThisHikeParticipantWithProducts(
    @Embedded
    val participant: ThisHikeParticipants,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            ThisHikeProductsParticipants::class,
            parentColumn = "participant_id",
            entityColumn = "this_products_id"
        )
    )
    val thisHikeProducts: List<ThisHikeProducts>,
)
