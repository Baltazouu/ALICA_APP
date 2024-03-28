
package com.example.alica_app.ui.utils

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alica_app.ui.utils.DateDefaults.DATE_LENGTH
import com.example.alica_app.ui.utils.DateDefaults.DATE_MASK
import java.util.Calendar
import kotlin.math.absoluteValue

class MaskVisualTransformation(private val mask: String) : VisualTransformation {

    private val specialSymbolsIndices = mask.indices.filter { mask[it] != '#' }

    override fun filter(text: AnnotatedString): TransformedText {
        var out = ""
        var maskIndex = 0
        text.forEach { char ->
            while (specialSymbolsIndices.contains(maskIndex)) {
                out += mask[maskIndex]
                maskIndex++
            }
            out += char
            maskIndex++
        }
        return TransformedText(AnnotatedString(out),offsetTranslator())
    }

    private fun offsetTranslator() = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            val offsetValue = offset.absoluteValue
            if (offsetValue == 0) return 0
            var numberOfHashtags = 0
            val masked = mask.takeWhile {
                if (it == '#') numberOfHashtags++
                numberOfHashtags < offsetValue
            }
            return masked.length + 1
        }

        override fun transformedToOriginal(offset: Int): Int {
            return mask.take(offset.absoluteValue).count { it == '#' }
        }
    }
}
@Preview

@Composable
fun DateTextField(
    label: String = "Date Début",
    onDateChanged: (String) -> Unit = {}
) {
    var date by remember { mutableStateOf("") }
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
                onDateChanged.invoke(date)
                if (newText.length <= DATE_LENGTH) {
                    date = newText
                }
                errorMessage = if(isValidDate) {
                    onDateChanged.invoke(date)
                    ""
                }
                else {
                    message
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

 fun validateDate(dateString: String): Pair<Boolean, String> {
    if (dateString.length != 8) return false to "Format de date invalide"

    val day = dateString.substring(0, 2).toIntOrNull() ?: return false to "Jour invalide"
    val month = dateString.substring(2, 4).toIntOrNull() ?: return false to "Mois invalide"
    val year = dateString.substring(4, 8).toIntOrNull() ?: return false to "Année invalide"

    val currentDate = Calendar.getInstance()
    val currentYear = currentDate.get(Calendar.YEAR)
    val currentMonth = currentDate.get(Calendar.MONTH) + 1 // Month starts from 0
    val currentDay = currentDate.get(Calendar.DAY_OF_MONTH)

    if (day <= 0 || month <= 0 || year <= 0) return false to "Valeur négative invalide"

    if (year > currentYear || year < 1950) return false to "Année hors plage (1950 - $currentYear)"

    if (month > 12) return false to "Mois invalide"

    val daysInMonth = when (month) {
        1, 3, 5, 7, 8, 10, 12 -> 31
        4, 6, 9, 11 -> 30
        2 -> if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) 29 else 28
        else -> return false to "Mois invalide"
    }

    if (day > daysInMonth) return false to "Jour invalide pour ce mois"

    if (month == currentMonth && day > currentDay) return false to "Jour futur invalide"

    return true to ""
}




object DateDefaults {
    const val DATE_MASK = "##/##/####"
    const val DATE_LENGTH = 8 // Equals to "##/##/####".count { it == '#' }
}
