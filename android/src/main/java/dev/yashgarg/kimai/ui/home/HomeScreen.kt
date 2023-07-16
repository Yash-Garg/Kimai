/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Add
import androidx.compose.material.icons.twotone.Logout
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dev.yashgarg.kimai.ui.navigation.HomeNavItems

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
  var selectedItem by remember { mutableIntStateOf(0) }

  Scaffold(
    topBar = {
      MediumTopAppBar(
        title = {
          Text(
            text = "Instance Name",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
          )
        },
        actions = { IconButton(onClick = {}) { Icon(Icons.TwoTone.Logout, null) } }
      )
    },
    bottomBar = {
      NavigationBar {
        HomeNavItems.all.forEachIndexed { index, item ->
          NavigationBarItem(
            icon = { Icon(imageVector = item.icon, contentDescription = null) },
            label = { Text(item.name) },
            selected = selectedItem == index,
            onClick = { selectedItem = index }
          )
        }
      }
    },
    floatingActionButton = {
      ExtendedFloatingActionButton(
        modifier = Modifier.navigationBarsPadding(),
        onClick = {},
        icon = { Icon(Icons.TwoTone.Add, contentDescription = null) },
        text = { Text("Add Time") }
      )
    }
  ) { values ->
    Column(modifier = Modifier.padding(values)) {}
  }
}
