package com.example.hike_menu_calculator.dataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.hike_menu_calculator.dataBase.archive.ArchiveEquipment
import com.example.hike_menu_calculator.dataBase.archive.ArchiveHike
import com.example.hike_menu_calculator.dataBase.archive.ArchiveHikeListIdProductsInMeal
import com.example.hike_menu_calculator.dataBase.archive.ArchiveHikeMealIntakeSheet
import com.example.hike_menu_calculator.dataBase.archive.ArchiveHikeProductsMealList
import com.example.hike_menu_calculator.dataBase.archive.ArchiveHikeProductsParticipants
import com.example.hike_menu_calculator.dataBase.archive.ArchiveParticipants
import com.example.hike_menu_calculator.dataBase.archive.ArchiveProducts
import com.example.hike_menu_calculator.dataBase.archive.ArchiveStorage
import com.example.hike_menu_calculator.dataBase.products.ListProducts
import com.example.hike_menu_calculator.dataBase.products.ListTypeOfProducts
import com.example.hike_menu_calculator.dataBase.products.ProductStorage
import com.example.hike_menu_calculator.dataBase.products.Products
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHike
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeEquipment
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeListIdProductsInMeal
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeMealIntakeSheet
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeParticipants
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeProducts
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeProductsMealList
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeProductsParticipants
import kotlinx.coroutines.flow.Flow

@Dao
interface HikeDao {
    // ArchiveHikeListIdProductsInMeal
    @Query("SELECT * FROM ArchiveHikeListIdProductsInMeal")
    fun getAllArchiveHikeListIdProductsInMealFlow(): Flow<List<ArchiveHikeListIdProductsInMeal>>

    @Query("SELECT * FROM ArchiveHikeListIdProductsInMeal")
    fun getAllArchiveHikeListIdProductsInMeal(): List<ArchiveHikeListIdProductsInMeal>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertArchiveHikeListIdProductsInMeal(ahisHikeListIdProductsInMeal: ArchiveHikeListIdProductsInMeal)

    @Delete
    suspend fun deleteArchiveHikeListIdProductsInMeal(archiveHikeListIdProductsInMeal: ArchiveHikeListIdProductsInMeal)

    @Update
    suspend fun updateArchiveHikeListIdProductsInMeal(archiveHikeListIdProductsInMeal: ArchiveHikeListIdProductsInMeal)

    // ThisHikeListIdProductsInMeal
    @Query("SELECT * FROM ThisHikeListIdProductsInMeal")
    fun getAllThisHikeListIdProductsInMealFlow(): Flow<List<ThisHikeListIdProductsInMeal>>

    @Query("SELECT * FROM ThisHikeListIdProductsInMeal")
    fun getAllThisHikeListIdProductsInMeal(): List<ThisHikeListIdProductsInMeal>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertThisHikeListIdProductsInMeal(thisHikeListIdProductsInMeal: ThisHikeListIdProductsInMeal)

    @Delete
    suspend fun deleteThisHikeListIdProductsInMeal(thisHikeListIdProductsInMeal: ThisHikeListIdProductsInMeal)

    @Update
    suspend fun updateThisHikeListIdProductsInMeal(thisHikeListIdProductsInMeal: ThisHikeListIdProductsInMeal)

    // ThisHike
    @Query("SELECT * FROM ThisHike")
    fun getAllThisHikeFlow(): Flow<List<ThisHike>>

    @Query("SELECT * FROM ThisHike")
    fun getAllListThisHike(): List<ThisHike>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertThisHike(thisHike: ThisHike)

    @Delete
    suspend fun deleteThisHike(thisHike: ThisHike)

    @Update
    suspend fun updateThisHike(thisHike: ThisHike)

    // ThisHikeProducts

    @Query("SELECT * FROM ThisHikeProducts")
    fun getAllThisHikeProductsFlow(): Flow<List<ThisHikeProducts>>

    @Query("SELECT * FROM ThisHikeProducts")
    fun getAllListThisHikeProducts(): List<ThisHikeProducts>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertThisHikeProducts(thisHikeProducts: ThisHikeProducts)

    @Delete
    suspend fun deleteThisHikeProducts(thisHikeProducts: ThisHikeProducts)

    @Update
    suspend fun updateThisHikeProducts(thisHikeProducts: ThisHikeProducts)

    // ThisHikeEquipment

    @Query("SELECT * FROM ThisHikeEquipment")
    fun getAllThisHikeEquipmentFlow(): Flow<List<ThisHikeEquipment>>

    @Query("SELECT * FROM ThisHikeEquipment")
    fun getAllListThisHikeEquipment(): List<ThisHikeEquipment>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertThisHikeEquipment(thisHikeEquipment: ThisHikeEquipment)

    @Delete
    suspend fun deleteThisHikeEquipment(thisHikeEquipment: ThisHikeEquipment)

    @Update
    suspend fun updateThisHikeEquipment(thisHikeEquipment: ThisHikeEquipment)

    // ThisHikeParticipants

    @Query("SELECT * FROM ThisHikeParticipants")
    fun getAllThisHikeParticipantsFlow(): Flow<List<ThisHikeParticipants>>

    @Query("SELECT * FROM ThisHikeParticipants")
    fun getAllListThisHikeParticipants(): List<ThisHikeParticipants>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertThisHikeParticipants(thisHikeParticipants: ThisHikeParticipants)

    @Delete
    suspend fun deleteThisHikeParticipants(thisHikeParticipants: ThisHikeParticipants)

    @Update
    suspend fun updateThisHikeParticipants(thisHikeParticipants: ThisHikeParticipants)

    // ThisHikeMealIntakeSheet

    @Query("SELECT * FROM ThisHikeMealIntakeSheet")
    fun getAllThisHikeMealIntakeSheetFlow(): Flow<List<ThisHikeMealIntakeSheet>>

    @Query("SELECT * FROM ThisHikeMealIntakeSheet")
    fun getAllListThisHikeMealIntakeSheet(): List<ThisHikeMealIntakeSheet>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertThisHikeMealIntakeSheet(thisHikeMealIntakeSheet: ThisHikeMealIntakeSheet)

    @Delete
    suspend fun deleteThisHikeMealIntakeSheet(thisHikeMealIntakeSheet: ThisHikeMealIntakeSheet)

    @Update
    suspend fun updateThisHikeMealIntakeSheet(thisHikeMealIntakeSheet: ThisHikeMealIntakeSheet)

    // ThisHikeProductsMealList
    @Transaction
    @Query("SELECT * FROM ThisHikeProductsMealList")
    fun getAllThisHikeProductsMealListFlow(): Flow<List<ThisHikeProductsMealList>>

    @Query("SELECT * FROM ThisHikeProductsMealList")
    fun getAllListThisHikeProductsMealList(): List<ThisHikeProductsMealList>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertThisHikeProductsMealList(thisHikeProductsMealList: ThisHikeProductsMealList)

    @Delete
    suspend fun deleteThisHikeProductsMealList(thisHikeProductsMealList: ThisHikeProductsMealList)

    @Update
    suspend fun updateThisHikeProductsMealList(thisHikeProductsMealList: ThisHikeProductsMealList)

    // ThisHikeProductsParticipants
    @Transaction
    @Query("SELECT * FROM ThisHikeProductsParticipants")
    fun getAllThisHikeProductsParticipantsFlow(): Flow<List<ThisHikeProductsParticipants>>

    @Query("SELECT * FROM ThisHikeProductsParticipants")
    fun getAllListThisHikeProductsParticipants(): List<ThisHikeProductsParticipants>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertThisHikeProductsParticipants(thisHikeProductsParticipants: ThisHikeProductsParticipants)

    @Delete
    suspend fun deleteThisHikeProductsParticipants(thisHikeProductsParticipants: ThisHikeProductsParticipants)

    @Update
    suspend fun updateThisHikeProductsParticipants(thisHikeProductsParticipants: ThisHikeProductsParticipants)


    // ArchiveProducts
    @Query("SELECT * FROM ArchiveProducts")
    fun getAllArchiveProductsFlow(): Flow<List<ArchiveProducts>>

    @Query("SELECT * FROM ArchiveProducts")
    fun getAllListArchiveProducts(): List<ArchiveProducts>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertArchiveProducts(archiveProducts: ArchiveProducts)

    @Delete
    suspend fun deleteArchiveProducts(archiveProducts: ArchiveProducts)

    @Update
    suspend fun updateOneArchiveProducts(archiveProducts: ArchiveProducts)

    // ArchiveEquipment

    @Query("SELECT * FROM ArchiveEquipment")
    fun getAllArchiveEquipmentFlow(): Flow<List<ArchiveEquipment>>

    @Query("SELECT * FROM ArchiveEquipment")
    fun getAllListArchiveEquipment(): List<ArchiveEquipment>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertArchiveEquipment(archiveEquipment: ArchiveEquipment)

    @Delete
    suspend fun deleteArchiveEquipment(archiveEquipment: ArchiveEquipment)

    @Update
    suspend fun updateArchiveEquipment(archiveEquipment: ArchiveEquipment)

    // ArchiveParticipants

    @Query("SELECT * FROM ArchiveParticipants")
    fun getAllArchiveParticipantsFlow(): Flow<List<ArchiveParticipants>>

    @Query("SELECT * FROM ArchiveParticipants")
    fun getAllListArchiveParticipants(): List<ArchiveParticipants>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertArchiveParticipants(archiveParticipants: ArchiveParticipants)

    @Delete
    suspend fun deleteArchiveParticipants(archiveParticipants: ArchiveParticipants)

    @Update
    suspend fun updateArchiveParticipants(archiveParticipants: ArchiveParticipants)

    // ArchiveHike

    @Query("SELECT * FROM ArchiveHike")
    fun getAllArchiveHikeFlow(): Flow<List<ArchiveHike>>

    @Query("SELECT * FROM ArchiveHike")
    fun getAllListArchiveHike(): List<ArchiveHike>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertArchiveHike(archiveHike: ArchiveHike)

    @Delete
    suspend fun deleteArchiveHike(archiveHike: ArchiveHike)

    @Update
    suspend fun updateArchiveHike(archiveHike: ArchiveHike)

    // ArchiveStorage

    @Query("SELECT * FROM ArchiveStorage")
    fun getAllArchiveStorageFlow(): Flow<List<ArchiveStorage>>

    @Query("SELECT * FROM ArchiveStorage")
    fun getAllListArchiveStorage(): List<ArchiveStorage>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertArchiveStorage(archiveStorage: ArchiveStorage)

    @Delete
    suspend fun deleteArchiveStorage(archiveStorage: ArchiveStorage)

    @Update
    suspend fun updateArchiveStorage(archiveStorage: ArchiveStorage)

    // ArchiveHikeMealIntakeSheet

    @Query("SELECT * FROM ArchiveHikeMealIntakeSheet")
    fun getAllArchiveHikeMealIntakeSheetFlow(): Flow<List<ArchiveHikeMealIntakeSheet>>

    @Query("SELECT * FROM ArchiveHikeMealIntakeSheet")
    fun getAllListArchiveHikeMealIntakeSheet(): List<ArchiveHikeMealIntakeSheet>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertArchiveHikeMealIntakeSheet(archiveHikeMealIntakeSheet: ArchiveHikeMealIntakeSheet)

    @Delete
    suspend fun deleteArchiveHikeMealIntakeSheet(archiveHikeMealIntakeSheet: ArchiveHikeMealIntakeSheet)

    @Update
    suspend fun updateArchiveHikeMealIntakeSheet(archiveHikeMealIntakeSheet: ArchiveHikeMealIntakeSheet)

    // ArchiveHikeProductsParticipants

    @Transaction
    @Query("SELECT * FROM ArchiveHikeProductsParticipants")
    fun getAllArchiveHikeProductsParticipantsFlow(): Flow<List<ArchiveHikeProductsParticipants>>

    @Query("SELECT * FROM ArchiveHikeProductsParticipants")
    fun getAllListArchiveHikeProductsParticipants(): List<ArchiveHikeProductsParticipants>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertArchiveHikeProductsParticipants(archiveHikeProductsParticipants: ArchiveHikeProductsParticipants)

    @Delete
    suspend fun deleteArchiveHikeProductsParticipants(archiveHikeProductsParticipants: ArchiveHikeProductsParticipants)

    @Update
    suspend fun updateArchiveHikeProductsParticipants(archiveHikeProductsParticipants: ArchiveHikeProductsParticipants)

    // ArchiveHikeProductsMealList

    @Transaction
    @Query("SELECT * FROM ArchiveHikeProductsMealList")
    fun getAllArchiveHikeProductsMealListFlow(): Flow<List<ArchiveHikeProductsMealList>>

    @Query("SELECT * FROM ArchiveHikeProductsMealList")
    fun getAllListArchiveHikeProductsMealList(): List<ArchiveHikeProductsMealList>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertArchiveHikeProductsMealList(archiveHikeProductsMealList: ArchiveHikeProductsMealList)

    @Delete
    suspend fun deleteArchiveHikeProductsMealList(archiveHikeProductsMealList: ArchiveHikeProductsMealList)

    @Update
    suspend fun updateArchiveHikeProductsMealList(archiveHikeProductsMealList: ArchiveHikeProductsMealList)

    // List_With_Products
    @Transaction
    @Query("SELECT * FROM List_With_Products")
    fun getAllListProductsFlow(): Flow<List<ListProducts>>

    @Query("SELECT * FROM List_With_Products")
    fun getAllListListProducts(): List<ListProducts>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOneListProducts(storegeWithList: ListProducts)

    @Delete
    suspend fun deleteOneListProducts(storegeWithList: ListProducts)

    @Update
    suspend fun updateOneListProducts(storegeWithList: ListProducts)

    // ListTypeOfProducts
    @Query("SELECT * FROM List_Type_Of_Products")
    fun getAllListTypeOfProductsFlow(): Flow<List<ListTypeOfProducts>>

    @Query("SELECT * FROM List_Type_Of_Products")
    fun getAllListListTypeOfProducts(): List<ListTypeOfProducts>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOneListTypeOfProducts(listTypeOfProducts: ListTypeOfProducts)

    @Delete
    suspend fun deleteOneListTypeOfProducts(listTypeOfProducts: ListTypeOfProducts)

    @Update
    suspend fun updateOneListTypeOfProducts(listTypeOfProducts: ListTypeOfProducts)

    // ProductStorage
    @Query("SELECT * FROM Product_Storage")
    fun getAllProductStorageFlow(): Flow<List<ProductStorage>>

    @Query("SELECT * FROM Product_Storage")
    fun getAllListProductStorage(): List<ProductStorage>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOneProductStorage(productStorage: ProductStorage)

    @Delete
    suspend fun deleteOneProductStorage(productStorage: ProductStorage)

    @Update
    suspend fun updateOneProductStorage(productStorage: ProductStorage)

    // Products
    @Query("SELECT * FROM Products")
    fun getAllProductsFlow(): Flow<List<Products>>

    @Query("SELECT * FROM Products")
    fun getAllListProducts(): List<Products>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOneParticipant(participant: Participants)

    @Delete
    suspend fun deleteOneParticipant(participant: Participants)

    @Update
    suspend fun updateOneParticipant(participant: Participants)

}