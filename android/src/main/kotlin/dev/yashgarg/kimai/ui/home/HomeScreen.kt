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
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Add
import androidx.compose.material.icons.twotone.Logout
import androidx.compose.material.icons.twotone.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
  onLogoutClick: () -> Unit = {},
) {
  val innerNavController = rememberNavController()
  var showLogoutDialog by remember { mutableStateOf(false) }

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
        actions = {
          IconButton(onClick = { showLogoutDialog = true }) { Icon(Icons.TwoTone.Logout, null) }
        }
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

    if (showLogoutDialog) {
      AlertDialog(
        onDismissRequest = { showLogoutDialog = false },
        icon = { Icon(Icons.TwoTone.Warning, null, modifier = Modifier.size(35.dp)) },
        title = {
          Text(text = "Are you sure you want to logout?", fontWeight = FontWeight.SemiBold)
        },
        text = { Text(text = "This will delete all the stored data.") },
        confirmButton = {
          TextButton(
            onClick = {
              onLogoutClick()
              showLogoutDialog = false
            }
          ) {
            Text("Confirm")
          }
        },
        dismissButton = { TextButton(onClick = { showLogoutDialog = false }) { Text("Cancel") } }
      )
    }
  }
}

@CommonPreview
@Composable
fun HomeScreenPreview() {
  HomeScreen()
}
