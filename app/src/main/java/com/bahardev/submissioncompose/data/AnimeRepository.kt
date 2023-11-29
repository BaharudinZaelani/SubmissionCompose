package com.bahardev.submissioncompose.data

import com.bahardev.submissioncompose.model.Anime
import com.bahardev.submissioncompose.model.AnimeData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class AnimeRepository {
    private val anime = mutableListOf<Anime>()

    init {
        if ( anime.isEmpty() ) {
            AnimeData.anime.forEach {
                anime.add(it)
            }
        }
    }

    companion object {
        @Volatile
        private var instance: AnimeRepository? = null

        fun getInstance(): AnimeRepository = instance ?: synchronized(this) {
            AnimeRepository().apply {
                instance = this
            }
        }
    }

    fun getAllHero(): Flow<List<Anime>> {
        return flowOf(anime)
    }

    fun getHeroById(id: String): Anime {
        return anime.first {
            it.id == id
        }
    }
}