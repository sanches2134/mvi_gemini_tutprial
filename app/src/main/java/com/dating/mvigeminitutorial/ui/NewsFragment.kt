package com.dating.mvigeminitutorial.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dating.mvigeminitutorial.R
import com.dating.mvigeminitutorial.databinding.FragmentNewsBinding
import com.dating.mvigeminitutorial.ext.showDialog
import com.dating.mvigeminitutorial.presentation.NewsBindingFactory
import com.dating.mvigeminitutorial.presentation.NewsStore
import com.haroncode.gemini.StoreEventListener
import com.haroncode.gemini.StoreView
import com.haroncode.gemini.binder.StoreViewBinding
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class NewsFragment : Fragment(R.layout.fragment_news), StoreView<NewsStore.Action, NewsStore.State>,
    StoreEventListener<NewsStore.Event> {

    private val broadcastChannel = BroadcastChannel<NewsStore.Action>(Channel.BUFFERED)

    private val binding by viewBinding<FragmentNewsBinding>()

    private val store by inject<NewsStore>()

    private val factory by inject<NewsBindingFactory> {
        parametersOf(store)
    }
    private var newsAdapter: NewsAdapter? = null

    override val actionFlow: Flow<NewsStore.Action>
        get() = broadcastChannel.asFlow()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StoreViewBinding.withRestore(factoryProvider = factory::bindingRulesFactory)
            .bind(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsAdapter = NewsAdapter {
            postAction(NewsStore.Action.ShowDialogMessage)
        }
        binding.rvBreakingNews.adapter = newsAdapter
    }

    override fun onEvent(event: NewsStore.Event) {
        when (event) {
            NewsStore.Event.ShowDialog -> showCancelDialog()
        }
    }

    private fun showCancelDialog() {
        showDialog(
            titleResId = R.string.title,
            messageResId = R.string.title,
        )
    }

    override fun accept(value: NewsStore.State) {
        newsAdapter?.submitList(value.articles)
    }

    private fun postAction(action: NewsStore.Action) = broadcastChannel.offer(action)
}
