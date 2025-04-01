package uk.co.zlurgg.thedaytoknow.news.presentation.news_reel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NewsListViewModel: ViewModel() {

    private val _state = MutableStateFlow(NewsListState())
    val state = _state.asStateFlow()

    fun onAction(action: NewsListAction)  {
        when(action) {
            is NewsListAction.OnNewsClick -> {

            }
            is NewsListAction.OnSearchQueryChange -> {
                _state.update {
                    it.copy(searchQuery = action.query)
                }
            }
        }
    }
}