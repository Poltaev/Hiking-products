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
                    idListTypeProduct.forEach {
                        idProduct = getIdProduct(idProduct, it)
                        idProduct = checkBanProducts(idProduct, idThisHikeProduct)
                        idProduct.shuffle()
                        thisMealProducts = createProductsFromMenu(idProduct, thisMealProducts)
                        idProduct.clear()
                        var couterProducts = 0
                        for (x in 1..numberOfDay) {
                            if (couterProducts > thisMealProducts.size - 1) {
                                couterProducts = 0
                            }
                            upDateThisHikeProductsForMenu(
                                thisMealProducts,
                                couterProducts,
                                numberOfParticipants
                            )
                            var idMealList = 1
                            ThisHikeUseCase(hikeDao).getAllListThisHikeMealIntakeSheet().forEach {
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
                        thisMealProducts.clear()
                    }
                    idListTypeProduct.clear()
                } else {

                }
            }
            removeUnusedProducts()
        }
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
    private suspend fun removeUnusedProducts () {
       val listProducts =  ThisHikeUseCase(hikeDao).getAllListThisHikeProducts()
        listProducts.forEach {
            if (it.remainingWeight == 0){
                ThisHikeUseCase(hikeDao).deleteThisHikeProducts(it)
            }
        }
    }
}