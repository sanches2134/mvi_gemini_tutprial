package com.dating.mvigeminitutorial.di

import com.dating.mvigeminitutorial.presentation.NewsBindingFactory
import com.dating.mvigeminitutorial.presentation.NewsStore
import org.koin.dsl.module

val geminiModule = module {
    factory {
        NewsStore(
            newsRepository = get()
        )
    }
    factory { (store: NewsStore) ->
        NewsBindingFactory(
            store = store
        )
    }
}
