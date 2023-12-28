package com.example.bookhavenapp.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.bookhavenapp.presentation.onboarding.OnBoardingScreen
import com.example.bookhavenapp.presentation.onboarding.OnBoardingViewModel

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(
                route = Route.OnBoardingScreen.route
            ) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(event = viewModel::onEvent)
                // The line above is the same as this:
                // OnBoardingScreen(
                //    event = {
                //        viewModel.onEvent(it)
                //    }
                //)
            }
        }

        navigation(
            route = Route.BooksNavigation.route,
            startDestination = Route.BooksNavigatorScreen.route
        ) {
            composable(route = Route.BooksNavigatorScreen.route) {

            }
        }
    }
}