/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai.ui.activity

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.ArrowBack
import androidx.compose.material.icons.twotone.CalendarMonth
import androidx.compose.material.icons.twotone.Check
import androidx.compose.material.icons.twotone.Timer
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.yashgarg.kimai.di.CommonPreview
import dev.yashgarg.kimai.toDate
import dev.yashgarg.kimai.toTime
import dev.yashgarg.kimai.ui.common.Center
import dev.yashgarg.kimai.ui.common.CustomTextField
import dev.yashgarg.kimai.ui.common.DropDownTextField
import dev.yashgarg.kimai.ui.common.TimePickerDialog
import dev.yashgarg.kimai.ui.common.ValidationEvent
import java.util.Calendar
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTimeScreen(
  state: AddTimeViewState,
  validationEvent: SharedFlow<ValidationEvent>,
  onEvent: (AddTimeViewEvent) -> Unit = {},
  onPop: () -> Unit,
) {
  val cal = Calendar.getInstance()
  var showDatePicker by remember { mutableStateOf(false) }
  var showTimePicker by remember { mutableStateOf(false) }
  val datePickerState =
    rememberDatePickerState(
      initialSelectedDateMillis = System.currentTimeMillis(),
    )
  val timePickerState =
    rememberTimePickerState(
      is24Hour = true,
      initialHour = cal.get(Calendar.HOUR),
      initialMinute = cal.get(Calendar.MINUTE)
    )
  val confirmEnabled = remember { derivedStateOf { datePickerState.selectedDateMillis != null } }

  val context = LocalContext.current
  LaunchedEffect(context) {
    validationEvent.collectLatest {
      val message =
        when (it) {
          is ValidationEvent.Loading -> "Sending data through API..."
          is ValidationEvent.Failure -> it.msg
          is ValidationEvent.Success -> {
            onPop()
            "Successfully added time entry!"
          }
        }

      Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
  }

  LaunchedEffect(Unit) {
    println("AddTimeScreen: LaunchedEffect")
    onEvent(AddTimeViewEvent.DateSelected(datePickerState.selectedDateMillis!!))
    onEvent(AddTimeViewEvent.StartTimeSelected(cal.timeInMillis))
    onEvent(AddTimeViewEvent.DurationSelected(state.durations.keys.first()))
  }

  Scaffold(
    topBar = {
      MediumTopAppBar(
        title = {
          Text(
            text = "Add Activity",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
          )
        },
        navigationIcon = {
          IconButton(onClick = onPop) { Icon(Icons.TwoTone.ArrowBack, contentDescription = null) }
        }
      )
    },
    floatingActionButton = {
      FloatingActionButton(onClick = { onEvent(AddTimeViewEvent.Save) }) {
        Icon(Icons.TwoTone.Check, contentDescription = null)
      }
    }
  ) { values ->
    if (state.isLoading) {
      Center { LinearProgressIndicator() }
      return@Scaffold
    }

    if (state.error != null) {
      Center { Text(text = state.error) }
      return@Scaffold
    }

    Column(modifier = Modifier.fillMaxSize().padding(values).padding(horizontal = 16.dp)) {
      CustomTextField(
        hideKeyboard = true,
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        label = "Date",
        supportText = "Use the calendar icon to select a date",
        value = state.selectedDate!!.toDate(),
        trailingIcon = {
          IconButton(onClick = { showDatePicker = true }) {
            Icon(Icons.TwoTone.CalendarMonth, contentDescription = null)
          }
        },
        readOnly = true,
        onValueChange = { onEvent(AddTimeViewEvent.DateSelected(it.toLong())) },
      )
      CustomTextField(
        hideKeyboard = true,
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        label = "Start Time",
        supportText = "Use the clock icon to select a time",
        value = state.selectedStartTime!!.toTime(),
        trailingIcon = {
          IconButton(onClick = { showTimePicker = true }) {
            Icon(Icons.TwoTone.Timer, contentDescription = null)
          }
        },
        readOnly = true,
        onValueChange = {},
      )
      DropDownTextField(
        title = "Duration",
        options = state.durations.keys.toList(),
        supportText = "in hours",
        onValueChange = { onEvent(AddTimeViewEvent.DurationSelected(it)) },
      )
      DropDownTextField(
        title = "Customer",
        options = state.customers.map { it.name }.toList(),
        onValueChange = { onEvent(AddTimeViewEvent.CustomerSelected(it)) },
      )
      DropDownTextField(
        title = "Project",
        options = state.projects.map { it.name }.toList(),
        onValueChange = { onEvent(AddTimeViewEvent.ProjectSelected(it)) },
      )
      DropDownTextField(
        title = "Activity",
        options = state.activities.map { it.name }.toList(),
        onValueChange = { onEvent(AddTimeViewEvent.ActivitySelected(it)) },
      )
    }

    if (showDatePicker) {
      DatePickerDialog(
        onDismissRequest = { showDatePicker = false },
        confirmButton = {
          TextButton(
            onClick = {
              showDatePicker = false
              onEvent(AddTimeViewEvent.DateSelected(datePickerState.selectedDateMillis!!))
            },
            enabled = confirmEnabled.value
          ) {
            Text("OK")
          }
        },
        dismissButton = { TextButton(onClick = { showDatePicker = false }) { Text("Cancel") } }
      ) {
        DatePicker(state = datePickerState)
      }
    }

    if (showTimePicker) {
      TimePickerDialog(
        onCancel = { showTimePicker = false },
        onConfirm = {
          cal.set(Calendar.HOUR_OF_DAY, timePickerState.hour)
          cal.set(Calendar.MINUTE, timePickerState.minute)
          cal.set(Calendar.SECOND, 0)

          cal.timeInMillis

          showTimePicker = false
          onEvent(AddTimeViewEvent.StartTimeSelected(cal.timeInMillis))
        },
      ) {
        TimeInput(state = timePickerState)
      }
    }
  }
}

@CommonPreview
@Composable
fun AddTimeScreenPreview() {
  AddTimeScreen(onPop = {}, state = AddTimeViewState(), validationEvent = MutableSharedFlow())
}
