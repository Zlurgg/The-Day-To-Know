package uk.co.zlurgg.thedaytoknow.news.presentation.news_reel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel
import uk.co.zlurgg.thedaytoknow.core.presentation.ui.theme.TheDayToKnowTheme
import uk.co.zlurgg.thedaytoknow.news.domain.News
import uk.co.zlurgg.thedaytoknow.news.presentation.news_reel.components.NewsReel
import uk.co.zlurgg.thedaytoknow.news.presentation.news_reel.components.NewsSearchBar

@Composable
fun NewsListScreenRoot(
    onNewsClick: (News) -> Unit,
    viewModel: NewsReelViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    NewsListScreen(
        state = state,
        onAction = { action ->
            when(action) {
                is NewsReelAction.OnNewsClick -> onNewsClick(action.news)
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
private fun NewsListScreen(
    state: NewsReelState,
    onAction: (NewsReelAction) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val searchResultsNewsReelState = rememberLazyListState()

    LaunchedEffect(state.searchResults) {
        searchResultsNewsReelState.animateScrollToItem(0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onBackground)
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NewsSearchBar(
            searchQuery = state.searchQuery,
            onSearchQueryChange = {
                onAction(NewsReelAction.OnSearchQueryChange(it))
            },
            onImeSearch = {
                keyboardController?.hide()
            },
            modifier = Modifier
                .widthIn(max = 400.dp)
                .fillMaxWidth()
                .padding(16.dp)
        )
        Spacer(modifier = Modifier.padding(4.dp))
        Surface(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            color = MaterialTheme.colorScheme.background,
            shape = RoundedCornerShape(
                topStart = 32.dp,
                topEnd = 32.dp
            )
        ) {
            Row(
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .widthIn(max = 700.dp)
                    .fillMaxWidth()
            ) {
                NewsReel(
                    news = state.searchResults,
                    onNewsClick = {
                        onAction(NewsReelAction.OnNewsClick(it))
                    },
                    modifier = Modifier
                        .fillMaxSize(),
                    scrollState = searchResultsNewsReelState
                )
            }
        }
    }
}

@Preview
@Composable
private fun NewsListScreenPreview() {
    TheDayToKnowTheme {
        NewsListScreen(
            state = NewsReelState(
                searchResults = newsReel
            ),
            onAction = {}
        )
    }
}

private val newsReel = (1..100).map {
    News(
        id = it.toString(),
        title = "Title $it",
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
}