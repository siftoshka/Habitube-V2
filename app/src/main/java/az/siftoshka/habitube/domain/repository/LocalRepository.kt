package az.siftoshka.habitube.domain.repository

import android.content.SharedPreferences
import az.siftoshka.habitube.presentation.screens.settings.sort.SortType

/**
 * The repository interface for [SharedPreferences].
 */
interface LocalRepository {

    fun setContentLanguage(value: String)

    fun getContentLanguage() : String

    fun setSortType(value: String)

    fun getSortType() : SortType

    fun setAdultVisibility(value: Boolean)

    fun isAdultVisible(): Boolean
}