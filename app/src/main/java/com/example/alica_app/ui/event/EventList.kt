package com.example.alica_app.ui.event

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import java.util.EventObject

@Composable
fun EventList() {
    val title : String = "Evenements"
    Column (horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(10.dp)) {
        Text(text = title)


    }
}

@Composable
fun EventObject() {
    Column (horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(10.dp)) {
        Row {
            EventImage()
            Spacer(modifier = Modifier.padding(20.dp))
            EventCaracteristique()
        }
        Spacer(modifier = Modifier.padding(5.dp))
        Row {
            Text("description")
            Spacer(modifier = Modifier.padding(10.dp))
        }
        Text("Clermont-Ferrand")
    }
}

@Composable
fun button(name: String, color : Color){
    
}

@Composable
fun EventImage() {
    
}

@Composable
fun EventCaracteristique() {
    Column (horizontalAlignment = Alignment.CenterHorizontally) {
        
    }
}