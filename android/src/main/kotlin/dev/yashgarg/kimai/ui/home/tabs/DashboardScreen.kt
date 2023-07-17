/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai.ui.home.tabs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.yashgarg.kimai.ui.common.Center
import dev.yashgarg.kimai.ui.home.ActivityState
import dev.yashgarg.kimai.ui.home.HomeState

@Composable
fun DashboardScreen(state: HomeState) {
  Box(modifier = Modifier.fillMaxSize()) {
    when (state.activityState) {
      is ActivityState.Error -> {}
      is ActivityState.Loading -> {
        Center { LinearProgressIndicator() }
      }
      is ActivityState.Success -> {}
    }
  }
}
