package com.example.myapplication.ui.this_hike

import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.myapplication.dataBase.Equipment
import com.example.myapplication.dataBase.HikeDao
import com.example.myapplication.dataBase.Participants
import com.example.myapplication.dataBase.thisHike.ThisHike
import com.example.myapplication.dataBase.thisHike.ThisHikeProducts
import com.example.myapplication.domain.ParticipantsEquipmentUseCase
import com.example.myapplication.domain.ProductsUseCase
import com.example.myapplication.domain.ThisHikeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ThisHikeViewModel(private val hikeDao: HikeDao) : ViewModel() {

    suspend fun getAllThisHike(): List<ThisHike> {
        return ThisHikeUseCase(hikeDao).getAllListThisHike()
    }

    suspend fun getAllEquipmentList(): List<Equipment> {
        return ParticipantsEquipmentUseCase(hikeDao).getAllCollectionEquipmentList()
    }

    suspend fun addEquipment(
        id: Int,
        name: String,
        photo: String,
        weight: Int,
        theVolumeItem: Boolean,
        equipmentInTheCampaign: Boolean,
    ) {
        ParticipantsEquipmentUseCase(hikeDao).loadEquipment(
            id = id,
            name = name,
            photo = photo,
            weight = weight,
            theVolumeItem = theVolumeItem,
            equipmentInTheCampaign = equipmentInTheCampaign
        )
    }

    fun checkEquipment() {
        viewModelScope.launch(Dispatchers.IO) {
            val getListEquipment =
                ParticipantsEquipmentUseCase(hikeDao).getAllCollectionEquipmentList()
            if (getListEquipment.size == 0) {

//                ParticipantsEquipmentUseCase(hikeDao).loadEquipment(
//                    1,
//                    "Нажмите сюда или на кнопку снизу, чтобы добавить своё снаряжение.",
//                    "Photo",
//                    1000,
//                    false
//                )
                ParticipantsEquipmentUseCase(hikeDao).loadEquipment(
                    1,
                    "Ремнабор",
                    "Photo",
                    650,
                    false,
                    true
                )
                ParticipantsEquipmentUseCase(hikeDao).loadEquipment(
                    2,
                    "Костровое",
                    "Photo",
                    575,
                    false,
                    true
                )
                ParticipantsEquipmentUseCase(hikeDao).loadEquipment(
                    3,
                    "Топор",
                    "Photo",
                    685,
                    false,
                    true
                )
                ParticipantsEquipmentUseCase(hikeDao).loadEquipment(
                    3,
                    "Конек",
                    "Photo",
                    545,
                    false,
                    true
                )
                ParticipantsEquipmentUseCase(hikeDao).loadEquipment(
                    4,
                    "Спальник спарка",
                    "Photo",
                    1780,
                    false,
                    true
                )
                ParticipantsEquipmentUseCase(hikeDao).loadEquipment(
                    5,
                    "Горелка Яр",
                    "Photo",
                    371,
                    false,
                    true
                )
                ParticipantsEquipmentUseCase(hikeDao).loadEquipment(
                    6,
                    "Палатка тройка",
                    "Photo",
                    2240,
                    false,
                    true
                )
                ParticipantsEquipmentUseCase(hikeDao).loadEquipment(
                    7,
                    "Палатка двойка",
                    "Photo",
                    2650,
                    false,
                    true
                )
                ParticipantsEquipmentUseCase(hikeDao).loadEquipment(
                    8,
                    "Аппаратура для видео",
                    "Photo",
                    2477,
                    false,
                    true
                )
                ParticipantsEquipmentUseCase(hikeDao).loadEquipment(
                    9,
                    "Спальник спарка один",
                    "Photo",
                    1780,
                    false,
                    true
                )
                ParticipantsEquipmentUseCase(hikeDao).loadEquipment(
                    10,
                    "Кухонное",
                    "Photo",
                    535,
                    false,
                    true
                )
                ParticipantsEquipmentUseCase(hikeDao).loadEquipment(
                    11,
                    "Спальник спарка второй",
                    "Photo",
                    1780,
                    false,
                    true
                )
                ParticipantsEquipmentUseCase(hikeDao).loadEquipment(
                    12,
                    "Женская палатка",
                    "Photo",
                    2450,
                    false,
                    true
                )
                ParticipantsEquipmentUseCase(hikeDao).loadEquipment(
                    13,
                    "Гитара",
                    "Photo",
                    2300,
                    false,
                    true
                )

            }
        }
    }

    fun checkParticipants() {
        viewModelScope.launch(Dispatchers.IO) {
            val getListParticipants =
                ParticipantsEquipmentUseCase(hikeDao).getAllCollectionParticipantsList()
            if (getListParticipants.size == 0) {
//                ParticipantsEquipmentUseCase(hikeDao).loadParticipants(
//                    1,
//                    "Photo",
//                    "Для добавления",
//                    "участника",
//                    "нажмите сюда, или на кнопку",
//                    "17",
//                    10000,
//                    10000,
                //                    10000,
//                    false
//
//                )
                ParticipantsEquipmentUseCase(hikeDao).loadParticipants(
                    1,
                    "Photo",
                    "Ярослав",
                    "Клюкин",
                    "Мужской",
                    "27",
                    25000,
                    10000,
                    10000,
                    true

                )
                ParticipantsEquipmentUseCase(hikeDao).loadParticipants(
                    2,
                    "Photo",
                    "Данила",
                    "Дайбов",
                    "Мужской",
                    "27",
                    24000,
                    10000,
                    10000,
                    true

                )
                ParticipantsEquipmentUseCase(hikeDao).loadParticipants(
                    3,
                    "Photo",
                    "Павел",
                    "Почикаев",
                    "Мужской",
                    "27",
                    23000,
                    10000,
                    10000,
                    true

                )
                ParticipantsEquipmentUseCase(hikeDao).loadParticipants(
                    4,
                    "Photo",
                    "Евгений",
                    "Полтаев",
                    "Мужской",
                    "28",
                    23000,
                    10000,
                    10000,
                    true

                )
                ParticipantsEquipmentUseCase(hikeDao).loadParticipants(
                    5,
                    "Photo",
                    "Елизавета",
                    "Хлобостова",
                    "Женский",
                    "22",
                    19000,
                    10000,
                    10000,
                    true

                )
                ParticipantsEquipmentUseCase(hikeDao).loadParticipants(
                    6,
                    "Photo",
                    "Софья",
                    "Арзамасцева",
                    "Женский",
                    "22",
                    19000,
                    10000,
                    10000,
                    true

                )
                ParticipantsEquipmentUseCase(hikeDao).loadParticipants(
                    7,
                    "Photo",
                    "Анна",
                    "Хурина",
                    "Женский",
                    "22",
                    19000,
                    10000,
                    10000,
                    true

                )
            }
        }
    }

    fun checkingForStorageAvailability() {
        viewModelScope.launch(Dispatchers.IO) {
            val getListProductStorage = ProductsUseCase(hikeDao).getAllProductStorageList()
            if (getListProductStorage.size == 0) {
                ProductsUseCase(hikeDao).loadProductStorage(
                    1,
                    "Первое и единственное хранилище"
                )
            }
        }
    }

    fun сheckingForTypeLists() {
        viewModelScope.launch(Dispatchers.IO) {
            val getListTypeListProducts = ProductsUseCase(hikeDao).getAllListTypeOfProductsList()
            if (getListTypeListProducts.size == 0) {
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    1,
                    1,
                    "Завтрак",
                    "каши"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    2,
                    1,
                    "Завтрак",
                    "сухофрукт"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    3,
                    1,
                    "Завтрак",
                    "масло"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    4,
                    1,
                    "Завтрак",
                    "сгущенка"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    5,
                    1,
                    "Завтрак",
                    "хлеб"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    6,
                    1,
                    "Завтрак",
                    "намазка к хлебу"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    7,
                    1,
                    "Завтрак",
                    "напиток"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    8,
                    1,
                    "Обед",
                    "крупа"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    9,
                    1,
                    "Обед",
                    "мясо"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    10,
                    1,
                    "Обед",
                    "сладкое"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    11,
                    1,
                    "Обед",
                    "хлеб"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    12,
                    1,
                    "Обед",
                    "чай"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    13,
                    1,
                    "Ужин",
                    "суп"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    14,
                    1,
                    "Ужин",
                    "Хлеб к супу"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    15,
                    1,
                    "Ужин",
                    "мясо в суп"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    16,
                    1,
                    "Ужин",
                    "крупа в суп"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    17,
                    1,
                    "Ужин",
                    "крупа на второе"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    18,
                    1,
                    "Ужин",
                    "мясо ко второму"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    19,
                    1,
                    "Ужин",
                    "сладкое"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    20,
                    1,
                    "Ужин",
                    "хлеб"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    21,
                    1,
                    "Перекус",
                    "Орехи"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    22,
                    1,
                    "Перекус",
                    "Сухофрукты"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    23,
                    1,
                    "Специи",
                    "Соль"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    24,
                    1,
                    "Специи",
                    "Сахар"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    25,
                    1,
                    "Специи",
                    "К супу"
                )
            }
        }
    }

    fun checkingTheAvailabilityOfProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            val getListProducts = ProductsUseCase(hikeDao).getAllProductsList()
            if (getListProducts.size == 0) {
                ProductsUseCase(hikeDao).loadProducts(
                    1,
                    "Каша злаки",
                    60,
                    800,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    1,
                    1
                )
                ProductsUseCase(hikeDao).loadProducts(
                    2,
                    "Каша кукурузная",
                    60,
                    800,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    1,
                    2
                )
                ProductsUseCase(hikeDao).loadProducts(
                    3,
                    "Каша овсяная",
                    60,
                    800,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    1,
                    3
                )
                ProductsUseCase(hikeDao).loadProducts(
                    4,
                    "Каша пшенная",
                    60,
                    800,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    1,
                    4
                )
                ProductsUseCase(hikeDao).loadProducts(
                    5,
                    "Каша рисовая",
                    60,
                    800,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    1,
                    5
                )
                ProductsUseCase(hikeDao).loadProducts(
                    6,
                    "Изюм",
                    12,
                    12,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    2,
                    6
                )
                ProductsUseCase(hikeDao).loadProducts(
                    7,
                    "Курага",
                    15,
                    15,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    2,
                    7
                )
                ProductsUseCase(hikeDao).loadProducts(
                    8,
                    "Чернослив",
                    15,
                    15,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    2,
                    8
                )
                ProductsUseCase(hikeDao).loadProducts(
                    9,
                    "Масло",
                    15,
                    180,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    3,
                    9
                )
                ProductsUseCase(hikeDao).loadProducts(
                    10,
                    "Сгущенка",
                    27,
                    270,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    4,
                    10
                )
                ProductsUseCase(hikeDao).loadProducts(
                    11,
                    "Галеты",
                    2,
                    14,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    5,
                    11
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    11,
                    11
                )
                ProductsUseCase(hikeDao).loadProducts(
                    12,
                    "Печенье",
                    25,
                    1000,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    5,
                    12
                )
                ProductsUseCase(hikeDao).loadProducts(
                    13,
                    "Сушки",
                    30,
                    450,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    5,
                    13
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    11,
                    13
                )
                ProductsUseCase(hikeDao).loadProducts(
                    14,
                    "Паштет",
                    25,
                    180,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    6,
                    14
                )
                ProductsUseCase(hikeDao).loadProducts(
                    15,
                    "Плавленый сыр",
                    25,
                    180,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    6,
                    15
                )
                ProductsUseCase(hikeDao).loadProducts(
                    16,
                    "Сыр",
                    25,
                    180,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    6,
                    16
                )
                ProductsUseCase(hikeDao).loadProducts(
                    17,
                    "Чай",
                    1,
                    25,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    7,
                    17
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    12,
                    17
                )
                ProductsUseCase(hikeDao).loadProducts(
                    18,
                    "Кофе 3в1",
                    1,
                    50,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    7,
                    18
                )
                ProductsUseCase(hikeDao).loadProducts(
                    19,
                    "Какао",
                    10,
                    250,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    7,
                    19
                )
                ProductsUseCase(hikeDao).loadProducts(
                    20,
                    "Пюре картофельное",
                    33,
                    300,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    8,
                    20
                )
                ProductsUseCase(hikeDao).loadProducts(
                    21,
                    "Разводные супы",
                    2,
                    2,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    8,
                    21
                )
                ProductsUseCase(hikeDao).loadProducts(
                    22,
                    "Кальмары",
                    5,
                    15,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    9,
                    22
                )
                ProductsUseCase(hikeDao).loadProducts(
                    23,
                    "Вяленное мясо",
                    5,
                    15,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    9,
                    23
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    15,
                    23
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    18,
                    23
                )
                ProductsUseCase(hikeDao).loadProducts(
                    24,
                    "Печенье",
                    25,
                    450,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    10,
                    24
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    19,
                    24
                )
                ProductsUseCase(hikeDao).loadProducts(
                    25,
                    "Конфеты батончики",
                    25,
                    450,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    10,
                    25
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    19,
                    25
                )
                ProductsUseCase(hikeDao).loadProducts(
                    26,
                    "Конфеты халва",
                    25,
                    450,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    10,
                    26
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    19,
                    26
                )
                ProductsUseCase(hikeDao).loadProducts(
                    27,
                    "Сникерс",
                    25,
                    450,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    10,
                    27
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    19,
                    27
                )
                ProductsUseCase(hikeDao).loadProducts(
                    28,
                    "Шоколадка",
                    30,
                    90,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    10,
                    28
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    19,
                    28
                )
                ProductsUseCase(hikeDao).loadProducts(
                    29,
                    "Тульский пряник",
                    60,
                    180,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    10,
                    29
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    19,
                    29
                )
                ProductsUseCase(hikeDao).loadProducts(
                    30,
                    "Грибной",
                    2,
                    2,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    13,
                    30
                )
                ProductsUseCase(hikeDao).loadProducts(
                    31,
                    "Куриный",
                    2,
                    2,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    13,
                    31
                )
                ProductsUseCase(hikeDao).loadProducts(
                    32,
                    "Говяжий",
                    2,
                    2,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    13,
                    32
                )
                ProductsUseCase(hikeDao).loadProducts(
                    33,
                    "Сухари",
                    10,
                    300,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    14,
                    33
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    20,
                    33
                )
                ProductsUseCase(hikeDao).loadProducts(
                    34,
                    "Тушенка",
                    60,
                    338,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    15,
                    34
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    18,
                    34
                )
                ProductsUseCase(hikeDao).loadProducts(
                    35,
                    "Соя",
                    5,
                    50,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    16,
                    35
                )
                ProductsUseCase(hikeDao).loadProducts(
                    36,
                    "Лапша",
                    6,
                    450,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    16,
                    36
                )
                ProductsUseCase(hikeDao).loadProducts(
                    37,
                    "Гречка",
                    70,
                    800,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    17,
                    37
                )
                ProductsUseCase(hikeDao).loadProducts(
                    38,
                    "Рис",
                    70,
                    800,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    17,
                    38
                )
                ProductsUseCase(hikeDao).loadProducts(
                    39,
                    "Чечевица",
                    70,
                    800,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    17,
                    39
                )
                ProductsUseCase(hikeDao).loadProducts(
                    40,
                    "Булгур",
                    70,
                    450,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    17,
                    40
                )
                ProductsUseCase(hikeDao).loadProducts(
                    41,
                    "Кус-кус",
                    70,
                    450,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    17,
                    41
                )
                ProductsUseCase(hikeDao).loadProducts(
                    42,
                    "Вафли",
                    30,
                    450,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    19,
                    42
                )
                ProductsUseCase(hikeDao).loadProducts(
                    43,
                    "Грецкий орех",
                    20,
                    20,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    21,
                    43
                )
                ProductsUseCase(hikeDao).loadProducts(
                    44,
                    "Миндаль",
                    20,
                    20,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    21,
                    44
                )
                ProductsUseCase(hikeDao).loadProducts(
                    45,
                    "Финики",
                    20,
                    20,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    22,
                    45
                )
                ProductsUseCase(hikeDao).loadProducts(
                    46,
                    "Сушеные яблоки",
                    20,
                    20,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    22,
                    46
                )
                ProductsUseCase(hikeDao).loadProducts(
                    47,
                    "Манго",
                    20,
                    20,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    22,
                    47
                )
                ProductsUseCase(hikeDao).loadProducts(
                    48,
                    "Цукаты",
                    20,
                    20,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    22,
                    48
                )
                ProductsUseCase(hikeDao).loadProducts(
                    49,
                    "Фундук",
                    20,
                    20,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    21,
                    49
                )
                ProductsUseCase(hikeDao).loadProducts(
                    50,
                    "Хмели-сунели",
                    2,
                    20,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    25,
                    50
                )
                ProductsUseCase(hikeDao).loadProducts(
                    51,
                    "Сахар",
                    20,
                    900,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    24,
                    51
                )
                ProductsUseCase(hikeDao).loadProducts(
                    52,
                    "Соль",
                    5,
                    450,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    23,
                    52
                )
                ProductsUseCase(hikeDao).loadProducts(
                    53,
                    "Перец",
                    5,
                    450,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    25,
                    53
                )
            }
        }
    }
}