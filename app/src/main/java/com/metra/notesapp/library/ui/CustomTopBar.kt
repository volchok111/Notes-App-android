package com.metra.notesapp.library.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.metra.notesapp.library.ui.CustomColors.black
import com.metra.notesapp.library.ui.CustomColors.chrome400
import com.metra.notesapp.library.ui.CustomColors.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(
    title: String,
    onBackClick: (() -> Unit)? = null
) {
    TopAppBar(
        colors = TopAppBarColors(
            containerColor = white,
            scrolledContainerColor = chrome400,
            navigationIconContentColor = black,
            titleContentColor = black,
            actionIconContentColor = black
        ),
        title = {
            Column(
                modifier = Modifier.padding(start = 12.dp)
            ) {
                CustomText(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall,
                    color = black,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        navigationIcon = {
            if (onBackClick != null) {
                IconButton(onClick = onBackClick) {
                    CustomIcon(
                        icon = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        }
    )
}
