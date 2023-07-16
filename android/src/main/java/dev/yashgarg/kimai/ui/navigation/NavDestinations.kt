/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai.ui.navigation

sealed class NavDestinations(val route: String) {
  object Landing : NavDestinations("landing")

  object Auth : NavDestinations("auth")

  object Home : NavDestinations("home")

  object Dashboard : NavDestinations("dashboard")

  object MyTimes : NavDestinations("my-times")

  object Calendar : NavDestinations("calendar")

  companion object {
    val startDestination
      get() = Landing
  }
}
