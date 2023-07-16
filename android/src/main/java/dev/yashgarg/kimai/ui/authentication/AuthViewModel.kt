/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai.ui.authentication

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yashgarg.kimai.api.KimaiRepository
import dev.yashgarg.kimai.daos.ConfigDao
import dev.yashgarg.kimai.models.InstanceConfig
import dev.yashgarg.kimai.util.ApiError
import dev.yashgarg.kimai.util.ApiException
import dev.yashgarg.kimai.util.ApiSuccess
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class AuthViewModel
@Inject
constructor(
  private val configDao: ConfigDao,
  private val repository: KimaiRepository,
) : ViewModel() {
  var state by mutableStateOf(AuthFormState())
    private set

  private val _event = MutableSharedFlow<ValidationEvent>()
  val event: SharedFlow<ValidationEvent> = _event.asSharedFlow()

  fun onEvent(event: AuthFormEvent) {
    when (event) {
      is AuthFormEvent.BaseUrlChanged -> {
        state =
          state.copy(
            baseUrl = event.url.trim(),
            baseUrlError = event.url.isEmpty(),
          )
      }
      is AuthFormEvent.UsernameChanged -> {
        state =
          state.copy(
            username = event.username.trim(),
            usernameError = event.username.isEmpty(),
          )
      }
      is AuthFormEvent.ApiTokenChanged -> {
        state =
          state.copy(
            apiToken = event.token.trim(),
            apiTokenError = event.token.isEmpty(),
          )
      }
      is AuthFormEvent.Save -> validateConfig()
    }
  }

  private fun validateConfig() {
    val urlCheck = state.baseUrl.isNotEmpty()
    val usernameCheck = state.username.isNotEmpty()
    val passwordCheck = state.apiToken.isNotEmpty()

    val hasError = listOf(urlCheck, usernameCheck, passwordCheck).any { !it }

    if (!hasError) {
      val config =
        InstanceConfig(
          id = 0,
          url = state.baseUrl,
          username = state.username,
          apiToken = state.apiToken,
          isSecure = true,
        )

      viewModelScope.launch { validateCredentials(config) }
    }
  }

  private suspend fun validateCredentials(config: InstanceConfig) {
    val result =
      repository.ping(
        authHeaders =
          mapOf(
            "X-AUTH-USER" to config.username,
            "X-AUTH-TOKEN" to config.apiToken,
          )
      )

    when (result) {
      is ApiSuccess -> {
        _event.emit(ValidationEvent.Success)
        withContext(Dispatchers.IO) { configDao.addInstance(config) }
      }
      is ApiError -> {
        _event.emit(ValidationEvent.Failure(result.code.toString()))
      }
      is ApiException -> {
        _event.emit(ValidationEvent.Failure(result.e.message ?: "Unknown error"))
      }
    }
  }

  sealed class ValidationEvent {
    class Failure(val msg: String) : ValidationEvent()

    object Success : ValidationEvent()
  }
}
