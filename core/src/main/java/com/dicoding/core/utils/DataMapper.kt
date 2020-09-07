package com.dicoding.core.utils

import com.dicoding.core.data.source.local.entity.GamesEntity
import com.dicoding.core.data.source.remote.response.GameResponse
import com.dicoding.core.domain.model.Game


object DataMapper {
    fun mapResponsesToEntities(input: List<GameResponse>): List<GamesEntity> {
        val gamesList = ArrayList<GamesEntity>()
        input.map {
            val game = GamesEntity(
                id = it.id,
                description = it.description,
                name = it.name,
                name_original = it.name_original,
                released = it.released,
                rating = it.rating,
                ratings_count = it.ratings_count,
                reviews_text_count = it.reviews_text_count,
                reviews_count = it.reviews_count,
                image = it.image
            )
            gamesList.add(game)
        }
        return gamesList
    }

    fun mapResponsesDetailToEntities(input: GameResponse): GamesEntity {
        return GamesEntity(
            id = input.id,
            description = input.description,
            name = input.name,
            name_original = input.name_original,
            released = input.released,
            rating = input.rating,
            ratings_count = input.ratings_count,
            reviews_text_count = input.reviews_text_count,
            reviews_count = input.reviews_count,
            image = input.image
        )
    }

    fun mapEntitiesToDomain(input: List<GamesEntity>): List<Game> =
        input.map {
            Game(
                id = it.id,
                description = it.description,
                name = it.name,
                name_original = it.name_original,
                released = it.released,
                rating = it.rating,
                ratings_count = it.ratings_count,
                reviews_text_count = it.reviews_text_count,
                reviews_count = it.reviews_count,
                image = it.image,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Game) = GamesEntity(
        id = input.id,
        description = input.description,
        name = input.name,
        name_original = input.name_original,
        released = input.released,
        rating = input.rating,
        ratings_count = input.ratings_count,
        reviews_text_count = input.reviews_text_count,
        reviews_count = input.reviews_count,
        image = input.image,
        isFavorite = input.isFavorite
    )

    fun mapEntityToDomain(input: GamesEntity) = Game(
        id = input.id,
        description = input.description,
        name = input.name,
        name_original = input.name_original,
        released = input.released,
        rating = input.rating,
        ratings_count = input.ratings_count,
        reviews_text_count = input.reviews_text_count,
        reviews_count = input.reviews_count,
        image = input.image,
        isFavorite = input.isFavorite
    )
}