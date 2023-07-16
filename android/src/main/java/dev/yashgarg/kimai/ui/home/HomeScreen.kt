/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Add
import androidx.compose.material.icons.twotone.Logout
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import dev.yashgarg.kimai.di.CommonPreview
import dev.yashgarg.kimai.ui.home.tabs.HomeNavGraph

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(homeState: HomeState) {
  val innerNavController = rememberNavController()
  val pullRefreshState = rememberPullRefreshState(homeState.isLoading, {})

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
        onClick = {},
      ) {
        Icon(Icons.TwoTone.Add, contentDescription = null)
      }
    }
  ) { values ->
    Box(modifier = Modifier.fillMaxSize().padding(values).pullRefresh(pullRefreshState)) {
      HomeNavGraph(
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
        navController = innerNavController,
      )

      PullRefreshIndicator(
        refreshing = homeState.isLoading,
        state = pullRefreshState,
        modifier = Modifier.align(Alignment.TopCenter),
        backgroundColor = MaterialTheme.colorScheme.surface,
        contentColor = contentColorFor(MaterialTheme.colorScheme.surface),
      )
    }
  }
}

@CommonPreview
@Composable
fun HomeScreenPreview() {
  HomeScreen(HomeState(activity = null, isLoading = true))
}
