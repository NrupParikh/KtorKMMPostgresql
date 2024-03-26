package com.nrup.ktor.backend.di

import com.nrup.ktor.backend.data.service.auth.AuthServiceImpl
import com.nrup.ktor.backend.repository.auth.AuthRepository
import com.nrup.ktor.backend.repository.auth.AuthRepositoryImpl

object RepositoryProvider {
    fun provideAuthRepository(): AuthRepository = AuthRepositoryImpl(AuthServiceImpl())
}