/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai.ui.navigation

import androidx.compose.animation.Crossfade
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun HomeNavigationBar(
  modifier: Modifier = Modifier,
  navController: NavController,
  items: List<BottomNavItem> = HomeNavItems.all,
) {
  var selectedItem by rememberSaveable { mutableIntStateOf(0) }

  NavigationBar(modifier = modifier) {
    items.forEachIndexed { index, item ->
      val isCurrentDestination = selectedItem == index

      NavigationBarItem(
        icon = {
          Crossfade(isCurrentDestination, label = "nav-label") {
            Icon(
              imageVector = if (it) item.selectedIcon else item.icon,
              contentDescription = item.label.replaceFirstChar(Char::uppercase),
            )
          }
        },
        label = { Text(item.label) },
        selected = isCurrentDestination,
        onClick = {
          if (!isCurrentDestination) {
            navController.navigate(item.route) { popUpTo(0) }
            selectedItem = index
          }
        },
      )
    }
  }
}
