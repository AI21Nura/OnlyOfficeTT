package com.ainsln.core.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.ainsln.core.model.storage.document.File
import com.ainsln.core.model.storage.document.Folder

@Composable
fun StorageItem(
    onItemClick: () -> Unit,
    icon: @Composable () -> Unit,
    title: String,
    subtitle: String? = null
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick() }
    ) {
        Column(Modifier.padding(vertical = 8.dp, horizontal = 16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                icon()
                Text(
                    text = title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
                subtitle?.let {
                    Text(
                        text = subtitle,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            }
            HorizontalDivider(Modifier.padding(top = 8.dp))
        }
    }
}

@Composable
fun FileItem(
    file: File,
    onFileClick: (Long) -> Unit,
    subtitle: String? = null
){
    StorageItem(
        onItemClick = { onFileClick(file.id) },
        icon = { CircleIcon(Icons.Outlined.Description) },
        title = file.title,
        subtitle = subtitle ?: file.contentLength
    )
}

@Composable
fun FolderItem(
    folder: Folder,
    onFolderClick: (Long, String) -> Unit,
    subtitle: String? = null
){
    StorageItem(
        onItemClick = { onFolderClick(folder.id, folder.title) },
        icon = { CircleIcon(Icons.Outlined.Folder) },
        title = folder.title,
        subtitle = subtitle
    )
}

