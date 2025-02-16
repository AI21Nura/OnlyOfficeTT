package com.ainsln.feature.auth.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ainsln.feature.auth.R

@Composable
internal fun LoginButton(
    isLoading: Boolean,
    onLoginClick: () -> Unit
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth().padding(top = 24.dp)
    ){
        if (isLoading){
            CircularProgressIndicator()
        } else {
            Button(
                onClick = onLoginClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.login_label),
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
    }
}
