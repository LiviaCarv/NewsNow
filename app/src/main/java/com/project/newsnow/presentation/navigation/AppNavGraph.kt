package com.project.newsnow.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.project.newsnow.presentation.onboarding.OnBoardingScreen
import com.project.newsnow.presentation.onboarding.OnBoardingViewModel

@Composable
fun AppNavGraph(
    startDestination: String,
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {

        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route,
        ) {
            composable(route = Route.OnBoardingScreen.route) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                val state by viewModel.state.collectAsStateWithLifecycle()
                OnBoardingScreen(
                    state = state,
                    onEvent = viewModel::onEvent
                )
            }
        }

        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigatorScreen.route
        ) {
            composable(route = Route.NewsNavigatorScreen.route) {
                TODO()
            }
        }
    }
}