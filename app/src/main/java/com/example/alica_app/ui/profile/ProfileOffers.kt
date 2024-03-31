package com.example.alica_app.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import com.example.alica_app.ui.offers.offerDetail.OfferDetail

@Composable
fun Offers(){
    Column(modifier = Modifier
        .clip(shape = RoundedCornerShape(Dp(15f)))
        .background(color = Color.LightGray)
        .padding(Dp(10f))
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Mes offres :",
            fontSize = 18.sp,
            style = TextStyle(textDecoration = TextDecoration.Underline)
        )
        LazyColumn {
            item {
                OfferDetail()
            }
            item {
                OfferDetail()
            }
        }
    }
}
