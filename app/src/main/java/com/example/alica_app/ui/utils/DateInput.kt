@file:OptIn(ExperimentalMaterial3Api::class)

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun DateInput(
    modifier: Modifier = Modifier,
    label:String,
    onDateChanged: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    val context = LocalContext.current


    OutlinedTextField(
        modifier = modifier,
        value = text,
        onValueChange = {
            text = it
            isError = !isValidDate(it)
            onDateChanged.invoke(text)
        },
        label = { Text("$label(jj/mm/aaaa)") },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                if (isValidDate(text)) {
                    // Format the input to a standardized date format
                    val formattedDate = formatDate(text)
                    text = formattedDate
                    onDateChanged.invoke(formattedDate)
                } else {
                    isError = true
                }
            }
        ),
        isError = isError
    )
}

fun isValidDate(input: String): Boolean {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    dateFormat.isLenient = false
    return try {
        dateFormat.parse(input)
        true
    } catch (e: Exception) {
        false
    }
}

fun formatDate(input: String): String {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    dateFormat.isLenient = false
    val date = dateFormat.parse(input)
    val calendar = Calendar.getInstance()
    calendar.time = date!!
    return dateFormat.format(calendar.time)
}

@Preview
@Composable
fun PreviewDateInput() {
    DateInput(Modifier.fillMaxWidth(),"Date Début") {}
}

@Preview
@Composable
fun DatePickerTest(){

    DatePicker(state = rememberDatePickerState(),
        dateFormatter = remember {DatePickerDefaults.dateFormatter() }, title = {
        DatePickerDefaults.DatePickerHeadline(
        selectedDateMillis = 0,
        displayMode = DisplayMode.Picker,
        dateFormatter = remember {DatePickerDefaults.dateFormatter() },
        )
    })




}

