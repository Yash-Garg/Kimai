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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Add
import androidx.compose.material.icons.twotone.Logout
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.yashgarg.kimai.di.CommonPreview
import dev.yashgarg.kimai.ui.navigation.HomeNavItems

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(homeState: HomeState) {
  var selectedItem by remember { mutableIntStateOf(0) }
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
    Box(modifier = Modifier.fillMaxSize().padding(values).pullRefresh(pullRefreshState)) {
      if (homeState.activity != null && !homeState.isLoading) {
        LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
          items(homeState.activity) { activity -> Text(text = activity.name) }
        }
      }

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
