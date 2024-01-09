package com.example.bookhavenapp.presentation.navgraph

sealed class Route(
    val route: String
) {
    object OnBoardingScreen: Route(route = "onBoardingScreen")
    object HomeScreen: Route(route = "homeScreen")
    object SearchScreen: Route(route = "searchScreen")
    object BookmarkScreen: Route(route = "bookmarkScreen")
    object BookDetailsScreen: Route(route = "bookDetailsScreen")

    object AppStartNavigation: Route(route = "appStartNavigation")
    object BooksNavigation: Route(route = "booksNavigation")
    object BooksNavigatorScreen: Route(route = "booksNavigator")
}
