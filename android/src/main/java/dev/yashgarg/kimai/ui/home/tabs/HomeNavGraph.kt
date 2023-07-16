/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai.ui.home.tabs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.yashgarg.kimai.ui.home.HomeViewModel
import dev.yashgarg.kimai.ui.navigation.NavDestinations

@Composable
fun HomeNavGraph(
  modifier: Modifier = Modifier,
  navController: NavHostController,
) {
  val viewModel = hiltViewModel<HomeViewModel>()

  NavHost(
    modifier = modifier,
    navController = navController,
    startDestination = NavDestinations.Dashboard.route
  ) {
    composable(NavDestinations.Dashboard.route) { DashboardScreen(state = viewModel.state) }

    composable(NavDestinations.MyTimes.route) { MyTimesScreen(state = viewModel.state) }

    composable(NavDestinations.Calendar.route) {
      Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Calendar")
      }
    }
  }
}
