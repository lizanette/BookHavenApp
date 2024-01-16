package com.example.bookhavenapp.presentation.books_navigator

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.bookhavenapp.domain.model.Item
import com.example.bookhavenapp.presentation.bookmark.BookmarkScreen
import com.example.bookhavenapp.presentation.bookmark.BookmarkViewModel
import com.example.bookhavenapp.presentation.books_navigator.components.BottomNavigation
import com.example.bookhavenapp.presentation.books_navigator.components.bottomNavigationItems
import com.example.bookhavenapp.presentation.details.BookDetailsEvent
import com.example.bookhavenapp.presentation.details.BookDetailsScreen
import com.example.bookhavenapp.presentation.details.BookDetailsViewModel
import com.example.bookhavenapp.presentation.home.HomeScreen
import com.example.bookhavenapp.presentation.home.HomeViewModel
import com.example.bookhavenapp.presentation.navgraph.Route
import com.example.bookhavenapp.presentation.search.SearchScreen
import com.example.bookhavenapp.presentation.search.SearchViewModel

@Composable
fun BooksNavigator() {
    val bottomNavigationItems = remember { bottomNavigationItems }

    val navController = rememberNavController()
    val backstackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }

    selectedItem = remember(key1 = backstackState) {
        when(backstackState?.destination?.route) {
            Route.HomeScreen.route -> 0
            Route.SearchScreen.route -> 1
            Route.BookmarkScreen.route -> 2
            else -> 0
        }
    }

    val bottomBarVisible = remember(key1 = backstackState) {
        backstackState?.destination?.route in setOf(
            Route.HomeScreen.route,
            Route.SearchScreen.route,
            Route.BookmarkScreen.route
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if(bottomBarVisible) {
                BottomNavigation(
                    items = bottomNavigationItems,
                    selected = selectedItem,
                    onItemClick = { index ->
                        when (index) {
                            0 -> navigate(
                                navController = navController,
                                route = Route.HomeScreen.route
                            )

                            1 -> navigate(
                                navController = navController,
                                route = Route.SearchScreen.route
                            )

                            2 -> navigate(
                                navController = navController,
                                route = Route.BookmarkScreen.route
                            )
                        }
                    }
                )
            }
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            modifier = Modifier.padding(bottom = bottomPadding),
            navController = navController,
            startDestination = Route.HomeScreen.route
        ) {
            composable(route = Route.HomeScreen.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                val books = viewModel.books.collectAsLazyPagingItems()
                HomeScreen(
                    books = books,
                    navigateToSearch = {
                        navigate(
                            navController = navController,
                            route = Route.SearchScreen.route
                        )
                    },
                    navigateToDetails = { item ->
                        navigateToDetails(
                            navController = navController,
                            item = item
                        )
                    }
                )
            }

            composable(route = Route.SearchScreen.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state.value
                SearchScreen(
                    state = state,
                    event = viewModel::onEvent,
                    navigateToDetails = { item ->
                        navigateToDetails(
                            navController = navController,
                            item = item
                        )
                    }
                )
            }

            composable(route = Route.BookDetailsScreen.route) {
                val viewModel: BookDetailsViewModel = hiltViewModel()

                // Handling side effect
                if(viewModel.sideEffect != null ) {
                    Toast.makeText(LocalContext.current, viewModel.sideEffect, Toast.LENGTH_SHORT).show()
                    viewModel.onEvent(BookDetailsEvent.RemoveSideEffect)
                }

                navController.previousBackStackEntry?.savedStateHandle?.get<Item?>("item")?.let { item ->
                    BookDetailsScreen(
                        book = item,
                        event = viewModel::onEvent,
                        navigateUp = {
                            navController.navigateUp()
                        }
                    )
                }
            }

            composable(route = Route.BookmarkScreen.route) {
                val viewModel: BookmarkViewModel = hiltViewModel()
                val state = viewModel.state.value
                BookmarkScreen(
                    state = state,
                    navigateToDetails = { item ->
                        navigateToDetails(navController = navController, item = item)
                    }
                )
            }
        }
    }
}

private fun navigate(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let {
                homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}

private fun navigateToDetails(navController: NavController, item: Item) {
    navController.currentBackStackEntry?.savedStateHandle?.set("item", item)
    navController.navigate(
        route = Route.BookDetailsScreen.route
    )
}
