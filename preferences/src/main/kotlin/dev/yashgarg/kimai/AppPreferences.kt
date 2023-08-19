/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai

import android.content.SharedPreferences
import javax.inject.Inject

class AppPreferences @Inject constructor(private val sharedPrefs: SharedPreferences) {
  val all: Map<String, *>
    get() = sharedPrefs.all
}
