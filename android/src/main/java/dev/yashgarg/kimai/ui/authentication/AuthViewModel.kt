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
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yashgarg.kimai.daos.ConfigDao
import dev.yashgarg.kimai.models.InstanceConfig
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val configDao: ConfigDao) : ViewModel() {
  var state by mutableStateOf(AuthFormState())
    private set

  fun onEvent(event: AuthFormEvent) {
    when (event) {
      is AuthFormEvent.BaseUrlChanged -> {
        state = state.copy(baseUrl = event.url.trim())
      }
      is AuthFormEvent.UsernameChanged -> {
        state = state.copy(username = event.username.trim())
      }
      is AuthFormEvent.PasswordChanged -> {
        state = state.copy(password = event.password.trim())
      }
      is AuthFormEvent.Save -> saveConfig()
    }
  }

  private fun saveConfig() {
    val config =
      InstanceConfig(
        id = 0,
        url = state.baseUrl,
        username = state.username,
        password = state.password,
        isSecure = state.baseUrl.startsWith("https")
      )

    configDao.addInstance(config)
  }
}
