/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai.ui.home.tabs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.yashgarg.kimai.toDateTime
import dev.yashgarg.kimai.toTime
import dev.yashgarg.kimai.ui.common.Center
import dev.yashgarg.kimai.ui.home.ActivityState
import dev.yashgarg.kimai.ui.home.HomeState
import dev.yashgarg.kimai.ui.home.ProjectsState
import dev.yashgarg.kimai.ui.home.TimesheetState

@Composable
fun MyTimesScreen(state: HomeState) {
  Box(modifier = Modifier.fillMaxSize()) {
    when (state.timesheetState) {
      is TimesheetState.Loading -> {
        Center { LinearProgressIndicator() }
      }
      is TimesheetState.Error -> {
        Center { Text(text = "Error: ${state.timesheetState.message}") }
      }
      is TimesheetState.Success -> {
        val timesheets = state.timesheetState.timesheets
        val activity = (state.activityState as? ActivityState.Success)?.activities
        val projects = (state.projectsState as? ProjectsState.Success)?.projects

        if (timesheets.isEmpty()) {
          Center { Text(text = "No timesheets found") }
        } else {
          LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(timesheets) { sheet ->
              val activityName =
                activity?.find { it.id.toInt() == sheet.activity }?.name ?: "Unknown"

              val projectName = projects?.find { it.id.toInt() == sheet.project }?.name ?: "Unknown"

              Card(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp)) {
                Column(modifier = Modifier.fillMaxSize().padding(12.dp)) {
                  Text(text = "$projectName - $activityName")
                  Text(text = sheet.begin.toDateTime())
                  Text(text = sheet.duration.toTime())
                }
              }
            }
          }
        }
      }
    }
  }
}
