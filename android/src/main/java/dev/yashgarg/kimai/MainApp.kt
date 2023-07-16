/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.yashgarg.kimai.ui.authentication.AuthScreen
import dev.yashgarg.kimai.ui.authentication.AuthViewModel
import dev.yashgarg.kimai.ui.home.HomeScreen
import dev.yashgarg.kimai.ui.home.HomeViewModel
import dev.yashgarg.kimai.ui.landing.LandingScreen
import dev.yashgarg.kimai.ui.navigation.NavDestinations
import dev.yashgarg.kimai.ui.theme.KimaiTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent { MainApp() }
  }
}

@Composable
fun MainApp() {
  val navController = rememberNavController()

  KimaiTheme {
    Scaffold { paddingValues ->
      NavHost(
        modifier = Modifier.padding(paddingValues),
        navController = navController,
        startDestination = NavDestinations.startDestination.route
      ) {
        composable(NavDestinations.Landing.route) {
          val isAuthenticated = hiltViewModel<AuthViewModel>().state.isAuthenticated
          if (isAuthenticated) {
            navController.navigate(NavDestinations.Home.route) { popUpTo(0) }
          } else {
            LandingScreen(
              onGetStartedClick = { navController.navigate(NavDestinations.Auth.route) }
            )
          }
        }
        composable(NavDestinations.Auth.route) {
          val viewModel = hiltViewModel<AuthViewModel>()

          AuthScreen(
            authState = viewModel.state,
            validationEvent = viewModel.event,
            onEvent = { viewModel.onEvent(it) },
            onSuccess = { navController.navigate(NavDestinations.Home.route) }
          )
        }
        composable(NavDestinations.Home.route) {
          val viewModel = hiltViewModel<HomeViewModel>()
          HomeScreen(homeState = viewModel.state)
        }
      }
    }
  }
}
