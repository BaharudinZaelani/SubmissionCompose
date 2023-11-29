package com.bahardev.submissioncompose.di

import com.bahardev.submissioncompose.data.AnimeRepository

object Injection {
    fun provideRepository(): AnimeRepository {
        return AnimeRepository.getInstance()
    }
}