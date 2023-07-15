/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai.ui.authentication

sealed class AuthFormEvent {
  data class BaseUrlChanged(val url: String) : AuthFormEvent()

  data class UsernameChanged(val username: String) : AuthFormEvent()

  data class PasswordChanged(val password: String) : AuthFormEvent()

  object Save : AuthFormEvent()
}
