package com.example.hike_menu_calculator.dataBase.archive

import androidx.room.Embedded
import androidx.room.Relation

data class ArhiveStorageWihtHike(
    @Embedded
    val strorage : ArchiveStorage,
    @Relation(
        entity = ArchiveHike::class,
        parentColumn = "id",
        entityColumn = "storageId"
    )
    val acrhiveHike : List<ArchiveHike>
)
