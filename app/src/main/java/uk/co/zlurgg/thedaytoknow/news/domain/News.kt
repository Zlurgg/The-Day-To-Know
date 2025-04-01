package uk.co.zlurgg.thedaytoknow.news.domain

/**
 * News model
 */
data class News(
    val id: String,
    val title: String,
    val category: String,
    val summary: String,
    val country: String,
    val language: String,
    val publisher: String,
    val publishedDate: String,
    val linkToArticle: String
)