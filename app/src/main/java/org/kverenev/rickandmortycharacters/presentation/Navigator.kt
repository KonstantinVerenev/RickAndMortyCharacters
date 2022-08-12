package org.kverenev.rickandmortycharacters.presentation

import androidx.fragment.app.Fragment

fun Fragment.navigator(): Navigator {
    /**
     *  6! Не беопасное кастование, ну жно поставить знак вопроса около "as"
     */
    return requireActivity() as Navigator
}

interface Navigator {

    fun showDetailsScreen(characterId: String)

    fun goBack()

}