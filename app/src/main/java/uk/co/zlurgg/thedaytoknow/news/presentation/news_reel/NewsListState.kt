package uk.co.zlurgg.thedaytoknow.news.presentation.news_reel

import uk.co.zlurgg.thedaytoknow.news.domain.News

/**
 * Article screen state
 * values that can change over time and have an effect on the ui
 */
data class NewsListState(
    val searchQuery: String = "Latest News",
    val searchResults: List<News> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
