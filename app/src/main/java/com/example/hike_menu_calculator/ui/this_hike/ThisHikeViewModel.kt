package com.example.hike_menu_calculator.ui.this_hike

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hike_menu_calculator.dataBase.HikeDao
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHike
import com.example.hike_menu_calculator.domain.ParticipantsEquipmentUseCase
import com.example.hike_menu_calculator.domain.ProductsUseCase
import com.example.hike_menu_calculator.domain.ThisHikeUseCase
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch

class ThisHikeViewModel(private val hikeDao: HikeDao) : ViewModel() {

    suspend fun getAllThisHike(): List<ThisHike> {
        return ThisHikeUseCase(hikeDao).getAllListThisHike()
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
                    false,
                    "nameAll",
                    0,
                    true
                )
                ParticipantsEquipmentUseCase(hikeDao).loadEquipment(
                    2,
                    "Джетбойл Fire Maple X2",
                    "Photo",
                    650,
                    false,
                    false,
                    "nameAll",
                    0,
                    true
                )
                ParticipantsEquipmentUseCase(hikeDao).loadEquipment(
                    3,
                    "Топор fiskars x7",
                    "Photo",
                    685,
                    false,
                    false,
                    "nameAll",
                    0,
                    true
                )
//                ParticipantsEquipmentUseCase(hikeDao).loadEquipment(
//                    3,
//                    "Конек",
//                    "Photo",
//                    545,
//                    false,
//                    false,
//                    "nameAll",
//                    0,
//                    true
//                )
//                ParticipantsEquipmentUseCase(hikeDao).loadEquipment(
//                    4,
//                    "Спальник спарка",
//                    "Photo",
//                    1780,
//                    false,
//                    false,
//                    "nameAll",
//                    0,
//                    true
//                )
                ParticipantsEquipmentUseCase(hikeDao).loadEquipment(
                    5,
                    "Горелка Fire Maple 121",
                    "Photo",
                    300,
                    false,
                    false,
                    "nameAll",
                    0,
                    true
                )
                ParticipantsEquipmentUseCase(hikeDao).loadEquipment(
                    6,
                    "Палатка Naturehike Canyon 2",
                    "Photo",
                    3300,
                    false,
                    false,
                    "nameAll",
                    0,
                    true
                )
//                ParticipantsEquipmentUseCase(hikeDao).loadEquipment(
//                    7,
//                    "Палатка двойка",
//                    "Photo",
//                    2650,
//                    false,
//                    false,
//                    "nameAll",
//                    0,
//                    true
//                )
//                ParticipantsEquipmentUseCase(hikeDao).loadEquipment(
//                    8,
//                    "Аппаратура для видео",
//                    "Photo",
//                    2477,
//                    false,
//                    false,
//                    "nameAll",
//                    0,
//                    true
//                )
//                ParticipantsEquipmentUseCase(hikeDao).loadEquipment(
//                    9,
//                    "Спальник спарка один",
//                    "Photo",
//                    1780,
//                    false,
//                    false,
//                    "nameAll",
//                    0,
//                    true
//                )
//                ParticipantsEquipmentUseCase(hikeDao).loadEquipment(
//                    10,
//                    "Кухонное",
//                    "Photo",
//                    535,
//                    false,
//                    false,
//                    "nameAll",
//                    0,
//                    true
//                )
//                ParticipantsEquipmentUseCase(hikeDao).loadEquipment(
//                    11,
//                    "Спальник спарка второй",
//                    "Photo",
//                    1780,
//                    false,
//                    false,
//                    "nameAll",
//                    0,
//                    true
//                )
//                ParticipantsEquipmentUseCase(hikeDao).loadEquipment(
//                    12,
//                    "Женская палатка",
//                    "Photo",
//                    2450,
//                    false,
//                    false,
//                    "nameAll",
//                    0,
//                    true
//                )
                ParticipantsEquipmentUseCase(hikeDao).loadEquipment(
                    13,
                    "Гитара",
                    "Photo",
                    2300,
                    false,
                    false,
                    "nameAll",
                    0,
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

                ParticipantsEquipmentUseCase(hikeDao).loadParticipants(
                    1,
                    "Photo",
                    "Иван",
                    "Иванов",
                    "Мужской",
                    "27",
                    25000,
                    10000,
                    10000,
                    true

                )
//                ParticipantsEquipmentUseCase(hikeDao).loadParticipants(
//                    2,
//                    "Photo",
//                    "Петр",
//                    "Петров",
//                    "Мужской",
//                    "27",
//                    24000,
//                    10000,
//                    10000,
//                    true
//
//                )
//                ParticipantsEquipmentUseCase(hikeDao).loadParticipants(
//                    3,
//                    "Photo",
//                    "Олег",
//                    "Олегов",
//                    "Мужской",
//                    "27",
//                    23000,
//                    10000,
//                    10000,
//                    true
//
//                )
//                ParticipantsEquipmentUseCase(hikeDao).loadParticipants(
//                    4,
//                    "Photo",
//                    "Виталий",
//                    "Витальев",
//                    "Мужской",
//                    "28",
//                    23000,
//                    10000,
//                    10000,
//                    true
//
//                )
//                ParticipantsEquipmentUseCase(hikeDao).loadParticipants(
//                    5,
//                    "Photo",
//                    "Оксана",
//                    "Оксановна",
//                    "Женский",
//                    "22",
//                    19000,
//                    10000,
//                    10000,
//                    true
//
//                )
//                ParticipantsEquipmentUseCase(hikeDao).loadParticipants(
//                    6,
//                    "Photo",
//                    "Анастасия",
//                    "Анастасьева",
//                    "Женский",
//                    "22",
//                    19000,
//                    10000,
//                    10000,
//                    true
//
//                )
//                ParticipantsEquipmentUseCase(hikeDao).loadParticipants(
//                    7,
//                    "Photo",
//                    "Виктория",
//                    "Ольговна",
//                    "Женский",
//                    "22",
//                    19000,
//                    10000,
//                    10000,
//                    true
//
//                )
            }
        }
    }

    fun checkingForStorageAvailability() {
        viewModelScope.launch(Dispatchers.IO) {
            val getListProductStorage = ProductsUseCase(hikeDao).getAllProductStorageList()
            if (getListProductStorage.size == 0) {
                ProductsUseCase(hikeDao).loadProductStorage(
                    1,
                    "Меню на поход"
                )
                ProductsUseCase(hikeDao).loadProductStorage(
                    2,
                    "Меню на поход с равновесием на ужин"
                )
                ProductsUseCase(hikeDao).loadProductStorage(
                    3,
                    "Меню на поход пакетами каш на завтрак"
                )
//                ProductsUseCase(hikeDao).loadProductStorage(
//                    4,
//                    "Меню на слет"
//                )
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
                //
                //
                //
                // Второй список Меню на поход с равновесием на ужин
                //
                //
                //
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    26,
                    2,
                    "Завтрак",
                    "каши"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    27,
                    2,
                    "Завтрак",
                    "сухофрукт"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    28,
                    2,
                    "Завтрак",
                    "масло"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    29,
                    2,
                    "Завтрак",
                    "сгущенка"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    30,
                    2,
                    "Завтрак",
                    "хлеб"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    31,
                    2,
                    "Завтрак",
                    "намазка к хлебу"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    32,
                    2,
                    "Завтрак",
                    "напиток"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    33,
                    2,
                    "Обед",
                    "крупа"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    34,
                    2,
                    "Обед",
                    "мясо"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    35,
                    2,
                    "Обед",
                    "сладкое"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    36,
                    2,
                    "Обед",
                    "хлеб"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    37,
                    2,
                    "Обед",
                    "чай"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    38,
                    2,
                    "Ужин",
                    "суп"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    39,
                    2,
                    "Ужин",
                    "Хлеб к супу"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    40,
                    2,
                    "Ужин",
                    "мясо в суп"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    41,
                    2,
                    "Ужин",
                    "крупа в суп"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    42,
                    2,
                    "Ужин",
                    "Равновесие сухая смесь"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    43,
                    2,
                    "Ужин",
                    "сладкое"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    44,
                    2,
                    "Ужин",
                    "хлеб"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    45,
                    2,
                    "Перекус",
                    "Орехи"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    46,
                    2,
                    "Перекус",
                    "Сухофрукты"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    47,
                    2,
                    "Специи",
                    "Соль"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    48,
                    2,
                    "Специи",
                    "Сахар"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    49,
                    2,
                    "Специи",
                    "К супу"
                )
                //
                //
                //
                // Третий список Меню на поход пакетами каш на завтрак
                //
                //
                //
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    50,
                    3,
                    "Завтрак",
                    "каши индивидуальные"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    51,
                    3,
                    "Завтрак",
                    "сухофрукт"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    52,
                    3,
                    "Завтрак",
                    "сгущенка"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    53,
                    3,
                    "Завтрак",
                    "хлеб"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    54,
                    3,
                    "Завтрак",
                    "намазка к хлебу"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    55,
                    3,
                    "Завтрак",
                    "напиток"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    56,
                    3,
                    "Обед",
                    "крупа"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    57,
                    3,
                    "Обед",
                    "мясо"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    58,
                    3,
                    "Обед",
                    "сладкое"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    59,
                    3,
                    "Обед",
                    "хлеб"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    60,
                    3,
                    "Обед",
                    "чай"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    61,
                    3,
                    "Ужин",
                    "суп"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    62,
                    3,
                    "Ужин",
                    "Хлеб к супу"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    63,
                    3,
                    "Ужин",
                    "мясо в суп"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    64,
                    3,
                    "Ужин",
                    "крупа в суп"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    65,
                    3,
                    "Ужин",
                    "крупа на второе"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    66,
                    3,
                    "Ужин",
                    "мясо ко второму"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    67,
                    3,
                    "Ужин",
                    "сладкое"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    68,
                    3,
                    "Ужин",
                    "хлеб"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    69,
                    3,
                    "Перекус",
                    "Орехи"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    70,
                    3,
                    "Перекус",
                    "Сухофрукты"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    71,
                    3,
                    "Специи",
                    "Соль"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    72,
                    3,
                    "Специи",
                    "Сахар"
                )
                ProductsUseCase(hikeDao).loadListTypeOfProducts(
                    73,
                    3,
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
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    1,
                    1
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    26,
                    1
                )
                ProductsUseCase(hikeDao).loadProducts(
                    2,
                    "Каша кукурузная",
                    60,
                    800,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    1,
                    2
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    26,
                    2
                )
                ProductsUseCase(hikeDao).loadProducts(
                    3,
                    "Каша овсяная",
                    60,
                    800,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    1,
                    3
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    26,
                    3
                )
                ProductsUseCase(hikeDao).loadProducts(
                    4,
                    "Каша пшенная",
                    60,
                    800,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    1,
                    4
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    26,
                    4
                )
                ProductsUseCase(hikeDao).loadProducts(
                    5,
                    "Каша рисовая",
                    60,
                    800,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    1,
                    5
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    26,
                    5
                )
                ProductsUseCase(hikeDao).loadProducts(
                    6,
                    "Изюм",
                    12,
                    12,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    2,
                    6
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    27,
                    6
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    51,
                    6
                )
                ProductsUseCase(hikeDao).loadProducts(
                    7,
                    "Курага",
                    15,
                    15,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    2,
                    7
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    27,
                    7
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    51,
                    7
                )
                ProductsUseCase(hikeDao).loadProducts(
                    8,
                    "Чернослив",
                    15,
                    15,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    2,
                    8
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    27,
                    8
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    51,
                    8
                )
                ProductsUseCase(hikeDao).loadProducts(
                    9,
                    "Масло",
                    12,
                    180,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    3,
                    9
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    28,
                    9
                )
                ProductsUseCase(hikeDao).loadProducts(
                    10,
                    "Сгущенка",
                    19,
                    380,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    4,
                    10
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    29,
                    10
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    52,
                    10
                )
                ProductsUseCase(hikeDao).loadProducts(
                    11,
                    "Галеты",
                    10,
                    200,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    5,
                    11
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    30,
                    11
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    53,
                    11
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    11,
                    11
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    36,
                    11
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    59,
                    11
                )
                ProductsUseCase(hikeDao).loadProducts(
                    12,
                    "Печенье",
                    25,
                    1000,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    5,
                    12
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    30,
                    12
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    53,
                    12
                )
                ProductsUseCase(hikeDao).loadProducts(
                    13,
                    "Сушки",
                    30,
                    450,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    5,
                    13
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    30,
                    13
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    53,
                    13
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    11,
                    13
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    36,
                    13
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    59,
                    13
                )
                ProductsUseCase(hikeDao).loadProducts(
                    14,
                    "Паштет",
                    25,
                    100,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    6,
                    14
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    31,
                    14
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    54,
                    14
                )
                ProductsUseCase(hikeDao).loadProducts(
                    15,
                    "Плавленый сыр",
                    25,
                    200,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    6,
                    15
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    31,
                    15
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    54,
                    15
                )
                ProductsUseCase(hikeDao).loadProducts(
                    16,
                    "Сыр",
                    30,
                    180,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    6,
                    16
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    31,
                    16
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    54,
                    16
                )
                ProductsUseCase(hikeDao).loadProducts(
                    17,
                    "Чай",
                    1,
                    25,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    7,
                    17
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    32,
                    17
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    55,
                    17
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    12,
                    17
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    37,
                    17
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    60,
                    17
                )
                ProductsUseCase(hikeDao).loadProducts(
                    18,
                    "Кофе 3в1",
                    1,
                    50,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    7,
                    18
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    32,
                    18
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    55,
                    18
                )
                ProductsUseCase(hikeDao).loadProducts(
                    19,
                    "Какао",
                    10,
                    250,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    7,
                    19
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    32,
                    19
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    55,
                    19
                )
                ProductsUseCase(hikeDao).loadProducts(
                    20,
                    "Пюре картофельное",
                    33,
                    320,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    8,
                    20
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    33,
                    20
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    56,
                    20
                )
                ProductsUseCase(hikeDao).loadProducts(
                    21,
                    "Разводные супы",
                    12,
                    12,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    8,
                    21
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    33,
                    21
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    56,
                    21
                )
                ProductsUseCase(hikeDao).loadProducts(
                    22,
                    "Кальмары",
                    40,
                    40,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    9,
                    22
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    34,
                    22
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    57,
                    22
                )
                ProductsUseCase(hikeDao).loadProducts(
                    23,
                    "Вяленное мясо",
                    20,
                    100,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    9,
                    23
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    34,
                    23
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    57,
                    23
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    15,
                    23
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    40,
                    23
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    63,
                    23
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    18,
                    23
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    66,
                    23
                )
                ProductsUseCase(hikeDao).loadProducts(
                    24,
                    "Печенье",
                    25,
                    450,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    10,
                    24
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    35,
                    24
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    58,
                    24
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    19,
                    24
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    43,
                    24
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    67,
                    24
                )
                ProductsUseCase(hikeDao).loadProducts(
                    25,
                    "Конфеты батончики Рот Фронт",
                    16,
                    16,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    10,
                    25
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    35,
                    25
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    58,
                    25
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    19,
                    25
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    43,
                    25
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    67,
                    25
                )
                ProductsUseCase(hikeDao).loadProducts(
                    26,
                    "Конфеты халва рот фронт",
                    27,
                    27,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    10,
                    26
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    35,
                    26
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    58,
                    26
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    19,
                    26
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    43,
                    26
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    67,
                    26
                )
                ProductsUseCase(hikeDao).loadProducts(
                    27,
                    "Сникерс",
                    51,
                    51,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    10,
                    27
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    35,
                    27
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    58,
                    27
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    19,
                    27
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    43,
                    27
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    67,
                    27
                )
                ProductsUseCase(hikeDao).loadProducts(
                    28,
                    "Шоколадка",
                    30,
                    90,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    10,
                    28
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    35,
                    28
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    58,
                    28
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    19,
                    28
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    43,
                    28
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    67,
                    28
                )
                ProductsUseCase(hikeDao).loadProducts(
                    29,
                    "Тульский пряник",
                    70,
                    140,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    10,
                    29
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    35,
                    29
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    58,
                    29
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    19,
                    29
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    43,
                    29
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    67,
                    29
                )
                ProductsUseCase(hikeDao).loadProducts(
                    30,
                    "Грибной",
                    15,
                    15,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    13,
                    30
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    38,
                    30
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    61,
                    30
                )
                ProductsUseCase(hikeDao).loadProducts(
                    31,
                    "Куриный",
                    15,
                    15,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    13,
                    31
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    38,
                    31
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    61,
                    31
                )
                ProductsUseCase(hikeDao).loadProducts(
                    32,
                    "Говяжий",
                    15,
                    15,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    13,
                    32
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    38,
                    32
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    61,
                    32
                )
                ProductsUseCase(hikeDao).loadProducts(
                    33,
                    "Сухари",
                    10,
                    300,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    14,
                    33
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    39,
                    33
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    62,
                    33
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    20,
                    33
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    44,
                    33
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    68,
                    33
                )
                ProductsUseCase(hikeDao).loadProducts(
                    34,
                    "Тушенка",
                    70,
                    338,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    15,
                    34
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    40,
                    34
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    63,
                    34
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    18,
                    34
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    66,
                    34
                )
                ProductsUseCase(hikeDao).loadProducts(
                    35,
                    "Соя",
                    10,
                    100,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    16,
                    35
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    41,
                    35
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    64,
                    35
                )
                ProductsUseCase(hikeDao).loadProducts(
                    36,
                    "Лапша суповая",
                    15,
                    450,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    16,
                    36
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    41,
                    36
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    64,
                    36
                )
                ProductsUseCase(hikeDao).loadProducts(
                    37,
                    "Гречка",
                    70,
                    800,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    17,
                    37
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    65,
                    37
                )
                ProductsUseCase(hikeDao).loadProducts(
                    38,
                    "Рис",
                    70,
                    800,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    17,
                    38
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    65,
                    38
                )
                ProductsUseCase(hikeDao).loadProducts(
                    39,
                    "Чечевица",
                    70,
                    800,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    17,
                    39
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    65,
                    39
                )
                ProductsUseCase(hikeDao).loadProducts(
                    40,
                    "Булгур",
                    70,
                    450,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    17,
                    40
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    65,
                    40
                )
                ProductsUseCase(hikeDao).loadProducts(
                    41,
                    "Кус-кус",
                    70,
                    450,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    17,
                    41
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    65,
                    41
                )
                ProductsUseCase(hikeDao).loadProducts(
                    42,
                    "Вафли",
                    30,
                    450,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    19,
                    42
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    43,
                    42
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    67,
                    42
                )
                ProductsUseCase(hikeDao).loadProducts(
                    43,
                    "Грецкий орех",
                    20,
                    20,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    21,
                    43
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    45,
                    43
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    69,
                    43
                )
                ProductsUseCase(hikeDao).loadProducts(
                    44,
                    "Миндаль",
                    20,
                    20,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    21,
                    44
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    45,
                    44
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    69,
                    44
                )
                ProductsUseCase(hikeDao).loadProducts(
                    45,
                    "Финики",
                    20,
                    20,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    22,
                    45
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    46,
                    45
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    70,
                    45
                )
                ProductsUseCase(hikeDao).loadProducts(
                    46,
                    "Сушеные яблоки",
                    20,
                    20,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    22,
                    46
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    46,
                    46
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    70,
                    46
                )
                ProductsUseCase(hikeDao).loadProducts(
                    47,
                    "Манго",
                    20,
                    20,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    22,
                    47
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    46,
                    47
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    70,
                    47
                )
                ProductsUseCase(hikeDao).loadProducts(
                    48,
                    "Цукаты",
                    20,
                    20,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    22,
                    48
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    46,
                    48
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    70,
                    48
                )
                ProductsUseCase(hikeDao).loadProducts(
                    49,
                    "Фундук",
                    20,
                    20,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    21,
                    49
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    45,
                    49
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    69,
                    49
                )
                ProductsUseCase(hikeDao).loadProducts(
                    50,
                    "Хмели-сунели",
                    2,
                    20,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    25,
                    50
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    49,
                    50
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    73,
                    50
                )
                ProductsUseCase(hikeDao).loadProducts(
                    51,
                    "Сахар",
                    20,
                    900,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    24,
                    51
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    48,
                    51
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    72,
                    51
                )
                ProductsUseCase(hikeDao).loadProducts(
                    52,
                    "Соль",
                    5,
                    450,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    23,
                    52
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    47,
                    52
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    71,
                    52
                )
                ProductsUseCase(hikeDao).loadProducts(
                    53,
                    "Перец",
                    5,
                    450,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    25,
                    53
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    49,
                    53
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    73,
                    53
                )
                ProductsUseCase(hikeDao).loadProducts(
                    54,
                    "Индийское карри",
                    70,
                    70,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    42,
                    54
                )
                ProductsUseCase(hikeDao).loadProducts(
                    55,
                    "Каша утренняя клубника",
                    40,
                    40,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    50,
                    55
                )
                ProductsUseCase(hikeDao).loadProducts(
                    56,
                    "Каша утренняя малина",
                    40,
                    40,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    50,
                    56
                )
                ProductsUseCase(hikeDao).loadProducts(
                    57,
                    "Каша утренняя черника",
                    40,
                    40,
                    false,
                    false,
                    "nameAll",
                    0,
                    false,
                    true
                )
                ProductsUseCase(hikeDao).loadListProducts(
                    50,
                    57
                )
            }
        }
    }
}