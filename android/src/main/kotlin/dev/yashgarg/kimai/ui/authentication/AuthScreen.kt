/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai.ui.authentication

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.yashgarg.kimai.R
import dev.yashgarg.kimai.di.CommonPreview
import dev.yashgarg.kimai.ui.authentication.AuthViewModel.ValidationEvent
import dev.yashgarg.kimai.ui.common.CustomTextField
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen(
  authState: AuthFormState,
  validationEvent: SharedFlow<ValidationEvent>,
  onEvent: (AuthFormEvent) -> Unit,
  onSuccess: () -> Unit,
  onPop: () -> Unit,
) {
  val context = LocalContext.current
  var tokenHidden by rememberSaveable { mutableStateOf(true) }
  val snackbarHostState = remember { SnackbarHostState() }

  LaunchedEffect(context) {
    validationEvent.collectLatest {
      val message =
        when (it) {
          is ValidationEvent.Loading -> "Validating credentials..."
          is ValidationEvent.Failure -> it.msg
          is ValidationEvent.Success -> {
            onSuccess()
            "Success"
          }
        }

      snackbarHostState.showSnackbar(message)
    }
  }

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
          IconButton(onClick = onPop) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
          }
        }
      )
    },
    floatingActionButton = {
      ExtendedFloatingActionButton(
        modifier = Modifier.navigationBarsPadding(),
        onClick = { onEvent(AuthFormEvent.Save) },
        icon = { Icon(Icons.TwoTone.Check, contentDescription = null) },
        text = { Text("SAVE CONFIG") }
      )
    }
  ) { padding ->
    Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp, 0.dp)) {
      CustomTextField(
        readOnly = authState.isLoading,
        modifier = Modifier.fillMaxWidth(),
        value = authState.baseUrl,
        isError = authState.baseUrlError,
        trailingIcon = { Icon(imageVector = Icons.TwoTone.Dns, contentDescription = null) },
        onValueChange = { onEvent(AuthFormEvent.BaseUrlChanged(it)) },
        label = "Instance URL",
      )
      CustomTextField(
        readOnly = authState.isLoading,
        modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
        value = authState.username,
        isError = authState.usernameError,
        trailingIcon = { Icon(imageVector = Icons.TwoTone.Person, contentDescription = null) },
        onValueChange = { onEvent(AuthFormEvent.UsernameChanged(it)) },
        label = "Username / Email",
      )
      CustomTextField(
        readOnly = authState.isLoading,
        modifier = Modifier.fillMaxWidth(),
        value = authState.apiToken,
        isError = authState.apiTokenError,
        onValueChange = { onEvent(AuthFormEvent.ApiTokenChanged(it)) },
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

@CommonPreview
@Composable
fun AuthScreenPreview() {
  AuthScreen(
    authState = AuthFormState(),
    validationEvent = MutableSharedFlow(),
    onEvent = {},
    onSuccess = {},
    onPop = {},
  )
}
