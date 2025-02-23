package com.example.hike_menu_calculator.ui.create_hike_products

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hike_menu_calculator.dataBase.HikeDao
import com.example.hike_menu_calculator.dataBase.products.Products
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeMealIntakeSheet
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeProducts
import com.example.hike_menu_calculator.domain.ArchiveHikeUseCase
import com.example.hike_menu_calculator.domain.ProductsUseCase
import com.example.hike_menu_calculator.domain.ThisHikeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class CreateHikeProductsiewModel(private val hikeDao: HikeDao) : ViewModel() {

    suspend fun transferTheTripToTheArchive() {
        viewModelScope.launch(Dispatchers.IO) {
            val idArchiveHike: Int
            val idNewParticipant: MutableList<Int>
            val idNewMeal: MutableList<Int>
            val idNewProducts: MutableList<Int>
            if (ArchiveHikeUseCase(hikeDao).getAllListArchiveStorage().size == 0) {
                ArchiveHikeUseCase(hikeDao).insertArchiveStorage(
                    1,
                    "Первое и единственное хранилище"
                )
            }
            runBlocking(Dispatchers.IO) {
                idArchiveHike = addArchiveHike()
            }
            runBlocking(Dispatchers.IO) {
                idNewParticipant = addArchiveHikeParticipant(idArchiveHike)
            }
            runBlocking(Dispatchers.IO) {
                idNewMeal = addArchiveHikeMeal(idArchiveHike)
            }
            runBlocking(Dispatchers.IO) {
                idNewProducts = addArchiveHikeProducts()
            }
            runBlocking(Dispatchers.IO) {
                addArchiveHikeProductsParticipant(idNewParticipant, idNewProducts)
            }
            runBlocking(Dispatchers.IO) {
                addArchiveHikeProductsMeal(idNewMeal, idNewProducts)
            }
        }
    }

    suspend fun addArchiveHikeProductsMeal(
        idNewMeal: MutableList<Int>,
        idNewProducts: MutableList<Int>,
    ) {
        for (indexMeal in 0..ThisHikeUseCase(hikeDao).getAllListThisHikeMealIntakeSheet().size - 1) {
            ThisHikeUseCase(hikeDao).getAllListThisHikeProductsMealList()
                .forEach { meal ->
                    if (meal.mealIntakeId == ThisHikeUseCase(hikeDao).getAllListThisHikeMealIntakeSheet()[indexMeal].id) {
                        ThisHikeUseCase(hikeDao).getAllListThisHikeProducts().forEach { product ->
                            if (meal.productsId == product.id) {
                                val indexProduct =
                                    ThisHikeUseCase(hikeDao).getAllListThisHikeProducts()
                                        .indexOf(product)
                                var counter = 0
                                ThisHikeUseCase(hikeDao).getAllThisHikeListIdProductsInMeal().forEach { idProductsInMeal ->
                                    if (idProductsInMeal.idMeal == meal.mealIntakeId){
                                        if (idProductsInMeal.idProductsInMeal == product.id){
                                            counter ++
                                        }
                                    }
                                }
                                for (x in 1 .. counter){
                                    if (ArchiveHikeUseCase(hikeDao).getAllArchiveHikeListIdProductsInMeal().size == 0) {
                                        ArchiveHikeUseCase(hikeDao).insertArchiveHikeListIdProductsInMeal(
                                            1,
                                            idNewMeal[indexMeal],
                                            idNewProducts[indexProduct],
                                            product.name
                                        )
                                    } else {
                                        val idProductsMeal = ArchiveHikeUseCase(hikeDao).getAllArchiveHikeListIdProductsInMeal().last().id + 1
                                        ArchiveHikeUseCase(hikeDao).insertArchiveHikeListIdProductsInMeal(
                                            idProductsMeal,
                                            idNewMeal[indexMeal],
                                            idNewProducts[indexProduct],
                                            product.name
                                        )
                                    }
                                }
                                ArchiveHikeUseCase(hikeDao).insertArchiveHikeProductsMealList(
                                    idNewMeal[indexMeal],
                                    idNewProducts[indexProduct]
                                )
                            }
                        }
                    }
                }
        }
    }

    suspend fun addArchiveHikeProductsParticipant(
        idNewParticipant: MutableList<Int>,
        idNewProducts: MutableList<Int>,
    ) {
        for (indexParticipant in 0..ThisHikeUseCase(hikeDao).getAllListThisHikeParticipants().size - 1) {
            ThisHikeUseCase(hikeDao).getAllListThisHikeProductsParticipants()
                .forEach { productParticipant ->
                    if (productParticipant.participantId == ThisHikeUseCase(hikeDao).getAllListThisHikeParticipants()[indexParticipant].id) {
                        ThisHikeUseCase(hikeDao).getAllListThisHikeProducts().forEach { product ->
                            if (productParticipant.productsId == product.id) {
                                val indexProduct =
                                    ThisHikeUseCase(hikeDao).getAllListThisHikeProducts()
                                        .indexOf(product)
                                ArchiveHikeUseCase(hikeDao).insertArchiveHikeProductsParticipants(
                                    idNewParticipant[indexParticipant],
                                    idNewProducts[indexProduct]
                                )
                            }
                        }

                    }
                }
        }
    }

    suspend fun addArchiveHikeProducts(): MutableList<Int> {
        val idNewProducts = mutableListOf<Int>()
        ThisHikeUseCase(hikeDao).getAllListThisHikeProducts()
            .forEach { products ->
                if (ArchiveHikeUseCase(hikeDao).getAllListArchiveProducts().size == 0) {
                    ArchiveHikeUseCase(hikeDao).insertArchiveProducts(
                        1,
                        products.name,
                        products.weightForPerson,
                        products.packageWeight,
                        products.theWeightOfOneMeal,
                        products.weightOnTheHike,
                        products.remainingWeight,
                        products.partiallyAssembled,
                        products.fullyAssembled,
                        products.theVolumeItem,
                        products.theSoleOwner,
                        products.nameOwner,
                        products.idOwner,
                        products.useTheWholePackInOneMeal,
                        products.comment

                    )
                    idNewProducts.add(1)
                } else {
                    val newId = ArchiveHikeUseCase(hikeDao).getAllListArchiveProducts()
                        .last().id + 1
                    ArchiveHikeUseCase(hikeDao).insertArchiveProducts(
                        newId,
                        products.name,
                        products.weightForPerson,
                        products.packageWeight,
                        products.theWeightOfOneMeal,
                        products.weightOnTheHike,
                        products.remainingWeight,
                        products.partiallyAssembled,
                        products.fullyAssembled,
                        products.theVolumeItem,
                        products.theSoleOwner,
                        products.nameOwner,
                        products.idOwner,
                        products.useTheWholePackInOneMeal,
                        products.comment
                    )
                    idNewProducts.add(
                        newId
                    )
                }
            }
        return idNewProducts
    }

    suspend fun addArchiveHikeMeal(idArchiveHike: Int): MutableList<Int> {
        val idNewMeal = mutableListOf<Int>()
        ThisHikeUseCase(hikeDao).getAllListThisHikeMealIntakeSheet()
            .forEach { meal ->
                if (ArchiveHikeUseCase(hikeDao).getAllListArchiveHikeMealIntakeSheet().size == 0) {
                    ArchiveHikeUseCase(hikeDao).insertArchiveHikeMealIntakeSheet(
                        1,
                        idArchiveHike,
                        meal.name,
                        meal.numberOfday,
                        meal.typeMeal
                    )
                    idNewMeal.add(1)
                } else {
                    val newId = ArchiveHikeUseCase(hikeDao).getAllListArchiveHikeMealIntakeSheet()
                        .last().id + 1
                    ArchiveHikeUseCase(hikeDao).insertArchiveHikeMealIntakeSheet(
                        newId,
                        idArchiveHike,
                        meal.name,
                        meal.numberOfday,
                        meal.typeMeal
                    )
                    idNewMeal.add(
                        newId
                    )
                }
            }
        return idNewMeal
    }

    suspend fun addArchiveHikeParticipant(idArchiveHike: Int): MutableList<Int> {
        val idNewParticipant = mutableListOf<Int>()
        ThisHikeUseCase(hikeDao).getAllListThisHikeParticipants()
            .forEach { thisHikeParticipants ->
                if (ArchiveHikeUseCase(hikeDao).getAllListArchiveParticipants().size == 0) {
                    ArchiveHikeUseCase(hikeDao).insertArchiveParticipants(
                        1,
                        idArchiveHike,
                        thisHikeParticipants.photo,
                        thisHikeParticipants.firstName,
                        thisHikeParticipants.lastName,
                        thisHikeParticipants.gender,
                        thisHikeParticipants.age,
                        thisHikeParticipants.maximumPortableWeight,
                        thisHikeParticipants.weightOfPersonalItems,
                        thisHikeParticipants.weightWithLoad,
                        thisHikeParticipants.comment
                    )
                    addEquiopment(thisHikeParticipants.id, 1)
                    idNewParticipant.add(1)
                } else {
                    val newId = ArchiveHikeUseCase(hikeDao).getAllListArchiveParticipants()
                        .last().id + 1
                    ArchiveHikeUseCase(hikeDao).insertArchiveParticipants(
                        newId,
                        idArchiveHike,
                        thisHikeParticipants.photo,
                        thisHikeParticipants.firstName,
                        thisHikeParticipants.lastName,
                        thisHikeParticipants.gender,
                        thisHikeParticipants.age,
                        thisHikeParticipants.maximumPortableWeight,
                        thisHikeParticipants.weightOfPersonalItems,
                        thisHikeParticipants.weightWithLoad,
                        thisHikeParticipants.comment
                    )
                    addEquiopment(
                        thisHikeParticipants.id,
                        newId
                    )
                    idNewParticipant.add(
                        newId
                    )
                }
            }
        return idNewParticipant
    }

    suspend fun addArchiveHike(): Int {
        val idArchiveHike: Int
        if (ArchiveHikeUseCase(hikeDao).getAllListArchiveHike().size == 0) {
            ArchiveHikeUseCase(hikeDao).insertArchiveHike(
                1,
                1,
                ThisHikeUseCase(hikeDao).getAllListThisHike()[0].name,
                ThisHikeUseCase(hikeDao).getAllListThisHike()[0].numberOfDay,
                ThisHikeUseCase(hikeDao).getAllListThisHike()[0].numberOfSnacksInDay
            )
            idArchiveHike = 1
        } else {
            val newId = ArchiveHikeUseCase(hikeDao).getAllListArchiveHike().last().id + 1
            ArchiveHikeUseCase(hikeDao).insertArchiveHike(
                newId,
                1,
                ThisHikeUseCase(hikeDao).getAllListThisHike()[0].name,
                ThisHikeUseCase(hikeDao).getAllListThisHike()[0].numberOfDay,
                ThisHikeUseCase(hikeDao).getAllListThisHike()[0].numberOfSnacksInDay
            )
            idArchiveHike = newId
        }
        return idArchiveHike
    }

    suspend fun addEquiopment(idThisHikeParticipant: Int, idArchiveHikePaticipant: Int) {
        ThisHikeUseCase(hikeDao).getAllListThisHikeEquipment().forEach {
            if (idThisHikeParticipant == it.participantsId) {
                if (ArchiveHikeUseCase(hikeDao).getAllListArchiveEquipment().size == 0) {
                    ArchiveHikeUseCase(hikeDao).insertArchiveEquipment(
                        1,
                        idArchiveHikePaticipant,
                        it.name,
                        it.photo,
                        it.weight,
                        it.partiallyAssembled,
                        it.fullyAssembled,
                        it.theVolumeItem,
                        it.theSoleOwner,
                        it.nameOwner,
                        it.idOwner,
                        it.comment
                    )
                } else {
                    val newId =
                        ArchiveHikeUseCase(hikeDao).getAllListArchiveEquipment().last().id + 1
                    ArchiveHikeUseCase(hikeDao).insertArchiveEquipment(
                        newId,
                        idArchiveHikePaticipant,
                        it.name,
                        it.photo,
                        it.weight,
                        it.partiallyAssembled,
                        it.fullyAssembled,
                        it.theVolumeItem,
                        it.theSoleOwner,
                        it.nameOwner,
                        it.idOwner,
                        it.comment
                    )
                }
            }
        }

    }

    suspend fun getAllProductsFlow(): Flow<List<Products>> {
        return ProductsUseCase(hikeDao).getAllProductsFlow()
    }

    suspend fun upDateProducts(
        id: Int,
        name: String,
        weightForPerson: Int,
        packageWeight: Int,
        theVolumeItem: Boolean,
        theSoleOwner: Boolean,
        nameOwner: String,
        idOwner: Int,
        useTheWholePackInOneMeal: Boolean,
        weWillUseItInTheCurrentCampaign: Boolean,
    ) {
        return ProductsUseCase(hikeDao).upDateProducts(
            id = id,
            name = name,
            weightForPerson = weightForPerson,
            packageWeight = packageWeight,
            theVolumeItem = theVolumeItem,
            theSoleOwner = theSoleOwner,
            nameOwner = nameOwner,
            idOwner = idOwner,
            useTheWholePackInOneMeal = useTheWholePackInOneMeal,
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
                                thisMealProducts =
                                    createProductsFromMenu(idProduct, thisMealProducts)
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
                    false,
                    "nameAll",
                    0,
                    false,
                    "Нажмите сюда, чтобы оставить комментарий к продукту."
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
        val listRemovedIdProducts = mutableListOf<Int>()
        idProduct.forEach {
            if (idThisHikeProduct.contains(it) == false) {
                listRemovedIdProducts.add(it)
            }
        }
        listRemovedIdProducts.forEach {
            idProduct.remove(it)
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
            thisMealProducts[couterProducts].theSoleOwner,
            thisMealProducts[couterProducts].nameOwner,
            thisMealProducts[couterProducts].idOwner,
            thisMealProducts[couterProducts].useTheWholePackInOneMeal,
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
        addListIdProductsInMeal(idMealList, thisMealProducts, couterProducts)
    }

    private suspend fun addListIdProductsInMeal(
        idMealList: Int,
        thisMealProducts: MutableList<ThisHikeProducts>,
        couterProducts: Int,
    ) {
        if (ThisHikeUseCase(hikeDao).getAllThisHikeListIdProductsInMeal().size == 0) {
            var nameProducts = ""
            ThisHikeUseCase(hikeDao).getAllListThisHikeProducts().forEach {
                if (it.id == thisMealProducts[couterProducts].id) {
                    nameProducts = it.name
                }
            }
            Log.i("nameProducts", "${nameProducts}")
            ThisHikeUseCase(hikeDao).insertThisHikeListIdProductsInMeal(
                1,
                idMealList,
                thisMealProducts[couterProducts].id,
                nameProducts
            )
        } else {
            var nameProducts = ""
            ThisHikeUseCase(hikeDao).getAllListThisHikeProducts().forEach {
                if (it.id == thisMealProducts[couterProducts].id) {
                    nameProducts = it.name
                }
            }
            ThisHikeUseCase(hikeDao).insertThisHikeListIdProductsInMeal(
                ThisHikeUseCase(hikeDao).getAllThisHikeListIdProductsInMeal().last().id + 1,
                idMealList,
                thisMealProducts[couterProducts].id,
                nameProducts
            )
        }


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
        addListIdProductsInMealSnack(
            counterListSnack,
            thisMealProducts,
            couterProducts,
            counterList
        )
    }

    private suspend fun addListIdProductsInMealSnack(
        counterListSnack: Int,
        thisMealProducts: MutableList<ThisHikeProducts>,
        couterProducts: Int,
        counterList: MutableList<ThisHikeMealIntakeSheet>,
    ) {
        if (ThisHikeUseCase(hikeDao).getAllThisHikeListIdProductsInMeal().size == 0) {
            var nameProducts = ""
            ThisHikeUseCase(hikeDao).getAllListThisHikeProducts().forEach {
                if (it.id == thisMealProducts[couterProducts].id) {
                    nameProducts = it.name
                }
            }
            Log.i("nameProducts", "${nameProducts}")
            ThisHikeUseCase(hikeDao).insertThisHikeListIdProductsInMeal(
                1,
                counterList[counterListSnack - 1].id,
                thisMealProducts[couterProducts].id,
                nameProducts
            )
        } else {
            var nameProducts = ""
            ThisHikeUseCase(hikeDao).getAllListThisHikeProducts().forEach {
                if (it.id == thisMealProducts[couterProducts].id) {
                    nameProducts = it.name
                }
            }
            ThisHikeUseCase(hikeDao).insertThisHikeListIdProductsInMeal(
                ThisHikeUseCase(hikeDao).getAllThisHikeListIdProductsInMeal().last().id + 1,
                counterList[counterListSnack - 1].id,
                thisMealProducts[couterProducts].id,
                nameProducts
            )
        }


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
                val returnIndexPartisipant =
                    theLightestBackpackPossible.indexOf(theLightestBackpackPossibleSort[0])
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