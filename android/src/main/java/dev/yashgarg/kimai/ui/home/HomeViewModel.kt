/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai.ui.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yashgarg.kimai.api.KimaiRepository
import dev.yashgarg.kimai.util.ApiError
import dev.yashgarg.kimai.util.ApiException
import dev.yashgarg.kimai.util.ApiSuccess
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel
@Inject
constructor(
  private val repository: KimaiRepository,
) : ViewModel() {
  var state by mutableStateOf(HomeState())
    private set

  init {
    viewModelScope.launch { loadActivities() }
  }

  private suspend fun loadActivities() {
    viewModelScope.launch {
      val result = repository.getActivities()

      state =
        when (result) {
          is ApiSuccess -> {
            state.copy(isLoading = false, activity = result.data, error = null)
          }
          is ApiError -> {
            Log.e("HomeViewModel", result.message ?: "Unknown error")
            state.copy(isLoading = false, error = result.message ?: "Unknown error")
          }
          is ApiException -> {
            Log.e("HomeViewModel", result.e.toString())
            state.copy(isLoading = false, error = result.e.toString())
          }
        }
    }
  }
}
