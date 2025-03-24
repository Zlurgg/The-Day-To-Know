package uk.co.zlurgg.thedaytoknow.news.presentation.news_reel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import org.koin.compose.viewmodel.koinViewModel
import uk.co.zlurgg.thedaytoknow.core.presentation.ui.theme.TheDayToKnowTheme

@Composable
fun ArticleListScreenRoot(
    navController: NavController,
    viewModel: ArticleListViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ArticleListScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
private fun ArticleListScreen(
    state: ArticleListState,
    onAction: (ArticleListAction) -> Unit
) {

}

@Preview
@Composable
private fun ArticleListScreenPreview() {
    TheDayToKnowTheme {
        ArticleListScreen(
            state = ArticleListState(),
            onAction = {}
        )
    }
}