package com.dating.mvigeminitutorial.di

import com.dating.mvigeminitutorial.domain.repository.NewsRepository
import com.dating.mvigeminitutorial.domain.repository.NewsRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory<NewsRepository> { NewsRepositoryImpl(get()) }
}
