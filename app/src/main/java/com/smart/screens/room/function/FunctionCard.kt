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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.smart.R
import com.smart.navigation.NavigationItem
import com.smart.navigation.changePage

@Composable
fun FunctionCard(
    navController: NavController,
    functionItem: NavigationItem,
    backgroundElementColor: Color,
    textColor: Color
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = backgroundElementColor,
        ),
        modifier = Modifier.clickable(
            onClick = {
                changePage(
                    navController = navController,
                    pageRoute = functionItem.route,
                )
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
    val navController = rememberNavController()
    val functionItem = NavigationItem.Temperature
    val backgroundElementColor: Color = colorResource(id = R.color.backgroundElementColor)
    val textColor: Color = colorResource(id = R.color.textColor)
    FunctionCard(
        navController = navController,
        functionItem = functionItem,
        backgroundElementColor = backgroundElementColor,
        textColor = textColor
    )
}
