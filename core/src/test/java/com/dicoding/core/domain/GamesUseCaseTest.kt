package com.dicoding.core.domain

import com.dicoding.core.data.Resource
import com.dicoding.core.domain.model.Game
import com.dicoding.core.domain.repository.IGamesRepository
import com.dicoding.core.domain.usecase.GamesInteractor
import com.dicoding.core.domain.usecase.GamesUseCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@RunWith(MockitoJUnitRunner::class)
class GameUseCaseTest {
    private lateinit var gamesUseCase: GamesUseCase

    @Mock
    private lateinit var gamesRepository: IGamesRepository

    @Before
    fun setUp() {
        gamesUseCase = GamesInteractor(gamesRepository)
        val dummyListResponse: Flow<Resource<List<Game>>> = flow {  Resource.Success(listOf(GAME)) }
        val dummySingleResponse: Flow<Resource<Game>> = flow { Resource.Success(GAME) }

        Mockito.`when`(gamesRepository.getAllGames(PAGE, PAGE_SIZE)).thenReturn(dummyListResponse)
        Mockito.`when`(gamesRepository.getDetailGame(ID)).thenReturn(dummySingleResponse)
    }

    @Test
    fun `should get list data game from repository`() {
        gamesUseCase.getAllGames(PAGE, PAGE_SIZE)
        Mockito.verify(gamesRepository).getAllGames(PAGE, PAGE_SIZE)
        Mockito.verifyNoMoreInteractions(gamesRepository)
    }

    @Test
    fun `should get detail data game from repository`() {
        gamesUseCase.getDetailGame(ID)
        Mockito.verify(gamesRepository).getDetailGame(ID)
        Mockito.verifyNoMoreInteractions(gamesRepository)
    }

    companion object {
        const val PAGE = 1
        const val PAGE_SIZE = 1
        const val ID = "1"
        val GAME = Game("1",
            "test",
            "test",
            "test",
            "test",
            "test",
            "test",
            "test",
            "test",
            "test",
            false)
    }
}