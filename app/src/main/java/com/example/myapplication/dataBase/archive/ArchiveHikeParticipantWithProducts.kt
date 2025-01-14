package com.example.myapplication.dataBase.archive

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class ArchiveHikeParticipantWithProducts(
    @Embedded
    val participant: ArchiveParticipants,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            ArchiveHikeProductsParticipants::class,
            parentColumn = "participant_id",
            entityColumn = "archive_products_id"
        )
    )
    val thisHikeProducts: List<ArchiveProducts>,
)
