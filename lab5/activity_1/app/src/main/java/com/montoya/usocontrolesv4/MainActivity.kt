package com.montoya.usocontrolesv4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.montoya.usocontrolesv4.ui.theme.UsoControlesv4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            actividad1()
        }
    }
}

@Composable
fun actividad1() {
    var usuario by remember { mutableStateOf("") }
    var clave by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "MI PRIMERA APLICACIÓN",
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF00897B))
                .padding(12.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(text = "USUARIO:")
        OutlinedTextField(
            value = usuario,
            onValueChange = { usuario = it },
            label = { Text("Ingrese su usuario") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "CLAVE:")
        OutlinedTextField(
            value = clave,
            onValueChange = { clave = it },
            label = { Text("Ingrese su clave") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                resultado = if (usuario == "admin" && clave == "1234") {
                    "Acceso concedido"
                } else {
                    "Acceso denegado"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("OK")
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedButton(
            onClick = { resultado = "Sin acceso" },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("SIN ACCESO")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "RESULTADO",
            fontSize = 18.sp,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF311B92))
                .padding(12.dp),
            textAlign = TextAlign.Center
        )

        Text(
            text = resultado,
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UsoControlesv4Theme {
        actividad1()
    }
}