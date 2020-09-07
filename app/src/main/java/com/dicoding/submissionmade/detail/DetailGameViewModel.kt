package com.dicoding.submissionmade.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.core.domain.model.Game
import com.dicoding.core.domain.usecase.GamesUseCase

class DetailGameViewModel(private val gameUseCase: GamesUseCase) : ViewModel() {
    fun getDetailGame(id: String) = gameUseCase.getDetailGame(id).asLiveData()
    fun setFavoriteGame(game: Game, newStatus:Boolean) = gameUseCase.setFavoriteGames(game, newStatus)
}
