/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai.ui.presenters

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.deliveryhero.whetstone.app.ApplicationScope
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import dev.yashgarg.kimai.ui.screens.AuthScreen
import dev.yashgarg.kimai.ui.screens.AuthScreen.AuthEvent
import dev.yashgarg.kimai.ui.screens.AuthScreen.AuthFormEvent
import dev.yashgarg.kimai.ui.screens.AuthScreen.AuthState

@SuppressLint("ComposableNaming")
@CircuitInject(AuthScreen::class, ApplicationScope::class)
@Composable
fun AuthPresenter(navigator: Navigator): AuthState {
  var baseUrl by rememberSaveable { mutableStateOf("") }
  var username by rememberSaveable { mutableStateOf("") }
  var apiToken by rememberSaveable { mutableStateOf("") }

  return AuthState(
    baseUrl = baseUrl,
    username = username,
    apiToken = apiToken,
  ) { authEvent ->
    when (authEvent) {
      is AuthEvent.Authenticated -> {}
      is AuthEvent.FormEvent -> {
        when (authEvent.event) {
          is AuthFormEvent.ApiTokenChanged -> {
            apiToken = authEvent.event.token
          }
          is AuthFormEvent.BaseUrlChanged -> {
            baseUrl = authEvent.event.url
          }
          is AuthFormEvent.UsernameChanged -> {
            username = authEvent.event.username
          }
          is AuthFormEvent.Save -> {}
        }
      }
      is AuthEvent.Cancel -> navigator.pop()
    }
  }
}
