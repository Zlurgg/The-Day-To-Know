package uk.co.zlurgg.thedaytoknow.news.presentation.news_reel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ArticleListViewModel: ViewModel() {

    private val _state = MutableStateFlow(ArticleListState())
    val state = _state.asStateFlow()

    fun onAction(action: ArticleListAction)  {
        when(action) {
            is ArticleListAction.OnArticleClick -> {

            }
            is ArticleListAction.OnSearchQueryChange -> {
                _state.update {
                    it.copy(searchQuery = action.query)
                }
            }
        }
    }
}