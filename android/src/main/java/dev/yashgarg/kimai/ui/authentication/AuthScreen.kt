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
import androidx.compose.material.icons.outlined.Dns
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.yashgarg.kimai.ui.common.CustomTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen(
  authState: AuthFormState,
  onEvent: (AuthFormEvent) -> Unit,
) {
  var passwordHidden by rememberSaveable { mutableStateOf(true) }

  Scaffold(
    topBar = {
      MediumTopAppBar(
        title = {
          Text(
            text = "Connect your instance",
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
          )
        },
        navigationIcon = {
          IconButton(onClick = {}) {
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
        modifier = Modifier.fillMaxWidth(),
        value = authState.baseUrl,
        isError = authState.baseUrlError != null,
        prefixText = "https://",
        trailingIcon = { Icon(imageVector = Icons.TwoTone.Dns, contentDescription = null) },
        onValueChange = { onEvent(AuthFormEvent.BaseUrlChanged(it)) },
        label = "Instance URL",
      )
      CustomTextField(
        modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
        value = authState.username,
        isError = authState.usernameError != null,
        trailingIcon = { Icon(imageVector = Icons.TwoTone.Person, contentDescription = null) },
        onValueChange = { onEvent(AuthFormEvent.BaseUrlChanged(it)) },
        label = "Username",
      )
      CustomTextField(
        modifier = Modifier.fillMaxWidth(),
        value = authState.password,
        isError = authState.passwordError != null,
        onValueChange = { onEvent(AuthFormEvent.PasswordChanged(it)) },
        label = "Password",
        keyboardType = KeyboardType.Password,
        passwordHidden = passwordHidden,
        trailingIcon = {
          IconButton(onClick = { passwordHidden = !passwordHidden }) {
            val visibilityIcon =
              if (passwordHidden) Icons.TwoTone.Visibility else Icons.TwoTone.VisibilityOff
            Icon(imageVector = visibilityIcon, contentDescription = null)
          }
        }
      )
    }
  }
}
