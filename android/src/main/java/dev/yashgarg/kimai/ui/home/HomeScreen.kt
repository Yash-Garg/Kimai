/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Add
import androidx.compose.material.icons.twotone.Logout
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import dev.yashgarg.kimai.di.CommonPreview
import dev.yashgarg.kimai.ui.navigation.HomeNavGraph
import dev.yashgarg.kimai.ui.navigation.HomeNavigationBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
  onAddTimeClick: () -> Unit = {},
) {
  val innerNavController = rememberNavController()

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
    bottomBar = { HomeNavigationBar(navController = innerNavController) },
    floatingActionButton = {
      FloatingActionButton(
        modifier = Modifier.navigationBarsPadding(),
        onClick = onAddTimeClick,
      ) {
        Icon(Icons.TwoTone.Add, contentDescription = null)
      }
    }
  ) { values ->
    HomeNavGraph(
      modifier = Modifier.fillMaxSize().padding(values).padding(horizontal = 16.dp),
      navController = innerNavController,
    )
  }
}

@CommonPreview
@Composable
fun HomeScreenPreview() {
  HomeScreen()
}
