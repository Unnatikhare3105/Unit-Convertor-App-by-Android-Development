package com.example.project5_my_ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project5_my_ui.ui.theme.Project5_MY_UITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Project5_MY_UITheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(modifier = Modifier.padding(innerPadding))

                    convertor()
                }
            }
        }
    }
}

@Composable
fun convertor() {
    var iexpand by remember { mutableStateOf(false) }
    var oexpand by remember { mutableStateOf(false) }
    var inputunit by remember { mutableStateOf("select unit") }
    var outputunit by remember { mutableStateOf("select unit") }
    var inputvalue by remember { mutableStateOf("") }
    var outputvalue by remember { mutableStateOf(0.0) }

    fun conversion() {
        var input = inputvalue.toDoubleOrNull() ?: 0.0
        if (inputunit == "milimeter" && outputunit == "milimeter") {
            outputvalue = input
        } else if (inputunit == "milimeter" && outputunit == "centimeter") {
            outputvalue = input / 10
        } else if (inputunit == "milimeter" && outputunit == "meter") {
            outputvalue = input / 1000
        }
        else if(inputunit == "centimeter" && outputunit == "milimeter"){
            outputvalue = input * 10
        }
        else if(inputunit == "centimeter" && outputunit == "centimeter"){
            outputvalue = input
        }
        else if(inputunit == "centimeter" && outputunit == "meter"){
            outputvalue = input / 100
        }
        else if(inputunit == "meter" && outputunit == "milimeter"){
            outputvalue = input * 1000
        }
        else if(inputunit == "meter" && outputunit == "centimeter"){
            outputvalue = input * 100
        }
        else if(inputunit == "meter" && outputunit == "meter"){
            outputvalue = input
        }
        else{
            outputvalue = 0.0
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(
            text = "Convertor",
            style = TextStyle(
                fontSize = 30.sp,
                fontFamily = FontFamily.Monospace
            )
        )
        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = inputvalue,
            onValueChange = { inputvalue = it},
            label = { Text("enter Value") }
        )

        Spacer(modifier = Modifier.height(15.dp))

        Row {
            Box {
                Button(onClick = { iexpand = true }) {
                    Text(inputunit)
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
                }
                DropdownMenu(expanded = iexpand, onDismissRequest = { iexpand = false })
                {
                    DropdownMenuItem(
                        text = { Text("milimeter") },
                        onClick = {
                            iexpand = false
                            inputunit = "milimeter"
                            conversion()
                        }
                    )
                    DropdownMenuItem(text = { Text("centimeter") },
                        onClick = {
                            iexpand = false
                            inputunit = "centimeter"
                            conversion()
                        })
                    DropdownMenuItem(text = { Text("meter") },
                        onClick = {
                            iexpand = false
                            inputunit = "meter"
                            conversion()
                        })
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            Box {
                Button(onClick = { oexpand = true }) {
                    Text(outputunit)
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
                }
                DropdownMenu(expanded = oexpand, onDismissRequest = { oexpand = false })
                {
                    DropdownMenuItem(text = { Text("milimeter") },
                        onClick = {
                            oexpand = false
                            outputunit = "milimeter"
                            conversion()
                        }
                    )
                    DropdownMenuItem(text = { Text("centimeter") },
                        onClick = {
                            oexpand = false
                            outputunit = "centimeter"
                            conversion()
                        })
                    DropdownMenuItem(text = { Text("meter") },
                        onClick = {
                            oexpand = false
                            outputunit = "meter"
                            conversion()
                        })
                }
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        Text(text = " result: $outputvalue$outputunit")
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Project5_MY_UITheme {
        convertor()
    }
}