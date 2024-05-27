package com.smart.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smart.R

@Composable
fun Signaling(
    signalingIsWork: MutableState<Boolean>,
    signalingState: MutableState<Boolean>
) {
    val backgroundColor: Color = colorResource(id = R.color.backgroundColor)
    val textColor: Color = colorResource(id = R.color.textColor)
    val errorTextColor: Color = colorResource(id = R.color.errorTextColor)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = createStateSignalingString(signalingIsWork.value),
            fontWeight = FontWeight.Bold,
            color = textColor,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
        Switch(
            checked = signalingIsWork.value,
            onCheckedChange = {
                signalingIsWork.value = it
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colorScheme.primary,
                checkedTrackColor = MaterialTheme.colorScheme.primaryContainer,
                uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
                uncheckedTrackColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            modifier = Modifier.padding(bottom = 24.dp),
        )
        if (signalingIsWork.value) {
            if (signalingState.value) {
                Text(
                    text = "Все спокойно",
                    fontWeight = FontWeight.Bold,
                    color = textColor,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp
                )
            } else {
                Text(
                    text = "Тревога!!!",
                    fontWeight = FontWeight.Bold,
                    color = errorTextColor,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignalingPreview() {
    val signalingIsWork = remember { mutableStateOf(true) }
    val signalingState = remember { mutableStateOf(true) }
    Signaling(
        signalingIsWork = signalingIsWork,
        signalingState = signalingState
    )
}

private fun createStateSignalingString(signalingIsWork: Boolean) : String {
    val firstPart = "Состояние сигнализации"
    if (!signalingIsWork) {
        return String.format("%s: Выключена", firstPart)
    }
    return String.format("%s: Работает", firstPart)
}
