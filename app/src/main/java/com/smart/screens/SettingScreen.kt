package com.smart.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smart.R

@Composable
fun SettingScreen(
    serverIp: MutableState<String>,
    serverPort: MutableState<String>
) {
    val backgroundColor: Color = colorResource(id = R.color.backgroundColor)
    val backgroundSelectElementColor: Color = colorResource(
        id = R.color.backgroundSelectElementColor
    )
    val textColor: Color = colorResource(id = R.color.textColor)
    val selectElementTextColor: Color = colorResource(id = R.color.selectElementTextColor)
    val errorTextColor: Color = colorResource(id = R.color.errorTextColor)

    val serverIpField = remember { mutableStateOf("") }
    val serverPortField = remember { mutableStateOf("") }
    val formError = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .background(backgroundColor)
            .wrapContentSize(Alignment.Center)

    ) {
        Text(
            text = createCurrentAddressString(serverIp.value, serverPort.value),
            color = textColor,
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )
        OutlinedTextField(
            serverIpField.value,
            {serverIpField.value = it},
            textStyle = TextStyle(fontSize =  30.sp),
            label = {
                Text(
                    text = "IP адрес сервера",
                    color = textColor
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            isError = serverIpField.value.isNotEmpty() &&
                    !isValidServerIp(serverIpField.value)
        )
        OutlinedTextField(
            serverPortField.value,
            {serverPortField.value = it},
            textStyle = TextStyle(fontSize =  30.sp),
            label = {
                Text(
                    text = "Порт сервера",
                    color = textColor
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            isError = serverPortField.value.isNotEmpty() &&
                    !isValidServerPort(serverPortField.value),
        )
        Button(
            onClick = {
                if (isValidForm(serverIpField.value, serverPortField.value)) {
                    serverIp.value = serverIpField.value
                    serverPort.value = serverPortField.value
                    formError.value = ""
                } else {
                    formError.value = "Ошибка в заполненных значениях"
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            colors = ButtonColors(
                contentColor = selectElementTextColor,
                containerColor = backgroundSelectElementColor,
                disabledContentColor = selectElementTextColor,
                disabledContainerColor = backgroundSelectElementColor
            )
        ) {
            Text(
                text = "Установить адрес сервера",
                color = selectElementTextColor,
                fontSize = 16.sp
            )
        }
        if (formError.value.isNotEmpty()) {
            Text(
                text = formError.value,
                color = errorTextColor,
                fontSize = 16.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingScreenPreview() {
    val serverIp = rememberSaveable { mutableStateOf("") }
    val serverPort = rememberSaveable { mutableStateOf("") }
    SettingScreen(
        serverIp = serverIp,
        serverPort = serverPort
    )
}

private fun isValidServerIp(text: String): Boolean {
    return text.matches(
        Regex("^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)(\\.(?!\$)|\$)){4}\$")
    )
}

private fun isValidServerPort(text: String): Boolean {
    return text.matches(
        Regex(
            "^([1-9][0-9]{0,3}|[1-5][0-9]{4}|6[0-4][0-9]{3}|65[0-4][0-9]{2}|655[0-2][0-9]|6553[0-5])\$"
        )
    )
}

private fun isValidForm(serverIpText: String, serverPortText: String): Boolean {
    return serverIpText.isNotEmpty() && serverPortText.isNotEmpty() &&
            isValidServerIp(serverIpText) && isValidServerPort(serverPortText)
}

private fun createCurrentAddressString(serverIp: String, serverPort: String) : String {
    val firstPart = "Текущий адрес сервера"
    if (!isValidForm(serverIp, serverPort)) {
        return String.format("%s: не задан", firstPart)
    }
    return String.format("%s: %s:%s", firstPart, serverIp, serverPort)
}
