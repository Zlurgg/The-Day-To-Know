package uk.co.zlurgg.thedaytoknow.news.presentation.news_reel

import uk.co.zlurgg.thedaytoknow.news.domain.Article

/**
 * Article screen state
 * values that can change over time and have an effect on the ui
 */
data class ArticleListState(
    val searchQuery: String = "Latest News",
    val searchResults: List<Article> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
