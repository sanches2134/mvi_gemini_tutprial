package com.dating.mvigeminitutorial.presentation

import com.dating.mvigeminitutorial.domain.entity.Article
import com.dating.mvigeminitutorial.domain.entity.NewsResponse
import com.dating.mvigeminitutorial.domain.repository.NewsRepository
import com.haroncode.gemini.element.Bootstrapper
import com.haroncode.gemini.element.EventProducer
import com.haroncode.gemini.element.Middleware
import com.haroncode.gemini.element.Reducer
import com.haroncode.gemini.store.OnlyActionStore
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class NewsStore(
    newsRepository: NewsRepository
) : OnlyActionStore<NewsStore.Action, NewsStore.State, NewsStore.Event>(
    initialState = State(),
    bootstrapper = BootstrapperImpl(newsRepository),
    middleware = MiddlewareImpl(),
    reducer = ReducerImpl(),
    eventProducer = EventProducerImpl()
) {

    data class State(
        val articles: MutableList<Article>? = mutableListOf()
    )

    sealed class Action {
        data class LoadNewsContentResponse(val news: NewsResponse) : Action()
        object ShowDialogMessage : Action()
    }

    sealed class Event {
        object ShowDialog : Event()
    }

    private class BootstrapperImpl(
        private val newsRepository: NewsRepository
    ) : Bootstrapper<Action> {
        override fun bootstrap(): Flow<Action> = flow {
            emit(
                Action.LoadNewsContentResponse(newsRepository.getNews("ru", 1))
            )
        }
    }

    @FlowPreview
    private class MiddlewareImpl(
    ) : Middleware<Action, State, Action> {
        override fun execute(action: Action, state: State): Flow<Action> = when (action) {
            else -> flowOf(action)
        }
    }

    private class ReducerImpl(
    ) : Reducer<State, Action> {
        override fun reduce(state: State, effect: Action): State = when (effect) {
            is Action.LoadNewsContentResponse -> {
                state.copy(
                    articles = effect.news.articles
                )
            }
            else -> state
        }
    }

    private class EventProducerImpl : EventProducer<State, Action, Event> {
        override fun produce(state: State, effect: Action): Event? = when (effect) {
            is Action.ShowDialogMessage -> Event.ShowDialog
            else -> null
        }
    }
}

