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
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModel() {
  var state by mutableStateOf(AuthFormState())
    private set

  fun onEvent(event: AuthFormEvent) {
    when (event) {
      is AuthFormEvent.BaseUrlChanged -> {
        state = state.copy(baseUrl = event.url)
      }
      is AuthFormEvent.UsernameChanged -> {
        state = state.copy(username = event.username)
      }
      is AuthFormEvent.PasswordChanged -> {
        state = state.copy(password = event.password)
      }
      AuthFormEvent.Save -> saveConfig()
    }
  }

  private fun saveConfig() {}
}
