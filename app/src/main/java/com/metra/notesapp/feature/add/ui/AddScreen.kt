package com.metra.notesapp.feature.add.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.metra.notesapp.feature.add.presentation.AddViewModel
import com.metra.notesapp.library.ui.CustomColors
import com.metra.notesapp.library.ui.CustomDimensions
import com.metra.notesapp.library.ui.CustomText
import com.metra.notesapp.library.ui.LineTextBlock
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddScreen() {
    val viewModel = koinViewModel<AddViewModel>()
    val state = viewModel.states.collectAsState()
    AddScreenImpl(state = state.value)
}

@Composable
private fun AddScreenImpl(
    state: AddViewModel.State
) {
    var checkboxItems by remember { mutableStateOf(listOf("")) }
    val focusRequester = remember { FocusRequester() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(CustomDimensions.sizeS)
            .systemBarsPadding()
    ) {
        CustomText(
            text = "Design Principles",
            style = MaterialTheme.typography.headlineMedium,
            color = CustomColors.black,
            maxLines = 1,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(CustomDimensions.sizeXS))

        CustomText(
            text = "Sun 10:24 | 4096 characters",
            style = MaterialTheme.typography.bodyMedium,
            color = CustomColors.chrome600,
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(CustomDimensions.sizeS))

        LineTextBlock(
            text = "The beauty comes from connecting principles for high conversion rates with world-class design.",
            color = CustomColors.black
        )

        Spacer(modifier = Modifier.height(CustomDimensions.sizeM))

        checkboxItems.forEachIndexed { index, text ->

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = false,
                    onCheckedChange = {}
                )

                BasicTextField(
                    value = text,
                    onValueChange = { newText ->
                        checkboxItems = checkboxItems.toMutableList().also { it[index] = newText }

                        // add new row if user hits enter and it's the last item
                        if (newText.endsWith("\n") && index == checkboxItems.lastIndex) {
                            checkboxItems = checkboxItems + ""
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester)
                        .onFocusChanged { focusState ->

                        }
                )
            }

            Spacer(modifier = Modifier.height(CustomDimensions.sizeXS))

            // automatically request focus for the new row
            if (index == checkboxItems.lastIndex && text == "") {
                LaunchedEffect(Unit) {
                    focusRequester.requestFocus()
                }
            }
        }
    }
}

