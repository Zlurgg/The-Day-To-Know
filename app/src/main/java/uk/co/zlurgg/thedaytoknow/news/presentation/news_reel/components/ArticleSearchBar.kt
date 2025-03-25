package uk.co.zlurgg.thedaytoknow.news.presentation.news_reel.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import uk.co.zlurgg.thedaytoknow.R
import uk.co.zlurgg.thedaytoknow.core.presentation.ui.theme.TheDayToKnowTheme

@Composable
fun ArticleSearchBar(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onImeSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = onSearchQueryChange,
        shape = RoundedCornerShape(100),
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = Color.DarkGray,
            focusedBorderColor = Color.LightGray,
        ),
        placeholder = {
            Text(
                text = stringResource(id = R.string.search_hint)
            )
        }
    )
}

@Preview
@Composable
private fun ArticleSearchBarPreview() {
     TheDayToKnowTheme {
         ArticleSearchBar(
             searchQuery = "Latest news",
             onSearchQueryChange = {},
             onImeSearch = {},
             modifier = Modifier
                 .fillMaxWidth()
         )
    }
}