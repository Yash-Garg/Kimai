/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.yashgarg.kimai.ui.navigation.NavDestinations

@Composable
fun HomeNavGraph(
  modifier: Modifier = Modifier,
  navController: NavHostController,
) {
  NavHost(
    modifier = modifier,
    navController = navController,
    startDestination = NavDestinations.Dashboard.route
  ) {
    composable(NavDestinations.Dashboard.route) {
      Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Dashboard")
      }
    }

    composable(NavDestinations.MyTimes.route) {
      Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "My Times")
      }
    }

    composable(NavDestinations.Calendar.route) {
      Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Calendar")
      }
    }
  }
}
