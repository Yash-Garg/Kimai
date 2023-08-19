/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.twotone.Check
import androidx.compose.material.icons.twotone.Dns
import androidx.compose.material.icons.twotone.Person
import androidx.compose.material.icons.twotone.Visibility
import androidx.compose.material.icons.twotone.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.deliveryhero.whetstone.app.ApplicationScope
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Screen
import dev.yashgarg.kimai.R
import dev.yashgarg.kimai.ui.common.CustomTextField
import dev.yashgarg.kimai.ui.screens.AuthScreen.AuthEvent
import dev.yashgarg.kimai.ui.screens.AuthScreen.AuthFormEvent
import dev.yashgarg.kimai.ui.screens.AuthScreen.AuthState
import kotlinx.parcelize.Parcelize

@Parcelize
object AuthScreen : Screen {
  data class AuthState(
    val isAuthenticated: Boolean = false,
    val baseUrl: String = "",
    val baseUrlError: Boolean = false,
    val username: String = "",
    val usernameError: Boolean = false,
    val apiToken: String = "",
    val apiTokenError: Boolean = false,
    val isLoading: Boolean = false,
    val eventSink: (AuthEvent) -> Unit,
  ) : CircuitUiState

  sealed interface AuthEvent : CircuitUiEvent {
    data class FormEvent(val event: AuthFormEvent) : AuthEvent

    data object Authenticated : AuthEvent

    data object Cancel : AuthEvent
  }

  sealed class AuthFormEvent {
    data class BaseUrlChanged(val url: String) : AuthFormEvent()

    data class UsernameChanged(val username: String) : AuthFormEvent()

    data class ApiTokenChanged(val token: String) : AuthFormEvent()

    data object Save : AuthFormEvent()
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@CircuitInject(AuthScreen::class, ApplicationScope::class)
@Composable
fun AuthUi(
  state: AuthState,
  modifier: Modifier = Modifier,
) {
  var tokenHidden by rememberSaveable { mutableStateOf(true) }
  val snackbarHostState = remember { SnackbarHostState() }

  Scaffold(
    snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    topBar = {
      MediumTopAppBar(
        title = {
          Text(
            text = stringResource(R.string.connect_instance),
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
          )
        },
        navigationIcon = {
          IconButton(onClick = { state.eventSink(AuthEvent.Cancel) }) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
          }
        }
      )
    },
    floatingActionButton = {
      ExtendedFloatingActionButton(
        modifier = Modifier.navigationBarsPadding(),
        onClick = { state.eventSink(AuthEvent.FormEvent(AuthFormEvent.Save)) },
        icon = { Icon(Icons.TwoTone.Check, contentDescription = null) },
        text = { Text("Save Config") }
      )
    }
  ) { padding ->
    Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp, 0.dp)) {
      CustomTextField(
        readOnly = state.isLoading,
        modifier = Modifier.fillMaxWidth(),
        value = state.baseUrl,
        isError = state.baseUrlError,
        trailingIcon = { Icon(imageVector = Icons.TwoTone.Dns, contentDescription = null) },
        onValueChange = { state.eventSink(AuthEvent.FormEvent(AuthFormEvent.BaseUrlChanged(it))) },
        label = "Instance URL",
      )
      CustomTextField(
        readOnly = state.isLoading,
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        value = state.username,
        isError = state.usernameError,
        trailingIcon = { Icon(imageVector = Icons.TwoTone.Person, contentDescription = null) },
        onValueChange = { state.eventSink(AuthEvent.FormEvent(AuthFormEvent.UsernameChanged(it))) },
        label = "Username / Email",
      )
      CustomTextField(
        readOnly = state.isLoading,
        modifier = Modifier.fillMaxWidth(),
        value = state.apiToken,
        isError = state.apiTokenError,
        onValueChange = { state.eventSink(AuthEvent.FormEvent(AuthFormEvent.ApiTokenChanged(it))) },
        label = "Api Token",
        keyboardType = KeyboardType.Password,
        passwordHidden = tokenHidden,
        trailingIcon = {
          IconButton(onClick = { tokenHidden = !tokenHidden }) {
            val visibilityIcon =
              if (tokenHidden) Icons.TwoTone.Visibility else Icons.TwoTone.VisibilityOff
            Icon(imageVector = visibilityIcon, contentDescription = null)
          }
        }
      )
    }
  }
}
