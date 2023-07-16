/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.CalendarMonth
import androidx.compose.material.icons.twotone.Timelapse
import androidx.compose.material.icons.twotone.ViewWeek
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val name: String, val icon: ImageVector)

object HomeNavItems {
  class Dashboard : BottomNavItem("Dashboard", Icons.TwoTone.Timelapse)

  class MyTimes : BottomNavItem("My Times", Icons.TwoTone.ViewWeek)

  class Calendar : BottomNavItem("Calendar", Icons.TwoTone.CalendarMonth)

  val all = listOf(Dashboard(), MyTimes(), Calendar())
}
