package com.dating.mvigeminitutorial.presentation

import com.dating.mvigeminitutorial.ui.NewsFragment
import com.haroncode.gemini.binder.rule.BindingRulesFactory
import com.haroncode.gemini.binder.rule.DelegateBindingRulesFactory
import com.haroncode.gemini.binder.rule.bindEventTo
import com.haroncode.gemini.binder.rule.bindingRulesFactory

class NewsBindingFactory (
    private val store: NewsStore
) : DelegateBindingRulesFactory<NewsFragment>() {
    override val bindingRulesFactory: BindingRulesFactory<NewsFragment> =
        bindingRulesFactory { view ->
            baseRule { store to view }
            rule { store bindEventTo view }
            autoCancel { store }
        }
}
