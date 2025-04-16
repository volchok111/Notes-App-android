package com.metra.notesapp.feature.add.ui

import CustomCheckbox
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.metra.notesapp.R
import com.metra.notesapp.library.ui.CustomColors
import com.metra.notesapp.library.ui.CustomColors.black
import com.metra.notesapp.library.ui.CustomDimensions
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Preview
@Composable
fun AddEditScreen(){
    AddEditScreenImpl(
        AddEditItem(),
        onBackClick = { },
        onSaveClick = { },
        returnToPreviousStepClick = { },
        returnToNextStepClick = { }

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditScreenImpl(
    addEditItem: AddEditItem,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    returnToPreviousStepClick: () -> Unit,
    returnToNextStepClick: () -> Unit
) {

    var tasks by remember { mutableStateOf(addEditItem.tasks) }
    var selectedDate by remember { mutableStateOf<LocalDate?>(addEditItem.selectedDate) }
    var showDatePicker by remember { mutableStateOf(false) }
    var selectedPriority by remember { mutableStateOf<String?>(addEditItem.selectedPriority) }
    var showPriorityPicker by remember { mutableStateOf(false) }
    var selectedColor by remember { mutableStateOf<String?>(addEditItem.selectedColor) }
    var showColorPicker by remember { mutableStateOf(false) }
    var selectedRepeat by remember { mutableStateOf<String?>(addEditItem.repeatType) }
    var showRepeatPicker by remember { mutableStateOf(false) }


    Scaffold(
        modifier = Modifier,
        topBar = {
            TopActionBar(
                onBackClick = onBackClick,
                onSaveClick = onSaveClick,
                returnToPreviousStepClick = returnToPreviousStepClick,
                returnToNextStepClick = returnToNextStepClick
            )
        },
        bottomBar = {
            BottomPanel(
                onAddTask = { tasks = tasks.toMutableList().apply {
                    add(Task(false,""))
                } },
                onPickDate = { showDatePicker = true },
                onPickColor = { showColorPicker = true},
                onSetPriority = { showPriorityPicker = true },
                onSetRepeat = {showRepeatPicker = true }
            )
        },
    ) {paddingValues ->
        var title by remember { mutableStateOf(addEditItem.title) }
        var description by remember { mutableStateOf(addEditItem.description) }
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(CustomColors.white)
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(CustomDimensions.sizeS))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                maxLines = 5
            )

            Spacer(modifier = Modifier.height(CustomDimensions.sizeM))

            //Display tasks

            tasks.forEachIndexed { index, task ->
                TaskInputField(
                    task = task,
                    onCheckedChange = { isChecked ->
                        tasks = tasks.mapIndexed { i, t ->
                            if (i == index) t.copy(isChecked = isChecked) else t
                        }.toMutableList()
                    },
                    onTextChange = { newText ->
                        tasks = tasks.mapIndexed { i, t ->
                            if (i == index) t.copy(taskText = newText) else t
                        }.toMutableList()
                    }
                )
            }



            // Date picker dialog

            if (showDatePicker) {
                val datePickerState = rememberDatePickerState()
                DatePickerDialog(
                    onDismissRequest = { showDatePicker = false },
                    confirmButton = {
                        TextButton(onClick = {
                            selectedDate = datePickerState.selectedDateMillis?.let {
                                Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault()).toLocalDate()
                            }
                            showDatePicker = false
                        }) {
                            Text("OK")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showDatePicker = false }) {
                            Text("Cancel")
                        }
                    }
                ) {
                    DatePicker(state = datePickerState)
                }
            }

            //Display picked date
            selectedDate?.let { date ->

                DisplayPickedField(
                    color = CustomColors.white,
                    onClickedTextButton = {
                        showDatePicker = true
                    },
                    option = date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")),
                    onDeleteIconClicked = {
                        selectedDate = null
                    }
                )
            }


            //Display Priority picker

            if (showPriorityPicker) {
                SimplePickerDialog(
                    title = "Select Priority",
                    options = listOf("Low", "Medium", "High"),
                    onSelect = {
                        selectedPriority = it
                        showPriorityPicker = false
                    },
                    onDismiss = {showPriorityPicker = false}
                )
            }

            //Display picked priority

            selectedPriority?.let { priority ->
                val color = when (priority) {
                    "Low" -> Color.Green
                    "Medium" -> Color.Yellow
                    "High" -> Color.Red
                    else -> Color.Gray
                }

                DisplayPickedField(
                    color = color,
                    onClickedTextButton = {
                        showPriorityPicker = true
                    },
                    option = priority,
                    onDeleteIconClicked = {
                        selectedPriority = null
                    }
                )
            }

            //Display Repeat picker dialog

            if (showRepeatPicker) {
                SimplePickerDialog(
                    title = "Select Repeat",
                    options = listOf("Every day", "Every week", "Every month"),
                    onSelect = { selectedRepeat = it
                                 showRepeatPicker = false
                               },
                    onDismiss = {showRepeatPicker = false}
                )
            }



            //Display picked repeat

            selectedRepeat?.let { repeat ->

                DisplayPickedField(
                    color = CustomColors.white,
                    onClickedTextButton = {
                        showRepeatPicker = true
                    },
                    option = repeat,
                    onDeleteIconClicked = {
                        selectedRepeat = null
                    }
                )
            }


            //Display color picker

            if (showColorPicker) {
                AlertDialog(
                    onDismissRequest = { showColorPicker = false },
                    title = { Text("Select Color") },
                    text = {
                        Column {
                            listOf("Blue", "Red", "Green","Yellow").forEach { color ->
                                val pickedColor = when (color) {
                                    "Blue" -> Color.Blue
                                    "Green" -> Color.Green
                                    "Red" -> Color.Red
                                    "Yellow" -> Color.Yellow
                                    else -> Color.Gray
                                }

                                TextButton(
                                    onClick = {
                                        selectedColor = color
                                        showColorPicker = false
                                    },
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(24.dp)
                                            .clip(CircleShape)
                                            .background(pickedColor)


                                    )
                                }
                            }
                        }
                    },
                    confirmButton = {},
                    dismissButton = {
                        TextButton(onClick = { showColorPicker = false }) {
                            Text("Cancel")
                        }
                    }
                )
            }

            //Display selected color

            selectedColor?.let { color ->
                val pickedColor = when (color) {
                    "Blue" -> Color.Blue
                    "Green" -> Color.Green
                    "Red" -> Color.Red
                    "Yellow" -> Color.Yellow
                    else -> Color.Gray
                }

                Row(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp)
                        .background(CustomColors.chrome300, shape = RoundedCornerShape(16.dp))
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(pickedColor)
                            .clickable {
                                showColorPicker = true
                            }

                    )
                    IconButton(onClick = {selectedColor = null}) {
                        Icon(Icons.Default.Close, contentDescription = "Delete priority")
                    }

                }
            }
        }
    }



}

@Composable
fun IconOnlyButton(
    @DrawableRes iconRes: Int,
    onClick: () -> Unit,
    shape: RoundedCornerShape = RoundedCornerShape(30.dp)
) {
    OutlinedButton(
        onClick = onClick,
        shape = shape,
        contentPadding = PaddingValues(CustomDimensions.sizeXXXS),
        modifier = Modifier.size(CustomDimensions.sizeXL) // You can tweak this size
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier.size(CustomDimensions.sizeM)
        )
    }
}
@Composable
fun BottomPanel(
    onAddTask: () -> Unit,
    onPickDate: () -> Unit,
    onPickColor: () -> Unit,
    onSetPriority: () -> Unit,
    onSetRepeat: () -> Unit
) {
    Surface(
        color = Color.LightGray,

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .height(100.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            IconButton(onClick = onAddTask) {
                Icon(painter = painterResource(id = R.drawable.checklist), contentDescription = "Add Task")

            }
            IconButton(onClick = onPickDate) {
                Icon(Icons.Default.DateRange, contentDescription = "Add Date")
            }
            IconButton(onClick = onPickColor) {
                Icon(painter = painterResource(id = R.drawable.palette), contentDescription = "Pick Color")
            }
            IconButton(onClick = onSetPriority) {
                Icon(painter = painterResource(id = R.drawable.prioritize), contentDescription = "Set Priority")
            }
            IconButton(onClick = onSetRepeat) {
                Icon(painter = painterResource(id = R.drawable.img), contentDescription = "Set Repeat")
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopActionBar(
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    returnToPreviousStepClick: () -> Unit,
    returnToNextStepClick: () -> Unit
) {
    TopAppBar(
        title = { Text("") },
        navigationIcon = {
            TextButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                    contentDescription = "Back"
                )
                Text("Home", color = CustomColors.black)
            }
        },
        actions = {
            IconOnlyButton(R.drawable.baseline_subdirectory_arrow_left_24, onClick = returnToPreviousStepClick)
            Spacer(modifier = Modifier.width(CustomDimensions.sizeXS))
            IconOnlyButton(R.drawable.baseline_subdirectory_arrow_right_24, onClick = returnToNextStepClick)
            Spacer(modifier = Modifier.width(CustomDimensions.sizeXS))
            IconOnlyButton(R.drawable.baseline_done_24, onClick = onSaveClick)
        }
    )
}

@Composable
fun TaskInputField(
    task: Task,
    onCheckedChange: (Boolean) -> Unit,
    onTextChange: (String) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        CustomCheckbox(
            checked = task.isChecked,
            onCheckedChange = onCheckedChange
        )
        OutlinedTextField(
            value = task.taskText,
            onValueChange = onTextChange,
            label = { Text("Task") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun SimplePickerDialog(
    title: String,
    options: List<String>,
    onSelect: (String) -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(title) },
        text = {
            Column {
                options.forEach { option ->
                    TextButton(onClick = { onSelect(option) }) {
                        Text(option)
                    }
                }
            }
        },
        confirmButton = {},
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}

@Composable
fun DisplayPickedField(
    color: Color,
    onClickedTextButton: () -> Unit,
    option: String,
    onDeleteIconClicked: () -> Unit
){
    Row(
        modifier = Modifier
            .padding(start = 16.dp, top = 8.dp)
            .background(color.copy(alpha = 0.2f), shape = RoundedCornerShape(16.dp))
            .border(border = BorderStroke(2.dp,black), shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextButton(
            onClick = onClickedTextButton,
        ){
            Text(
                text = option,
                style = MaterialTheme.typography.bodyMedium
            )

        }
        IconButton(onClick = onDeleteIconClicked) {
            Icon(Icons.Default.Close, contentDescription = "Delete")
        }

    }
}

// Task data class
data class Task(
    val isChecked: Boolean,
    val taskText: String
)


data class AddEditItem(
    val title: String = "",
    val description: String = "",
    val tasks: List<Task> = mutableListOf<Task>(),
    val selectedDate: LocalDate?= null,
    val selectedPriority: String?= null,
    val repeatType: String? = null,
    val selectedColor: String? = null,
    val createdAt: Long?= null
)
