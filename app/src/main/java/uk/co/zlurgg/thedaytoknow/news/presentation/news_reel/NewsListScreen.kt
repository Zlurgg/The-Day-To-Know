package uk.co.zlurgg.thedaytoknow.news.presentation.news_reel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
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
import uk.co.zlurgg.thedaytoknow.news.presentation.news_reel.components.NewsReelItem
import uk.co.zlurgg.thedaytoknow.news.presentation.news_reel.components.NewsSearchBar
import uk.co.zlurgg.thedaytoknow.news.presentation.news_reel.components.news

@Composable
fun NewsListScreenRoot(
    onNewsClick: (News) -> Unit,
    viewModel: NewsListViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    NewsListScreen(
        state = state,
        onAction = { action ->
            when(action) {
                is NewsListAction.OnNewsClick -> onNewsClick(action.news)
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
private fun NewsListScreen(
    state: NewsListState,
    onAction: (NewsListAction) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NewsSearchBar(
            searchQuery = state.searchQuery,
            onSearchQueryChange = {
                onAction(NewsListAction.OnSearchQueryChange(it))
            },
            onImeSearch = {
                keyboardController?.hide()
            },
            modifier = Modifier
                .widthIn(max = 400.dp)
                .fillMaxWidth()
                .padding(16.dp)
        )
        Spacer(modifier = Modifier.padding(16.dp))
        NewsReelItem(
            news = news,
            onClick = {},
        )
    }

}

@Preview
@Composable
private fun NewsListScreenPreview() {
    TheDayToKnowTheme {
        NewsListScreen(
            state = NewsListState(),
            onAction = {}
        )
    }
}