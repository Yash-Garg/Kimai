/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai.ui.common

sealed class ValidationEvent {
  class Failure(val msg: String) : ValidationEvent()

  object Success : ValidationEvent()

  object Loading : ValidationEvent()
}
