package com.github.lzaytseva.moviesimdbapi.data.mapper

import com.github.lzaytseva.moviesimdbapi.data.dto.Actor
import com.github.lzaytseva.moviesimdbapi.data.dto.Directors
import com.github.lzaytseva.moviesimdbapi.data.dto.Item
import com.github.lzaytseva.moviesimdbapi.data.dto.MovieCastResponse
import com.github.lzaytseva.moviesimdbapi.data.dto.Other
import com.github.lzaytseva.moviesimdbapi.data.dto.Writers
import com.github.lzaytseva.moviesimdbapi.domain.model.MovieCast
import com.github.lzaytseva.moviesimdbapi.domain.model.MovieCastPerson

class MovieCastMapper {
    fun mapDtoToDomain(response: MovieCastResponse): MovieCast {
        return with(response) {
            MovieCast(
                imdbId = this.imDbId,
                fullTitle = this.fullTitle,
                directors = mapToMovieCastPerson(directors),
                others = mapOthersToMovieCastPerson(others),
                writers = mapToMovieCastPerson(this.writers),
                actors = mapToMovieCastPerson(this.actors)
            )
        }
    }

    private fun mapToMovieCastPerson(directors: Directors): List<MovieCastPerson> {
        return directors.items.map { it.toMovieCastPerson() }
    }

    private fun mapOthersToMovieCastPerson(others: List<Other>): List<MovieCastPerson> {
        return others.flatMap { others ->
            others.items.map { it.toMovieCastPerson(jobPrefix = others.job) }
        }
    }

    private fun mapToMovieCastPerson(writers: Writers): List<MovieCastPerson> {
        return writers.items.map { it.toMovieCastPerson() }
    }

    private fun mapToMovieCastPerson(actors: List<Actor>): List<MovieCastPerson> {
        return actors.map { actor ->
            MovieCastPerson(
                id = actor.id,
                name = actor.name,
                description = actor.asCharacter,
                image = actor.image,
            )
        }
    }

    private fun Item.toMovieCastPerson(jobPrefix: String = ""): MovieCastPerson {
        return MovieCastPerson(
            id = this.id,
            name = this.name,
            description = if (jobPrefix.isEmpty()) this.description else "$jobPrefix -- ${this.description}",
            image = null,
        )
    }
}