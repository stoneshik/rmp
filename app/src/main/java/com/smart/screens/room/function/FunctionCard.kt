package com.smart.screens.room.function

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smart.R

@Composable
fun FunctionCard(
    route: MutableState<FunctionRoute>,
    functionItem: FunctionItem,
    backgroundElementColor: Color,
    textColor: Color
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = backgroundElementColor,
        ),
        modifier = Modifier.clickable(
            onClick = {
                route.value = functionItem.route
            }
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(8.dp)
                .width(100.dp)
                .height(100.dp)
                .clip(RoundedCornerShape(4.dp)),
        ) {
            Icon(
                painter = painterResource(functionItem.icon),
                contentDescription = null,
                modifier = Modifier.size(60.dp),
                tint = textColor
            )
            Text(
                text = functionItem.title,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = textColor
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FunctionCardPreview() {
    val route = remember{
        mutableStateOf(FunctionItem.Temperature.route)
    }
    val functionItem = FunctionItem.Temperature
    val backgroundElementColor: Color = colorResource(id = R.color.backgroundElementColor)
    val textColor: Color = colorResource(id = R.color.textColor)
    FunctionCard(
        route = route,
        functionItem = functionItem,
        backgroundElementColor = backgroundElementColor,
        textColor = textColor
    )
}
