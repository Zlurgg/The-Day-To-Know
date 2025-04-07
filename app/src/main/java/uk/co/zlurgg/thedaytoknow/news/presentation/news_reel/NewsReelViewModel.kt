package uk.co.zlurgg.thedaytoknow.news.presentation.news_reel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NewsReelViewModel: ViewModel() {

    private val _state = MutableStateFlow(NewsReelState())
    val state = _state.asStateFlow()

    fun onAction(action: NewsReelAction)  {
        when(action) {
            is NewsReelAction.OnNewsClick -> {

            }
            is NewsReelAction.OnSearchQueryChange -> {
                _state.update {
                    it.copy(searchQuery = action.query)
                }
            }
        }
    }
}