package com.example.alica_app.ui.profile.Experiences

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alica_app.ui.profile.randomResponse
import kotlinx.coroutines.launch

@Composable
fun ProfileExperiences(viewModelExperience: ViewModelExperience,onAddClicked: () -> Unit){

    val coRoutineScope = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        coRoutineScope.launch {

            val result = viewModelExperience.findExperiences()

            if (result) {
                isLoading = false
            }
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Row(modifier=Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceEvenly) {

            Text(modifier = Modifier.padding(10.dp),text = "Experiences :",fontSize = 18.sp,
                    style = TextStyle(textDecoration = TextDecoration.Underline))

            IconButton(onClick = onAddClicked, colors = IconButtonColors(containerColor = Color.Green, contentColor = Color.Black, disabledContainerColor = Color.Transparent, disabledContentColor = Color.Transparent)) {
                Icon(Icons.Outlined.Create, contentDescription = "Add Experience")
            }

        }

        if(isLoading){
            CircularProgressIndicator()
        }
        else{

            LazyColumn (modifier = Modifier
                .fillMaxWidth()
                .padding(Dp(10f))){

                viewModelExperience.experiences().forEach(){
                    item {
                        ExperienceComponent(year = it.startDate, experience = it.title, it.current)
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun PreviewProfileExperiences(onAddClicked: () -> Unit = {}){
    ProfileExperiences(viewModelExperience = ViewModelExperience(authentication = randomResponse),onAddClicked = onAddClicked)
}


@Composable
fun ExperienceComponent(year: String, experience: String,current: Boolean){

    Row(modifier = Modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly) {
        Column {
            Text(text = year)
            Text(text = experience)
        }
        if(current){
            Column {
                CurrentJob()
            }
        }
        Column {
            DeleteComponent()
        }
    }
    HorizontalDivider(Modifier.padding(5.dp))

}

@Preview
@Composable
fun CurrentJob() {

    SuggestionChip(modifier = Modifier.width(100.dp),onClick = { /*TODO*/ }, label = {
        Text(text = "Emploi Actuel",fontSize = 10.sp, fontWeight = FontWeight.Bold,
            style = TextStyle(color = Color.Black)
        )
    }, border =  BorderStroke(
            width = 1.dp,
            color = Color.Cyan
        ),
        shape = RoundedCornerShape(60)
    )

}

@Preview
@Composable
fun DeleteComponent(){
    IconButton(onClick = { /*TODO*/ }) {
        Icon(Icons.Outlined.Delete, contentDescription = "delete")
    }
}


