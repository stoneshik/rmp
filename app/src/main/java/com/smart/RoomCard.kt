package com.smart

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource

@Composable
fun RoomCard() {
    val backgroundElementColor: Color = colorResource(id = R.color.backgroundElementColor)
    Card(
        colors = CardDefaults.cardColors(
            containerColor = backgroundElementColor,
        ),
        modifier = Modifier
                .padding(4.dp)
                .width(150.dp)
                .height(200.dp)
                .clip(RoundedCornerShape(4.dp))
    ) {
        Text(
            text = "Filled",
            modifier = Modifier
                .padding(16.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RoomCardPreview() {
    RoomCard()
}
