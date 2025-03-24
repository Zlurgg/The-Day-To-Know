package uk.co.zlurgg.thedaytoknow.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import uk.co.zlurgg.thedaytoknow.core.presentation.ui.theme.TheDayToKnowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheDayToKnowTheme {

            }
        }
    }
}