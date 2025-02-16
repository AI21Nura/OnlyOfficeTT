package com.ainsln.core.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ainsln.core.ui.R
import com.ainsln.core.ui.theme.OnlyOfficeTheme

@Composable
fun InputTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    leadingIcon: ImageVector,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    error: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    val focusRequester = remember { FocusRequester() }
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(text = label)
        },
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null
            )
        },
        trailingIcon = trailingIcon,
        keyboardOptions = keyboardOptions,
        supportingText = error,
        isError = isError,
        singleLine = true,
        shape = RoundedCornerShape(8.dp),
        visualTransformation = visualTransformation,
        modifier = modifier.fillMaxWidth().focusRequester(focusRequester)
    )
}

@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    leadingIcon: ImageVector,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    error: (@Composable () -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
) {
    var passwordVisible by remember { mutableStateOf(false) }
    val (passwordIcon, passwordIconDesc) =
        if (passwordVisible) Icons.Default.VisibilityOff to stringResource(R.string.hide_password)
        else Icons.Default.Visibility to stringResource(R.string.show_password)

    InputTextField(
        value = value,
        onValueChange = onValueChange,
        label = label,
        leadingIcon = leadingIcon,
        modifier = modifier,
        isError = isError,
        error = error,
        keyboardOptions = keyboardOptions,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(passwordIcon, passwordIconDesc)
            }
        }
    )
}

@Composable
@Preview(showBackground = true)
fun InputTextFieldPreview() {
    OnlyOfficeTheme {
        InputTextField(
            value = "",
            onValueChange = {},
            label = "Portal",
            leadingIcon = Icons.Default.Home
        )
    }
}
