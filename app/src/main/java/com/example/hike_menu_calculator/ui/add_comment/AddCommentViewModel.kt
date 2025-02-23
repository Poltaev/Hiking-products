package com.example.hike_menu_calculator.ui.add_comment

import androidx.lifecycle.ViewModel
import com.example.hike_menu_calculator.dataBase.HikeDao
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeProducts
import com.example.hike_menu_calculator.domain.ThisHikeUseCase

class AddCommentViewModel(private val hikeDao: HikeDao) : ViewModel() {

   suspend fun upDateComment(idProduct: Int, comment: String) {
        ThisHikeUseCase(hikeDao).getAllListThisHikeProducts().forEach {
            if (it.id == idProduct){
                upDateThisHikeProducts(
                    it.id,
                    it.name,
                    it.weightForPerson,
                    it.packageWeight,
                    it.theWeightOfOneMeal,
                    it.weightOnTheHike,
                    it.remainingWeight,
                    it.partiallyAssembled,
                    it.fullyAssembled,
                    it.theVolumeItem,
                    it.theSoleOwner,
                    it.nameOwner,
                    it.idOwner,
                    it.useTheWholePackInOneMeal,
                    comment
                )
            }
        }
    }
    suspend fun upDateThisHikeProducts(
        id: Int,
        name: String,
        weightForPerson: Int,
        packageWeight: Int,
        theWeightOfOneMeal: Int,
        weightOnTheHike: Int,
        remainingWeight: Int,
        partiallyAssembled: Boolean,
        fullyAssembled: Boolean,
        theVolumeItem: Boolean,
        theSoleOwner: Boolean,
        nameOwner: String,
        idOwner: Int,
        useTheWholePackInOneMeal: Boolean,
        comment: String
    ) {
        return ThisHikeUseCase(hikeDao).updateThisHikeProducts(
            id = id,
            name = name,
            weightForPerson = weightForPerson,
            packageWeight = packageWeight,
            theWeightOfOneMeal= theWeightOfOneMeal,
            weightOnTheHike= weightOnTheHike,
            remainingWeight = remainingWeight,
            partiallyAssembled = partiallyAssembled,
            fullyAssembled = fullyAssembled,
            theVolumeItem = theVolumeItem,
            theSoleOwner = theSoleOwner,
            nameOwner = nameOwner,
            idOwner = idOwner,
            useTheWholePackInOneMeal = useTheWholePackInOneMeal,
            comment = comment
        )
    }
}