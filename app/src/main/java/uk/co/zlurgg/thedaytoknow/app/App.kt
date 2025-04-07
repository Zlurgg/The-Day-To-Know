package uk.co.zlurgg.thedaytoknow.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import uk.co.zlurgg.thedaytoknow.news.presentation.news_reel.NewsListScreenRoot
import uk.co.zlurgg.thedaytoknow.news.presentation.news_reel.NewsReelViewModel

@Composable
@Preview
fun App() {
    NewsListScreenRoot(
        viewModel = remember { NewsReelViewModel() },
        onNewsClick = {

        }
    )
}
