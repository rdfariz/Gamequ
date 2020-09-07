package com.dicoding.submissionmade.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.core.domain.usecase.GamesUseCase

class HomeViewModel(gamesUseCase: GamesUseCase) : ViewModel() {
    val games = gamesUseCase.getAllGames().asLiveData()
}
