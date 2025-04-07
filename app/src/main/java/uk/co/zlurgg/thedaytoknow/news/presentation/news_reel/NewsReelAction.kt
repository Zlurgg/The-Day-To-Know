package uk.co.zlurgg.thedaytoknow.news.presentation.news_reel

import uk.co.zlurgg.thedaytoknow.news.domain.News

/**
 * Actions a user can take on the reel screen
 * can search for articles and can click on an article
 * **/
sealed interface NewsReelAction {
    data class OnSearchQueryChange(val query: String): NewsReelAction
    data class OnNewsClick(val news: News): NewsReelAction
}