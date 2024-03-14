package com.example.pruebatecnicaalten.presentation.ui.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.pruebatecnicaalten.R
import com.example.pruebatecnicaalten.presentation.ui.theme.oswaldFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersTopAppBar(
    title: String,
    onBackPressed: () -> Unit = {},
    textColor: Color = Color.Black,
    containerColor: Color = Color.White,
    onSearchUserClicked: (() -> Unit)? = null,
) {

    var showMenu by remember { mutableStateOf(false) }

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(containerColor = containerColor),
        title = {
            Text(
                text = title.uppercase(),
                fontFamily = oswaldFontFamily,
                fontWeight = FontWeight.W500,
                fontSize = 16.sp,
                color = textColor
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.icon_back),
                    contentDescription = "Back",
                    tint = textColor
                )
            }
        },

        actions = {
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(
                    Icons.Default.MoreVert,
                    contentDescription = "More",
                    tint = textColor
                )
            }
            DropdownMenu(
                expanded = showMenu && onSearchUserClicked != null,
                onDismissRequest = { showMenu = false }
            ) {
                DropdownMenuItem(
                    text = { Text(stringResource(R.string.topbar_action_find_users)) },
                    onClick = {
                        showMenu = false
                        onSearchUserClicked?.invoke()
                    }
                )
            }
        }
    )
}