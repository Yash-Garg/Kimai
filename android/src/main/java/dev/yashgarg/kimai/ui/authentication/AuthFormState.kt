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
  val baseUrl: String = "",
  val baseUrlError: String? = null,
  val username: String = "",
  val usernameError: String? = null,
  val password: String = "",
  val passwordError: String? = null,
)
