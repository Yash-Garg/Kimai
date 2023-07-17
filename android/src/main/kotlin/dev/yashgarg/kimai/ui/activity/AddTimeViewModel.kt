/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai.ui.activity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yashgarg.kimai.api.KimaiRepository
import dev.yashgarg.kimai.toOnlyDate
import dev.yashgarg.kimai.toTime
import dev.yashgarg.kimai.ui.common.ValidationEvent
import dev.yashgarg.kimai.util.ApiError
import dev.yashgarg.kimai.util.ApiException
import dev.yashgarg.kimai.util.ApiSuccess
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AddTimeViewModel
@Inject
constructor(
  private val repository: KimaiRepository,
) : ViewModel() {
  var state by mutableStateOf(AddTimeViewState())
    private set

  private val _event = MutableSharedFlow<ValidationEvent>()
  val event: SharedFlow<ValidationEvent> = _event.asSharedFlow()

  init {
    viewModelScope.launch { loadFieldData() }
  }

  fun onEvent(event: AddTimeViewEvent) {
    when (event) {
      is AddTimeViewEvent.ActivitySelected -> {
        state = state.copy(selectedActivity = event.activity)
      }
      is AddTimeViewEvent.CustomerSelected -> {
        state = state.copy(selectedCustomer = event.customer)
      }
      is AddTimeViewEvent.DateSelected -> {
        state = state.copy(selectedDate = event.date)
      }
      is AddTimeViewEvent.DescriptionSelected -> {
        state = state.copy(description = event.description)
      }
      is AddTimeViewEvent.DurationSelected -> {
        state = state.copy(selectedDuration = event.duration)
      }
      is AddTimeViewEvent.ProjectSelected -> {
        state = state.copy(selectedProject = event.project)
      }
      is AddTimeViewEvent.StartTimeSelected -> {
        state = state.copy(selectedStartTime = event.time)
      }
      is AddTimeViewEvent.Save -> submitActivity()
    }
  }

  private suspend fun loadFieldData() {
    state =
      when (val result = repository.getProjects()) {
        is ApiSuccess -> {
          state.copy(projects = result.data, selectedProject = result.data[0].name)
        }
        is ApiError -> {
          state.copy(error = result.message)
        }
        is ApiException -> {
          state.copy(error = result.e.toString())
        }
      }

    state =
      when (val result = repository.getActivities()) {
        is ApiSuccess -> {
          state.copy(activities = result.data, selectedActivity = result.data[0].name)
        }
        is ApiError -> {
          state.copy(error = result.message)
        }
        is ApiException -> {
          state.copy(error = result.e.toString())
        }
      }

    state =
      when (val result = repository.getCustomers()) {
        is ApiSuccess -> {
          state.copy(customers = result.data, selectedCustomer = result.data[0].name)
        }
        is ApiError -> {
          state.copy(error = result.message)
        }
        is ApiException -> {
          state.copy(error = result.e.toString())
        }
      }

    state = state.copy(isLoading = false)
  }

  private fun submitActivity() {
    viewModelScope.launch { _event.emit(ValidationEvent.Loading) }
    val startDt = "${state.selectedDate?.toOnlyDate()}T${state.selectedStartTime?.toTime()}"
    val endTime =
      state.selectedStartTime!!.plus(
        state.durations[state.selectedDuration!!]!!.inWholeMilliseconds
      )
    val endDt = "${state.selectedDate?.toOnlyDate()}T${endTime.toTime()}"
    val projectId =
      state.projects
        .first { it.parentTitle == state.selectedCustomer && it.name == state.selectedProject }
        .id
        .toInt()
    val activityId = state.activities.first { it.name == state.selectedActivity }.id.toInt()

    viewModelScope.launch {
      val result =
        repository.createTimeSheet(
          begin = startDt,
          end = endDt,
          project = projectId,
          activity = activityId,
        )

      when (result) {
        is ApiSuccess -> {
          _event.emit(ValidationEvent.Success)
        }
        is ApiError -> {
          _event.emit(ValidationEvent.Failure(result.message ?: "Unknown error"))
        }
        is ApiException -> {
          _event.emit(ValidationEvent.Failure(result.e.toString()))
        }
      }
    }
  }
}
