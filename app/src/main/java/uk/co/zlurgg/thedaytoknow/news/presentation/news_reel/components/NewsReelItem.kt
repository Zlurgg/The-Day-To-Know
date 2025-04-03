package uk.co.zlurgg.thedaytoknow.news.presentation.news_reel.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import uk.co.zlurgg.thedaytoknow.R
import uk.co.zlurgg.thedaytoknow.core.presentation.ui.theme.TheDayToKnowTheme
import uk.co.zlurgg.thedaytoknow.news.domain.News

@Composable
fun NewsReelItem(
    news: News,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(32.dp),
        modifier = modifier
            .clickable(onClick = onClick),
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            Box(
                modifier = Modifier
                    .height(100.dp),
                contentAlignment = Alignment.Center
            ) {
                LoadImage(
                    model = news.imageUrl
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    news.categories.forEach { category ->
                        Text(
                            text = category,
                            style = MaterialTheme.typography.bodyMedium,
                            maxLines = 1,
                        )
                    }
                }
                Text(
                    text = news.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = news.description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis
                )

            }
        }
    }
}

@Composable
fun LoadImage(
    model: String,
) {
    println("image url: $model")
    var imageLoadResult by remember {
        mutableStateOf<Result<Painter>?>(null)
    }
    val painter = rememberAsyncImagePainter(
        model = model,
        onSuccess = {
            imageLoadResult = if(it.painter.intrinsicSize.width > 1 && it.painter.intrinsicSize.height > 1) {
                Result.success(it.painter)
            } else {
                Result.failure(Exception("Invalid image size"))
            }
        },
        onError = {
            it.result.throwable.printStackTrace()
            imageLoadResult = Result.failure(it.result.throwable)
        }
    )
    println("painter: $painter")

    when(val result = imageLoadResult) {
        null -> CircularProgressIndicator()
        else -> {
            Image(
                painter = if(result.isSuccess) painter else {
                    painterResource(R.drawable.news_image_error)
                },
                contentDescription = news.title,
                contentScale = if(result.isSuccess) {
                    ContentScale.Crop
                } else {
                    ContentScale.Fit
                },
                modifier = Modifier
                    .aspectRatio(
                        ratio = 0.65f,
                        matchHeightConstraintsFirst = true
                    )
            )
        }
    }
}

@Preview
@Composable
private fun NewsListItemPreview() {
    TheDayToKnowTheme {
        NewsReelItem(
            news = news,
            onClick = {},
        )
    }
}

val news = News(
    id = "1",
    title = "Stellantis idles plants in Mexico and Canada due to tariffs",
    description = "Stellantis is pausing production at the automaker's Windsor Assembly Plant in Ontario, Canada, and its Toluca Assembly Plant in Mexico.",
    keywords = "Breaking News: Business, Autos, Transportation, Business, Stellantis NV, Stellantis NV, Canada, Environment, Donald Trump, Breaking News: Politics, Politics, Trade, Donald J. Trump, Personnel, business news",
    snippet = "The Stellantis Windsor Assembly Plant is shown on April 1, 2025 in Windsor, Canada.\\n\\nDETROIT — Stellantis is pausing production at two assembly plants in Cana...",
    url = "https://www.cnbc.com/2025/04/03/stellantis-idles-plants-in-mexico-and-canada-due-to-tariffs.html",
    imageUrl = "https://image.cnbcfm.com/api/v1/image/108124412-1743531991434-gettyimages-2207473532-_C3A5390CR2.jpeg?v=1743532113&w=1920&h=1080",
    language = "en",
    publishedAt = "2025-04-03T13:56:19.000000Z",
    categories = emptyList(),
    source = "cnbc.com",
    relevanceScore = null,
    locale = "us"
)