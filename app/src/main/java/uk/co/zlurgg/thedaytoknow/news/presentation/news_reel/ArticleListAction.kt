package uk.co.zlurgg.thedaytoknow.news.presentation.news_reel

import uk.co.zlurgg.thedaytoknow.news.domain.Article

/**
 * Actions a user can take on the reel screen
 * can search for articles and can click on an article
 * **/
sealed interface ArticleListAction {
    data class OnSearchQueryChange(val query: String): ArticleListAction
    data class OnArticleClick(val article: Article): ArticleListAction
}