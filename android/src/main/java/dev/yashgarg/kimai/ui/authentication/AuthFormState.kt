/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai.ui.authentication

import androidx.annotation.Keep

@Keep
data class AuthFormState(
  val isAuthenticated: Boolean = false,
  val baseUrl: String = "",
  val baseUrlError: Boolean = false,
  val username: String = "",
  val usernameError: Boolean = false,
  val apiToken: String = "",
  val apiTokenError: Boolean = false,
)
