package com.project.newsnow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.project.newsnow.presentation.navigation.AppNavGraph
import com.project.newsnow.ui.theme.NewsNowTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.splashCondition
            }
        }
        enableEdgeToEdge()
        setContent {

            NewsNowTheme {
                val startDestination = viewModel.startDestination
                AppNavGraph(
                    startDestination = startDestination,
                )
            }
        }
    }
}
