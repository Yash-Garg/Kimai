/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai.ui.common

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun CustomTextField(
  modifier: Modifier = Modifier,
  value: String,
  onValueChange: (String) -> Unit,
  label: String,
  isError: Boolean = false,
  readOnly: Boolean = false,
  trailingIcon: @Composable (() -> Unit)? = null,
  passwordHidden: Boolean = false,
  leadingIcon: @Composable (() -> Unit)? = null,
  keyboardType: KeyboardType = KeyboardType.Text,
  suffixText: String? = null,
  prefixText: String? = null,
) {
  OutlinedTextField(
    modifier = Modifier.navigationBarsPadding().then(modifier),
    value = value,
    onValueChange = onValueChange,
    isError = isError,
    leadingIcon = leadingIcon,
    readOnly = readOnly,
    label = { Text(label) },
    suffix = suffixText?.let { { Text(it) } },
    prefix = prefixText?.let { { Text(it) } },
    keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
    singleLine = true,
    visualTransformation =
      if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
    trailingIcon = trailingIcon
  )
}
