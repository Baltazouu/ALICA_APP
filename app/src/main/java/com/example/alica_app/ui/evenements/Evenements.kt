package com.example.alica_app.ui.evenements

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.alica_app.R
import com.example.alica_app.models.Event
import java.util.Date

@Composable
fun Evenements() {

    val allEvent : List<Event> = listOf(
        Event("Laser Game","Clermont-Ferrand", "24/09/2015",25,12),
        Event("Laser Game","Paris", "24/09/2015",25,12),
        Event("Patinage","Clermont", "24/10/2015",25,12),
        Event("Laser Game","Orléans", "24/09/2015",32,12)
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dp(15f)), horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = stringResource(R.string.event),
            fontSize = 35.sp,
            textAlign = TextAlign.Center
        )
        for (e in allEvent){
            OneEvent(e)
        }
    }
}

@Composable
fun OneEvent(e : Event){
    Column {
        Row {
            AsyncImage(
                model = "https://www.mairie-bailly.fr/wp-content/uploads/2023/05/5d4d2f0c0ff89451695087.jpg",
                contentDescription = "Limage",
                modifier = Modifier
                    .height(150.dp)
                    .width(160.dp),
                )
            Spacer(modifier = Modifier.width(20.dp))
            Column {
                Field(e.Name)
                Field(e.Site)
                Field(e.Date)
                Field(e.Subscriber.toString())
                Field(e.Limit.toString())
            }
        }
        Row{
            Button(onClick = { /*TODO*/ }) {
                Text(text = stringResource(R.string.details))
            }
            Spacer(modifier = Modifier.width(25.dp))
            Button(onClick = { /*TODO*/ }) {
                Text(text = stringResource(R.string.inscription))
            }
        }
    }
}

@Composable
fun Field(resources : String){
    Text(
        text = resources,
        fontSize = 18.sp,
        textAlign = TextAlign.Center
    )
}
