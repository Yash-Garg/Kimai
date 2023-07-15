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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
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
    Scaffold {
      NavHost(
        modifier = Modifier.padding(it),
        navController = navController,
        startDestination = NavDestinations.startDestination.route
      ) {
        composable(NavDestinations.Landing.route) { LandingScreen() }
      }
    }
  }
}
