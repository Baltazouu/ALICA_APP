@file:OptIn(ExperimentalMaterial3Api::class)

import androidx.compose.foundation.background
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
import androidx.compose.material3.TextField
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
import androidx.compose.ui.unit.sp
import com.example.alica_app.ui.utils.DateDefaults.DATE_MASK
import com.example.alica_app.ui.utils.MaskVisualTransformation
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun DateTextField(
    label: String = "Date Début",
    onDateChanged: (String) -> Unit = {}
) {
    var date by remember { mutableStateOf("") }
    var previousDate by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column {
        Text(
            text = label,
            color = Color.Gray,
            fontSize = 12.sp,
            modifier = Modifier.padding(5.dp)
        )

        TextField(
            modifier = Modifier.background(Color.Black),
            value = date,
            onValueChange = { newText ->
                val (isValidDate, message) = validateDate(newText)
                if (isValidDate) {
                    date = newText
                    onDateChanged.invoke(date)
                    errorMessage = ""
                } else {
                    errorMessage = message
                    date = previousDate
                }
            },
            visualTransformation = MaskVisualTransformation(DATE_MASK),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        if (errorMessage.isNotBlank()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 5.dp)
            )
        }
    }
}

private fun validateDate(dateString: String): Pair<Boolean, String> {
    val currentDate = Calendar.getInstance()
    val currentYear = currentDate.get(Calendar.YEAR)
    val currentMonth = currentDate.get(Calendar.MONTH) + 1 // Month starts from 0
    val currentDay = currentDate.get(Calendar.DAY_OF_MONTH)

    val dateParts = dateString.split("/")
    if (dateParts.size != 3) return false to "Format de date invalide"

    val (dayStr, monthStr, yearStr) = dateParts
    val day = dayStr.toIntOrNull() ?: return false to "Jour invalide"
    val month = monthStr.toIntOrNull() ?: return false to "Mois invalide"
    val year = yearStr.toIntOrNull() ?: return false to "Année invalide"

    if (day <= 0 || month <= 0 || year <= 0) return false to "Valeur négative invalide"

    if (year > currentYear || year < 1950) return false to "Année hors plage (1950 - $currentYear)"

    if (month > 12 || day > 31) return false to "Mois ou jour invalide"

    if (month == 2 && day > 29) return false to "Février ne peut pas avoir plus de 29 jours"

    val months30Days = setOf(4, 6, 9, 11)
    if (day > 30 && month in months30Days) return false to "Mois invalide (30 jours maximum)"

    if (month == currentMonth && day > currentDay) return false to "Jour futur invalide"

    return true to ""
}


