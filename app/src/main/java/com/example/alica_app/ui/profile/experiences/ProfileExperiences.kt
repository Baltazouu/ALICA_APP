package com.example.alica_app.ui.profile.experiences

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
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.compose.rememberNavController
import com.example.alica_app.NavigationItem
import com.example.alica_app.data.models.Experience
import com.example.alica_app.ui.profile.randomResponse
import kotlinx.coroutines.launch

@Composable
fun ProfileExperiences(viewModelExperience: ViewModelExperience,navController: NavController){

    val coRoutineScope = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(true) }

    val experiences by viewModelExperience.experiences.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        coRoutineScope.launch {

            viewModelExperience.findExperiences()
            isLoading = false
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Row(modifier=Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceEvenly) {

            Text(modifier = Modifier.padding(10.dp),text = "Experiences :",fontSize = 18.sp,
                    style = TextStyle(textDecoration = TextDecoration.Underline))

            IconButton(onClick = { navController.navigate(NavigationItem.AddExperience.route) }, colors = IconButtonColors(containerColor = Color.Green, contentColor = Color.Black, disabledContainerColor = Color.Transparent, disabledContentColor = Color.Transparent)) {
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

                experiences.forEach{
                    item {
                        ExperienceComponent(experience = it, onDeleteClick = {
                            if(it.id != null){viewModelExperience.deleteExperience(it.id)}})
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun PreviewProfileExperiences(onAddClicked: () -> Unit = {}){
    ProfileExperiences(viewModelExperience = ViewModelExperience(authentication = randomResponse),
        rememberNavController())
}


@Composable
fun ExperienceComponent(experience: Experience,onDeleteClick: ()-> Unit){

    Row(modifier = Modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
        Column {
            Text(text = experience.startDate + " " + experience.endDate)
            Text(text = experience.title)
        }
        if(experience.isCurrent != null && experience.isCurrent){
            Column {
                CurrentJob()
            }
        }
        Column {
            DeleteComponent(onDeleteClick= onDeleteClick)
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
fun DeleteComponent(onDeleteClick: () -> Unit = {}){
    IconButton(onClick =  onDeleteClick ) {
        Icon(Icons.Outlined.Delete, contentDescription = "delete")
    }
}


