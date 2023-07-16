/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun ColorScheme.surfaceColorAtNavigationBarElevation(): Color {
  val elevation = LocalAbsoluteTonalElevation.current + NavigationBarDefaults.Elevation
  return surfaceColorAtElevation(elevation)
}
