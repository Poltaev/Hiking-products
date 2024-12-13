package com.example.myapplication.dataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface HikeDao {
    // Products
    @Query("SELECT * FROM Products")
     fun getAllProductsFlow(): Flow<List<Products>>

    @Query("SELECT * FROM Products")
    fun getAllListProducts(): List<Products>

    @Insert
    suspend fun insertOneProducts(products: Products)

    @Delete
    suspend fun deleteOneProducts(products: Products)

    @Update
    suspend fun updateOneProducts(products: Products)

    // Equipment
    @Query("SELECT * FROM Equipment")
    fun getAllEquipmentFlow(): Flow<List<Equipment>>

    @Query("SELECT * FROM Equipment")
    fun getAllListEquipment(): List<Equipment>

    @Insert
    suspend fun insertOneEquipment(equipment: Equipment)

    @Delete
    suspend fun deleteOneEquipment(equipment: Equipment)

    @Update
    suspend fun updateOneEquipment(equipment: Equipment)

    // Participants
    @Query("SELECT * FROM Participants")
    fun getAllParticipantsFlow(): Flow<List<Participants>>

    @Query("SELECT * FROM Participants")
    fun getAllListParticipants(): List<Participants>

    @Insert
    suspend fun insertOneParticipant(participant: Participants)

    @Delete
    suspend fun deleteOneParticipant(participant: Participants)

    @Update
    suspend fun updateOneParticipant(participant: Participants)

}