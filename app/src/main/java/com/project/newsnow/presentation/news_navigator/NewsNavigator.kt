package com.project.newsnow.presentation.news_navigator

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.project.newsnow.R
import com.project.newsnow.domain.model.Article
import com.project.newsnow.presentation.bookmark.BookmarkScreen
import com.project.newsnow.presentation.bookmark.BookmarkViewModel
import com.project.newsnow.presentation.details.DetailsScreen
import com.project.newsnow.presentation.details.DetailsViewModel
import com.project.newsnow.presentation.home.HomeScreen
import com.project.newsnow.presentation.home.HomeViewModel
import com.project.newsnow.presentation.navigation.Route
import com.project.newsnow.presentation.news_navigator.components.BottomNavItem
import com.project.newsnow.presentation.news_navigator.components.NewsBottomNavigation
import com.project.newsnow.presentation.search.SearchScreen
import com.project.newsnow.presentation.search.SearchViewModel
import com.project.newsnow.util.Constants.ARTICLE_KEY

@Composable
fun NewsNavigator() {
    val bottomNavItems = remember {
        listOf(
            BottomNavItem(icon = R.drawable.ic_home, text = "Home"),
            BottomNavItem(icon = R.drawable.ic_search, text = "Search"),
            BottomNavItem(icon = R.drawable.ic_bookmark, text = "Bookmark")
        )
    }

    val navController = rememberNavController()
    val backstackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }

    selectedItem = when (backstackState?.destination?.route) {
        Route.HomeScreen.route -> 0
        Route.SearchScreen.route -> 1
        Route.BookmarkScreen.route -> 2
        else -> 0
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NewsBottomNavigation(
                items = bottomNavItems,
                selectedItem = selectedItem,
                onItemClick = { index ->
                    when (index) {
                        0 -> navigateToTab(navController, Route.HomeScreen.route)
                        1 -> navigateToTab(navController, Route.SearchScreen.route)
                        2 -> navigateToTab(navController, Route.BookmarkScreen.route)
                    }
                })
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.HomeScreen.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(
                    articles = articles,
                    navigateToSearch = {
                        navigateToTab(navController, Route.SearchScreen.route)
                    },
                    navigateToDetails = { article ->
                        navigateToDetails(navController, article)
                    }
                )
            }

            composable(route = Route.SearchScreen.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state.collectAsState().value
                SearchScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    navigateToDetails = { article ->
                        navigateToDetails(navController, article)
                    }
                )
            }

            composable(Route.DetailsScreen.route) {
                val viewModel: DetailsViewModel = hiltViewModel()
                val article = navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.get<Article>(ARTICLE_KEY)

                article?.let {
                    LaunchedEffect(it) {
                        viewModel.setArticle(it)
                    }

                    DetailsScreen(
                        state = viewModel.state,
                        onEvent = viewModel::onEvent,
                        navigateUp = { navController.navigateUp() }
                    )
                }
            }
            composable(route = Route.BookmarkScreen.route) {
                val viewModel: BookmarkViewModel = hiltViewModel()
                val state = viewModel.state.collectAsState().value
                BookmarkScreen(
                    state = state,
                    navigateToDetails = { article ->
                        navigateToDetails(navController, article)
                    }
                )
            }
        }
    }

}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let {
            popUpTo(it) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}

private fun navigateToDetails(navController: NavController, article: Article) {
    navController.currentBackStackEntry?.savedStateHandle?.set(ARTICLE_KEY, article)
    navController.navigate(
        route = Route.DetailsScreen.route
    )
}