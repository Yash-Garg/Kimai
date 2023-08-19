/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun Center(
  modifier: Modifier = Modifier,
  content: @Composable () -> Unit,
) {
  Box(modifier = Modifier.fillMaxSize().then(modifier), contentAlignment = Alignment.Center) {
    content()
  }
}
