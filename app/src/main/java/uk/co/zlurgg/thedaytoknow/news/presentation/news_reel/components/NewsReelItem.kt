package uk.co.zlurgg.thedaytoknow.news.presentation.news_reel.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
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
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = news.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.padding(12.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LoadImage(
                        title = news.title,
                        model = news.imageUrl
                    )
                }
                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    text = news.description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.padding(8.dp))
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
            }
        }
    }
}

@Composable
fun LoadImage(
    title: String,
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
        onError = { errorResult ->
            val throwable = errorResult.result.throwable
            Log.e("CoilImageError", "Error loading image: ${throwable.message}", throwable)
            imageLoadResult = Result.failure(throwable)
        }
    )
    println("painter: $painter")

    when(val result = imageLoadResult) {
        null -> CircularProgressIndicator(modifier = Modifier.fillMaxWidth())
        else -> {
            if(result.isSuccess) {
                Box(
                    modifier = Modifier
                        .heightIn(min = 150.dp, max = 400.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painter,
                        contentDescription = title,
                        contentScale =
                            ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(
                                ratio = 1.65f,
                                matchHeightConstraintsFirst = true
                            )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun NewsReelItemPreview() {
    TheDayToKnowTheme {
        NewsReelItem(
            news = news,
            onClick = {},
        )
    }
}

private val news = News(
    id = "1",
    title = "Title 1",
    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum sollicitudin ut augue maximus luctus. Donec quis nulla vitae nisi fermentum dictum nec rutrum erat. Maecenas feugiat varius libero, in vulputate metus fermentum vitae. Nullam at tincidunt ante. Phasellus sodales nulla tincidunt neque pellentesque cursus a ac magna. Vivamus dapibus nulla at felis faucibus, id tempus metus hendrerit. Praesent ut magna accumsan diam ultricies malesuada. Proin turpis urna, tempor in lacus ac, vestibulum suscipit nisi. Maecenas non sagittis lacus, non posuere augue.",
    keywords = "test news with dummy image",
    snippet = "",
    url = "",
    imageUrl = "https://image.cnbcfm.com/api/v1/image/108124412-1743531991434-gettyimages-2207473532-_C3A5390CR2.jpeg?v=1743532113&w=1920&h=1080",
    language = "en",
    publishedAt = "2025-04-03T13:56:19.000000Z",
    categories = emptyList(),
    source = "",
    relevanceScore = null,
    locale = ""
)