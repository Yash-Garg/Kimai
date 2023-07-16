/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Timelapse
import androidx.compose.material.icons.filled.ViewWeek
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Timelapse
import androidx.compose.material.icons.outlined.ViewWeek
import androidx.compose.material.icons.twotone.CalendarMonth
import androidx.compose.material.icons.twotone.Timelapse
import androidx.compose.material.icons.twotone.ViewWeek
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
  val label: String,
  val route: String,
  val icon: ImageVector,
  val selectedIcon: ImageVector,
)

object HomeNavItems {
  class Dashboard :
    BottomNavItem(
      label = "Dashboard",
      route = NavDestinations.Dashboard.route,
      icon = Icons.Outlined.Timelapse,
      selectedIcon = Icons.Filled.Timelapse,
    )

  class MyTimes :
    BottomNavItem(
      label = "My Times",
      route = NavDestinations.MyTimes.route,
      icon = Icons.Outlined.ViewWeek,
      selectedIcon = Icons.Filled.ViewWeek,
    )

  class Calendar :
    BottomNavItem(
      label = "Calendar",
      route = NavDestinations.Calendar.route,
      icon = Icons.Outlined.CalendarMonth,
      selectedIcon = Icons.Filled.CalendarMonth,
    )

  val all = listOf(Dashboard(), MyTimes(), Calendar())
}
