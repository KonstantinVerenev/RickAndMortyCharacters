package org.kverenev.rickandmortycharacters.presentation

import androidx.fragment.app.Fragment

fun Fragment.navigator(): Navigator {
    return requireActivity() as Navigator
}

interface Navigator {

    fun showDetailsScreen(characterId: String)

    fun goBack()

}