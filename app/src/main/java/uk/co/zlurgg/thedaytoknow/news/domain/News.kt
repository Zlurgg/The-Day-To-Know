package uk.co.zlurgg.thedaytoknow.news.domain

/**
 * News model
 */
data class News(
    val id: String,
    val title: String,
    val description: String,
    val keywords: String,
    val snippet: String,
    val url: String,
    val imageUrl: String,
    val language: String,
    val publishedAt: String,
    val categories: List<String>,
    val source: String
)