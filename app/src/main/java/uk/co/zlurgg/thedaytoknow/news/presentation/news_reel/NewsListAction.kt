package uk.co.zlurgg.thedaytoknow.news.presentation.news_reel

import uk.co.zlurgg.thedaytoknow.news.domain.News

/**
 * Actions a user can take on the reel screen
 * can search for articles and can click on an article
 * **/
sealed interface NewsListAction {
    data class OnSearchQueryChange(val query: String): NewsListAction
    data class OnNewsClick(val news: News): NewsListAction
}