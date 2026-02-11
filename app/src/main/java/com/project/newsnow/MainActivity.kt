package com.project.newsnow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.project.newsnow.presentation.onboarding.OnBoardingScreen
import com.project.newsnow.presentation.onboarding.OnBoardingViewModel
import com.project.newsnow.ui.theme.NewsNowTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            val viewModel: OnBoardingViewModel = hiltViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()
            NewsNowTheme {
                OnBoardingScreen(state, viewModel::onEvent)
            }
        }
    }
}
