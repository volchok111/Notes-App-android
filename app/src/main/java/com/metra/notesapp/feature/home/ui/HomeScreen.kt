package com.metra.notesapp.feature.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.metra.notesapp.library.ui.CustomColors
import com.metra.notesapp.library.ui.CustomDimensions
import com.metra.notesapp.library.ui.CustomText
import com.metra.notesapp.library.ui.CustomTopBar

@Composable
fun HomeScreen() {
    // TODO: Just for testing purposes
    val items = listOf(
        Item("Tasks", "Learn new things, Design things, Share my work, Stay hydrated"),
        Item("Test", "Can occur in nature."),
        Item("Travel", "Perfect time to finally create a list: Canada, Finland, Norway"),
        Item("Balance", "When a design is unbalanced, the individual elements dominate the whole."),
        Item(
            title = "Travel",
            description = "Perfect time to finally create a list: Canada, Finland, Norway",
            tasks = listOf("Canada", "Finland", "Norway")
        ),
        Item(
            title = "Test",
            tasks = listOf("Canada", "Finland", "Norway")
        ),
        Item(
            "Maths",
            "Can occur in nature, art, and design, where objects are mirrored blaaaaaaaaadnvjkanvkanvkasvasdkvnsv."
        ),
        Item(
            "Maths",
            "Can occur in nature, art, and design, where objects are mirrored blaaaaaaaaadnvjkanvkanvkasvasdkvnsv."
        ),
        Item(
            title = "Tasks",
            tasks = listOf("Learn new things", "Design things", "Share my work", "Stay hydrated")
        ),
        Item(
            title = "Test",
            description = "Can occur in nature."
        ),
        Item(
            title = "Travel",
            tasks = listOf("Canada", "Finland", "Norway")
        ),
        Item(
            title = "Balance",
            description = "When a design is unbalanced, the individual elements dominate the whole."
        )
    )
    HomeScreenImpl(items)
}

@Composable
private fun HomeScreenImpl(items: List<Item>) {
    Scaffold(
        topBar = {
            CustomTopBar(title = "Test")
        },
        containerColor = CustomColors.white
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            val leftColumnItems = items.filterIndexed { index, _ -> index % 2 == 0 }
            val rightColumnItems = items.filterIndexed { index, _ -> index % 2 != 0 }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(CustomDimensions.sizeXS),
                verticalArrangement = Arrangement.spacedBy(CustomDimensions.sizeXS),
                contentPadding = PaddingValues(bottom = CustomDimensions.sizeXS)
            ) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(CustomDimensions.sizeXS)
                    ) {
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(CustomDimensions.sizeXS)
                        ) {
                            leftColumnItems.forEach { item ->
                                ReminderCardItem(item = item, onClick = {})
                            }
                        }

                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(CustomDimensions.sizeXS)
                        ) {
                            rightColumnItems.forEach { item ->
                                ReminderCardItem(item = item, onClick = {})
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ReminderCardItem(
    item: Item,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val taskStates = remember {
        mutableStateListOf<Boolean>().apply {
            repeat(item.tasks?.size ?: 0) { add(false) }
        }
    }
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(CustomDimensions.sizeXXS),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        onClick = { onClick() }
    ) {
        Column(
            modifier = Modifier.padding(CustomDimensions.sizeS)
        ) {
            CustomText(
                text = item.title,
                style = MaterialTheme.typography.headlineMedium,
                color = CustomColors.black
            )

            if (item.description != null) {
                Spacer(modifier = Modifier.height(CustomDimensions.sizeXS))
                CustomText(
                    text = item.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = CustomColors.black
                )
            }

            if (item.tasks != null) {
                Spacer(modifier = Modifier.height(CustomDimensions.sizeXS))
                item.tasks.forEachIndexed { index, task ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 0.dp, bottom = CustomDimensions.sizeXS)
                    ) {
//                        CustomCheckbox(checked = taskStates[index], onCheckedChange = { taskStates[index] = it })
                        Checkbox(
                            checked = taskStates[index],
                            onCheckedChange = { taskStates[index] = it },
                            modifier = Modifier
                                .size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(CustomDimensions.sizeXS))
                        CustomText(
                            text = task,
                            style = MaterialTheme.typography.bodyMedium,
                            color = CustomColors.black
                        )
                    }
                }
            }
        }
    }
}

// TODO: Just for testing purposes
data class Item(
    val title: String,
    val description: String? = null,
    val tasks: List<String>? = null
)
