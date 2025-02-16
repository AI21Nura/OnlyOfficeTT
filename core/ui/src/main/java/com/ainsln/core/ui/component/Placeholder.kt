package com.ainsln.core.ui.component

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ainsln.core.common.exception.AppException
import com.ainsln.core.ui.R
import com.ainsln.core.ui.state.UiState
import com.ainsln.core.ui.utils.ResourceManager

@Composable
internal fun PlaceholderBlock(
    text: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    subtext: String? = null,
    actions: (@Composable () -> Unit)? = null
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(space = 12.dp, alignment = Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ){
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.outline,
            modifier = Modifier.size(96.dp)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth(0.7f)
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.outline
            )
            subtext?.let {
                Text(
                    text = subtext,
                    style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.Center,
                )
            }
        }
        actions?.invoke()
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(Modifier.size(64.dp))
    }
}

@Composable
fun ErrorScreen(
    error: AppException,
    onRetryClick: (() -> Unit)? = null
){
    val errMessageId = ResourceManager.getErrorStringId(error)
    PlaceholderBlock(
        text = stringResource(errMessageId),
        icon = ImageVector.vectorResource(R.drawable.ic_error),
        actions = {
            onRetryClick?.let {
                Button(
                    onClick = onRetryClick,
                    modifier = Modifier.fillMaxWidth(0.5f)
                ){
                    Text(
                        text = stringResource(R.string.retry)
                    )
                }
            }
        }
    )
}

@Composable
fun EmptyScreen(
    icon: ImageVector,
    message: String,
    subtext: String? = null,
    actions: (@Composable () -> Unit)? = null
){
    PlaceholderBlock(
        text = message,
        subtext = subtext,
        icon = icon,
        actions = actions
    )
}

@Composable
fun <T> RenderUiState(
    uiState: UiState<T>,
    successContent: @Composable (T) -> Unit,
    crossfadeLabel: String = "",
    errorAction: (() -> Unit)? = null
){
    Crossfade(
        targetState = uiState,
        label = crossfadeLabel
    ) { state ->
        when(state){
            is UiState.Loading -> LoadingScreen()
            is UiState.Error -> ErrorScreen(state.e, errorAction)
            is UiState.Success -> successContent(state.data)
        }
    }
}
