package org.example.multicalculator

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CalcView()
        }
    }
    @Composable
    fun CalcView() {
        val displayText = remember { mutableStateOf("0") }
        Column {
            CalcDisplay(displayText)
            CalcRow(displayText, startNum = 1, numButtons = 3)
            CalcRow(displayText, startNum = 4, numButtons = 3)
            CalcRow(displayText, startNum = 7, numButtons = 3)
            Row {
                CalcNumericButton(0, displayText)
                CalcEqualsButton(display = displayText)
            }

            Column{
                CalcOperationButton(operation = "+", display = displayText)
                CalcOperationButton(operation = "-", display = displayText)
                CalcOperationButton(operation = "*", display = displayText)
                CalcOperationButton(operation = "/", display = displayText)
            }
        }
    }

    @Composable
    fun CalcRow(display: MutableState<String>, startNum: Int, numButtons: Int) {
        val endNum = startNum + numButtons
        Row(modifier = Modifier.padding(0.dp)) {
            for (i in startNum until endNum) {
                CalcNumericButton(i, display)
            }
        }
    }

    @Composable
    fun CalcDisplay(display: MutableState<String>) {
        Text(
            text = display.value,
            modifier = Modifier
                .height(50.dp)
                .padding(5.dp)
                .fillMaxWidth()
        )
    }

    @Composable
    fun CalcNumericButton(number: Int, display: MutableState<String>) {
        Button(
            onClick = { display.value += number.toString() },
            modifier = Modifier.padding(4.dp)
        ) {
            Text(text = number.toString())
        }
    }

    @Composable
    fun CalcOperationButton(operation: String, display: MutableState<String>) {
        Button(
            onClick = { /* Handle operation click */ },
            modifier = Modifier.padding(4.dp)
        ) {
            Text(text = operation)
        }
    }

    @Composable
    fun CalcEqualsButton(display: MutableState<String>) {
        Button(
            onClick = { display.value = "0" },
            modifier = Modifier.padding(4.dp)
        ) {
            Text(text = "=")
        }
    }


}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}