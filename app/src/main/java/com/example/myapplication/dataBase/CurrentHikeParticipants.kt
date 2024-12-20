package com.example.myapplication.dataBase

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "current_hike_participants",
    primaryKeys = ["current_hike_id","participants_id"]
)
data class CurrentHikeParticipants(
    @ColumnInfo(name = "current_hike_id")
    val currentHikeId: Int,
    @ColumnInfo(name = "participants_id")
    val participantsId: Int
)
