package com.smart.screens.signaling

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.smart.client.postDataToServer
import kotlinx.serialization.json.Json

@Composable
fun Signaling(
    serverIp: MutableState<String>,
    serverPort: MutableState<String>,
    dataSignalingString: MutableState<String>
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
        if (dataSignalingString.value.isEmpty()) {
            Text(
                text = "Нет соединения с сервером",
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
            )
            return
        }
        val dataSignaling = Json.decodeFromString<SignalingData>(dataSignalingString.value)
        var signalingIsWork =
            dataSignaling.signalingWorkingState == SignalingWorkingState.WORK.state ||
                    dataSignaling.signalingWorkingState == SignalingWorkingState.TURNS_ON.state
        Text(
            text = createStateSignalingString(dataSignaling.signalingWorkingState),
            fontWeight = FontWeight.Bold,
            color = textColor,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
        Switch(
            checked = signalingIsWork,
            onCheckedChange = {
                signalingIsWork = it
                postDataToServer(
                    serverIp = serverIp.value,
                    serverPort = serverPort.value,
                    bodyString = "",
                    endpointName = "signaling-data"
                )
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colorScheme.primary,
                checkedTrackColor = MaterialTheme.colorScheme.primaryContainer,
                uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
                uncheckedTrackColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            modifier = Modifier.padding(bottom = 24.dp),
        )
        if (dataSignaling.signalingWorkingState.equals(SignalingWorkingState.WORK.state)) {
            if (dataSignaling.signalingState) {
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
    val serverIp = rememberSaveable { mutableStateOf("") }
    val serverPort = rememberSaveable { mutableStateOf("") }
    val dataSignalingString = remember {
        mutableStateOf("{\"signalingWorkingState\":\"work\",\"signalingState\":true}")
    }
    Signaling(
        serverIp = serverIp,
        serverPort = serverPort,
        dataSignalingString = dataSignalingString
    )
}

private fun createStateSignalingString(signalingWorkingStateString: String) : String {
    val firstPart = "Состояние сигнализации"
    for (signalingWorkingStateEnum in SignalingWorkingState.entries) {
        if (signalingWorkingStateEnum.state.equals(signalingWorkingStateString)) {
            return String.format("%s: %s", firstPart, signalingWorkingStateEnum.description)
        }
    }
    return String.format("%s: Серверная ошибка", firstPart)
}
