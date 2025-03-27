package ru.kabirov.ideaplatformtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import dagger.hilt.android.AndroidEntryPoint
import ru.kabirov.main.ui.GoodsScreen
import ru.kabirov.uikit.theme.DarkPrimary
import ru.kabirov.uikit.theme.IdeaPlatformTestTheme
import ru.kabirov.uikit.theme.LightPrimary

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            IdeaPlatformTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GoodsScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}