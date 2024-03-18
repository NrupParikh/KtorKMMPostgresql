package com.nrup.ktor.backend.di

import com.nrup.ktor.backend.data.service.auth.AuthServiceImpl
import com.nrup.ktor.backend.data.service.post.PostServiceImpl
import com.nrup.ktor.backend.repository.auth.AuthRepository
import com.nrup.ktor.backend.repository.auth.AuthRepositoryImpl
import com.nrup.ktor.backend.repository.post.PostRepository
import com.nrup.ktor.backend.repository.post.PostRepositoryImpl

object RepositoryProvider {
    fun provideAuthRepository(): AuthRepository = AuthRepositoryImpl(AuthServiceImpl())
    fun providePostRepository(): PostRepository = PostRepositoryImpl(PostServiceImpl())
}