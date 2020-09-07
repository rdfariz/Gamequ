package com.dicoding.submissionmade.di

import com.dicoding.core.domain.usecase.GamesInteractor
import com.dicoding.core.domain.usecase.GamesUseCase
import com.dicoding.submissionmade.detail.DetailGameViewModel
import com.dicoding.submissionmade.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<GamesUseCase> { GamesInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailGameViewModel(get()) }
}