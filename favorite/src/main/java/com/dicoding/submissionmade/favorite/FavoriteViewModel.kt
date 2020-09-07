package com.dicoding.submissionmade.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.core.domain.usecase.GamesUseCase

class FavoriteViewModel(private val gamesUseCase: GamesUseCase) : ViewModel() {
    val favoriteGames = gamesUseCase.getFavoriteGames().asLiveData()
    fun clearFavoriteGames() = gamesUseCase.clearFavoriteGames()
}
