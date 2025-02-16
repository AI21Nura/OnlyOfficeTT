package com.ainsln.feature.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Key
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ainsln.core.ui.component.InputTextField
import com.ainsln.core.ui.component.PasswordTextField
import com.ainsln.core.ui.theme.OnlyOfficeTheme
import com.ainsln.feature.auth.component.AuthAppBar
import com.ainsln.feature.auth.component.LoginButton
import com.ainsln.feature.auth.state.AuthFormState
import com.ainsln.feature.auth.state.AuthUiEvent
import com.ainsln.feature.auth.state.AuthViewModel
import com.ainsln.feature.auth.state.Field
import com.ainsln.feature.auth.state.ValidationError
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun AuthScreen(
    showSnackbar: suspend (String) -> Unit,
    viewModel: AuthViewModel = hiltViewModel()
){
    val uiState by viewModel.uiState.collectAsState()
    LaunchedEffect(viewModel.uiEvents) {
        viewModel.uiEvents.collectLatest { event ->
            when(event){
                is AuthUiEvent.ShowSnackbarError -> showSnackbar(event.msg)
            }
        }
    }

    AuthScreenContent(
        uiState = uiState,
        onFormFieldChange = viewModel::onValueChange,
        onLoginClick = viewModel::onLoginClick,
        useTestData = viewModel::useTestData
    )
}

@Composable
private fun AuthScreenContent(
    uiState: AuthFormState,
    onFormFieldChange: (Field, String) -> Unit,
    onLoginClick: () -> Unit,
    useTestData: ((Boolean) -> Unit)? = null //ONLY FOR DEMO
){
    Scaffold(
        topBar = {
            AuthAppBar(title = stringResource(R.string.auth_title))
        }
    ) { innerPadding ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        ){
            AuthInputBlock(
                uiState = uiState,
                onFormFieldChange = onFormFieldChange,
                onLoginClick = onLoginClick,
                useTestData = useTestData
            )
        }
    }
}

@Composable
private fun AuthInputBlock(
    uiState: AuthFormState,
    onFormFieldChange: (Field, String) -> Unit,
    onLoginClick: () -> Unit,
    useTestData: ((Boolean) -> Unit)? = null //ONLY FOR DEMO
){
    val focusManager = LocalFocusManager.current
    Box(Modifier.fillMaxWidth(0.8f).fillMaxHeight(0.8f)){
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            InputTextField(
                value = uiState.portal,
                onValueChange = { onFormFieldChange(Field.PORTAL, it) },
                leadingIcon = Icons.Default.Home,
                label = stringResource(R.string.portal_label),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                error = inputErrorText(uiState.inputErrors[Field.PORTAL]),
                isError = uiState.inputErrors[Field.PORTAL] != null
            )
            InputTextField(
                value = uiState.email,
                onValueChange = { onFormFieldChange(Field.EMAIL, it) },
                leadingIcon = Icons.Default.Email,
                label = stringResource(R.string.email_label),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                error = inputErrorText(uiState.inputErrors[Field.EMAIL]),
                isError = uiState.inputErrors[Field.EMAIL] != null

            )
            PasswordTextField(
                value = uiState.password,
                onValueChange = { onFormFieldChange(Field.PASSWORD, it) },
                leadingIcon = Icons.Default.Key,
                label = stringResource(R.string.password_label),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                error = inputErrorText(uiState.inputErrors[Field.PASSWORD]),
                isError = uiState.inputErrors[Field.PASSWORD] != null
            )

            LoginButton(
                isLoading = uiState.isLoading,
                onLoginClick = {
                    focusManager.clearFocus()
                    onLoginClick()
                }
            )

            //ONLY FOR DEMO
            useTestData?.let { TestDataSwitch(it) }
        }
    }

}

private fun inputErrorText(error: ValidationError?): (@Composable () -> Unit)? {
    return when (error) {
        is ValidationError.Empty -> ({ Text(stringResource(R.string.error_empty)) })
        is ValidationError.InvalidPortalFormat -> ({ Text(stringResource(R.string.error_invalid_portal)) })
        is ValidationError.InvalidEmailFormat -> ({ Text(stringResource(R.string.error_invalid_email)) })
        else -> null
    }
}

//ONLY FOR DEMO
@Composable
private fun TestDataSwitch(onCheckedChange: (Boolean) -> Unit){
    var checked by remember { mutableStateOf(false) }
    Column {
        Spacer(Modifier.weight(1f))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Use Test Data (Only For Demo)")
            Switch(
                checked = checked,
                onCheckedChange = {
                    checked = !checked
                    onCheckedChange(checked)
                }
            )
        }
    }
}

@Preview
@Composable
private fun AuthInputBlockPreview(){
    OnlyOfficeTheme {
        Surface(Modifier.fillMaxSize()) {
            AuthScreenContent(
                uiState = AuthFormState(),
                onFormFieldChange = {_, _ ->},
                onLoginClick = {}
            )
        }
    }
}
