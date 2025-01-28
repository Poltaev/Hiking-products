package com.example.myapplication.ui.create_hike_products

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.myapplication.dataBase.Equipment
import com.example.myapplication.dataBase.HikeDao
import com.example.myapplication.dataBase.products.ListProducts
import com.example.myapplication.dataBase.products.ListTypeOfProducts
import com.example.myapplication.dataBase.products.Products
import com.example.myapplication.dataBase.thisHike.ThisHike
import com.example.myapplication.dataBase.thisHike.ThisHikeMealIntakeSheet
import com.example.myapplication.dataBase.thisHike.ThisHikeParticipants
import com.example.myapplication.dataBase.thisHike.ThisHikeProducts
import com.example.myapplication.dataBase.thisHike.ThisHikeProductsMealList
import com.example.myapplication.domain.ParticipantsEquipmentUseCase
import com.example.myapplication.domain.ProductsUseCase
import com.example.myapplication.domain.ThisHikeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CreateHikeProductsiewModel(private val hikeDao: HikeDao) : ViewModel() {

    suspend fun getAllProductsFlow(): Flow<List<Products>> {
        return ProductsUseCase(hikeDao).getAllProductsFlow()
    }

    suspend fun upDateProducts(
        id: Int,
        name: String,
        weightForPerson: Int,
        packageWeight: Int,
        theVolumeItem: Boolean,
        weWillUseItInTheCurrentCampaign: Boolean,
    ) {
        return ProductsUseCase(hikeDao).upDateProducts(
            id = id,
            name = name,
            weightForPerson = weightForPerson,
            packageWeight = packageWeight,
            theVolumeItem = theVolumeItem,
            weWillUseItInTheCurrentCampaign = weWillUseItInTheCurrentCampaign
        )
    }

    suspend fun getAllProductsList(): List<Products> {
        return ProductsUseCase(hikeDao).getAllProductsList()
    }

    fun createAHikeProducts(typeMeal: List<String>) {
        viewModelScope.launch(Dispatchers.IO) {
            createThisHikeProduct()
            val numberOfDay = ThisHikeUseCase(hikeDao).getAllListThisHike()[0].numberOfDay
            val numberOfSnacks =
                ThisHikeUseCase(hikeDao).getAllListThisHike()[0].numberOfSnacksInDay
            val numberOfParticipants =
                ThisHikeUseCase(hikeDao).getAllListThisHikeParticipants().size
            var idThisHikeProduct = mutableListOf<Int>()
            var idProduct = mutableListOf<Int>()
            var idListTypeProduct = mutableListOf<Int>()
            var thisMealProducts = mutableListOf<ThisHikeProducts>()
            createThisHikeMenuList(numberOfDay, numberOfSnacks, typeMeal)
            idThisHikeProduct = getIdThisHikeProduct(idThisHikeProduct)
            typeMeal.forEach { typeMeal ->
                if (typeMeal != "Перекус") {
                    idListTypeProduct = getIdThisHikeProductList(idListTypeProduct, typeMeal)
                    if (idThisHikeProduct.size != 0) {
                        idListTypeProduct.forEach {
                            idProduct = getIdProduct(idProduct, it)
                            idProduct = checkBanProducts(idProduct, idThisHikeProduct)
                            idProduct.shuffle()
                            var couterProducts = 0
                            for (x in 1..numberOfDay) {
                                if (couterProducts > thisMealProducts.size - 1) {
                                    couterProducts = 0
                                }
                                thisMealProducts.clear()
                                thisMealProducts = createProductsFromMenu(idProduct, thisMealProducts)
                                upDateThisHikeProductsForMenu(
                                    thisMealProducts,
                                    couterProducts,
                                    numberOfParticipants
                                )
                                var idMealList = 1
                                ThisHikeUseCase(hikeDao).getAllListThisHikeMealIntakeSheet()
                                    .forEach {
                                        if (it.numberOfday == x) {
                                            if (it.typeMeal == typeMeal) {
                                                idMealList = it.id
                                            }
                                        }
                                    }
                                addThisHikeProductsMealList(
                                    idMealList,
                                    thisMealProducts,
                                    couterProducts
                                )
                                couterProducts++
                            }
                            idProduct.clear()
                            thisMealProducts.clear()
                        }
                    }
                    idListTypeProduct.clear()
                } else {
                    idListTypeProduct = getIdThisHikeProductList(idListTypeProduct, typeMeal)
                    if (idThisHikeProduct.size != 0) {
                        idListTypeProduct.forEach {
                            getIdSnack(it).forEach { item ->
                                idProduct.add(item)
                            }
                        }
                        idListTypeProduct.clear()
                        idProduct = checkBanProducts(idProduct, idThisHikeProduct)
                        idProduct.shuffle()


                        var couterProducts = 0
                        for (x in 1..getNumberSnackList()) {
                            if (couterProducts > thisMealProducts.size - 1) {
                                couterProducts = 0
                            }
                            thisMealProducts.clear()
                            thisMealProducts = createProductsFromMenu(idProduct, thisMealProducts)
                            upDateThisHikeProductsForMenu(
                                thisMealProducts,
                                couterProducts,
                                numberOfParticipants
                            )
                            addThisHikeSnackMealList(
                                x,
                                thisMealProducts,
                                couterProducts
                            )
                            couterProducts++
                        }
                    }
                    idProduct.clear()

                }
            }
            removeUnusedProducts()
            addThisHikeProductsToBackpack()
        }
    }

    private suspend fun getNumberSnackList(): Int {
        var counterList = mutableListOf<ThisHikeMealIntakeSheet>()
        ThisHikeUseCase(hikeDao).getAllListThisHikeMealIntakeSheet().forEach {
            if (it.typeMeal == "Перекус") {
                counterList.add(it)
            }
        }
        return counterList.size
    }

    private suspend fun createThisHikeProduct() {
        val listProducts = ProductsUseCase(hikeDao).getAllProductsList()
        listProducts.forEach {
            if (it.weWillUseItInTheCurrentCampaign) {
                ThisHikeUseCase(hikeDao).insertThisHikeProducts(
                    it.id,
                    it.name,
                    it.weightForPerson,
                    it.packageWeight,
                    0,
                    0,
                    0,
                    false,
                    false,
                    false,
                    ""
                )
            }
        }
    }

    private suspend fun createThisHikeMenuList(
        numberOfDay: Int,
        numberOfSnacks: Int,
        typeMeal: List<String>,
    ) {
        var counter = 1
        for (x in 1..numberOfDay) {
            typeMeal.forEach {
                if (it == "Перекус") {
                    if (numberOfSnacks != 0) {
                        for (y in 1..numberOfSnacks) {
                            ThisHikeUseCase(hikeDao).insertThisHikeMealIntakeSheet(
                                counter,
                                1,
                                "${it} ${y} День ${x}",
                                x,
                                it
                            )
                            counter++
                        }
                    }
                    counter++
                } else {
                    Log.i("createSnack", "${it}")
                    ThisHikeUseCase(hikeDao).insertThisHikeMealIntakeSheet(
                        counter,
                        1,
                        "${it} День ${x}",
                        x,
                        it
                    )
                    counter++
                }
            }
        }
    }

    private suspend fun getIdThisHikeProduct(idThisHikeProduct: MutableList<Int>): MutableList<Int> {
        ThisHikeUseCase(hikeDao).getAllListThisHikeProducts().forEach {
            idThisHikeProduct.add(it.id)
        }
        return idThisHikeProduct
    }

    private suspend fun getIdThisHikeProductList(
        idListTypeProduct: MutableList<Int>,
        typeMeal: String,
    ): MutableList<Int> {
        ProductsUseCase(hikeDao).getAllListTypeOfProductsList().forEach { item ->
            if (item.typeOfMeal == typeMeal) {
                idListTypeProduct.add(item.id)
            }
        }
        return idListTypeProduct
    }

    private suspend fun getIdProduct(idProduct: MutableList<Int>, it: Int): MutableList<Int> {
        ProductsUseCase(hikeDao).getAllListProductsList().forEach { item ->
            if (item.listId == it) {
                idProduct.add(item.productsId)
            }
        }
        return idProduct
    }

    private suspend fun getIdSnack(it: Int): MutableList<Int> {
        val idProduct = mutableListOf<Int>()
        ProductsUseCase(hikeDao).getAllListProductsList().forEach { item ->
            if (item.listId == it) {
                idProduct.add(item.productsId)
            }
        }
        return idProduct
    }

    private fun checkBanProducts(
        idProduct: MutableList<Int>,
        idThisHikeProduct: MutableList<Int>,
    ): MutableList<Int> {
        idProduct.forEach {
            if (idThisHikeProduct.contains(it) == false) {
                idProduct.remove(it)
            }
        }
        return idProduct
    }

    private suspend fun createProductsFromMenu(
        idProduct: MutableList<Int>,
        thisMealProducts: MutableList<ThisHikeProducts>,
    ): MutableList<ThisHikeProducts> {
        ThisHikeUseCase(hikeDao).getAllListThisHikeProducts().forEach { item ->
            idProduct.forEach {
                if (item.id == it) {
                    thisMealProducts.add(item)
                }
            }
        }
        return thisMealProducts
    }

    private suspend fun upDateThisHikeProductsForMenu(
        thisMealProducts: MutableList<ThisHikeProducts>,
        couterProducts: Int,
        numberOfParticipants: Int,
    ) {
        val theWeightOfOneMeal =
            thisMealProducts[couterProducts].weightForPerson * numberOfParticipants
        val weightOnTheHike =
            thisMealProducts[couterProducts].weightOnTheHike + theWeightOfOneMeal
        ThisHikeUseCase(hikeDao).updateThisHikeProducts(
            thisMealProducts[couterProducts].id,
            thisMealProducts[couterProducts].name,
            thisMealProducts[couterProducts].weightForPerson,
            thisMealProducts[couterProducts].packageWeight,
            theWeightOfOneMeal,
            weightOnTheHike,
            weightOnTheHike,
            thisMealProducts[couterProducts].partiallyAssembled,
            thisMealProducts[couterProducts].fullyAssembled,
            thisMealProducts[couterProducts].theVolumeItem,
            thisMealProducts[couterProducts].comment
        )
    }

    private suspend fun addThisHikeProductsMealList(
        idMealList: Int,
        thisMealProducts: MutableList<ThisHikeProducts>,
        couterProducts: Int,
    ) {
        ThisHikeUseCase(hikeDao).insertThisHikeProductsMealList(
            idMealList,
            thisMealProducts[couterProducts].id
        )
    }

    private suspend fun addThisHikeSnackMealList(
        counterListSnack: Int,
        thisMealProducts: MutableList<ThisHikeProducts>,
        couterProducts: Int,
    ) {
        var counterList = mutableListOf<ThisHikeMealIntakeSheet>()
        ThisHikeUseCase(hikeDao).getAllListThisHikeMealIntakeSheet().forEach {
            if (it.typeMeal == "Перекус") {
                counterList.add(it)
            }
        }
        ThisHikeUseCase(hikeDao).insertThisHikeProductsMealList(
            counterList[counterListSnack - 1].id,
            thisMealProducts[couterProducts].id
        )
    }

    private suspend fun removeUnusedProducts() {
        val listProducts = ThisHikeUseCase(hikeDao).getAllListThisHikeProducts()
        listProducts.forEach {
            if (it.remainingWeight == 0) {
                ThisHikeUseCase(hikeDao).deleteThisHikeProducts(it)
            }
        }
    }
    fun addThisHikeProductsToBackpack() {
        viewModelScope.launch(Dispatchers.IO) {
            ThisHikeUseCase(hikeDao).getAllListThisHikeProducts().forEach {
                    val listParticipant = ThisHikeUseCase(hikeDao).getAllListThisHikeParticipants()
                    val listParticipantMaxWeight = mutableListOf<Double>()
                    val listParticipantWeight = mutableListOf<Double>()
                    listParticipant.forEach {
                        listParticipantMaxWeight.add(it.maximumPortableWeight.toDouble())
                        listParticipantWeight.add(it.weightWithLoad.toDouble())
                    }
                    val theLightestBackpackPossible = mutableListOf<Double>()
                    for (i in 0..listParticipant.size - 1) {
                        val sum =
                            ((listParticipantMaxWeight[i] - listParticipantWeight[i]) / listParticipantMaxWeight[i]) * 100
                        theLightestBackpackPossible.add(sum)
                    }
                    val theLightestBackpackPossibleSort = theLightestBackpackPossible.sortedDescending()
                    val returnIndexPartisipant = theLightestBackpackPossible.indexOf(theLightestBackpackPossibleSort[0])
                    ThisHikeUseCase(hikeDao).insertThisHikeProductsParticipants(
                        listParticipant[returnIndexPartisipant].id,
                        it.id
                    )
                    ThisHikeUseCase(hikeDao).updateThisHikeParticipants(
                        listParticipant[returnIndexPartisipant].id,
                        listParticipant[returnIndexPartisipant].hikeId,
                        listParticipant[returnIndexPartisipant].photo,
                        listParticipant[returnIndexPartisipant].firstName,
                        listParticipant[returnIndexPartisipant].lastName,
                        listParticipant[returnIndexPartisipant].gender,
                        listParticipant[returnIndexPartisipant].age,
                        listParticipant[returnIndexPartisipant].maximumPortableWeight,
                        listParticipant[returnIndexPartisipant].weightOfPersonalItems,
                        listParticipant[returnIndexPartisipant].weightWithLoad + it.remainingWeight,
                        listParticipant[returnIndexPartisipant].comment
                    )
            }
        }
    }
}