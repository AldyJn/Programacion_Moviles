package com.montoya.cerveceria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.montoya.cerveceria.ui.theme.CerveceriaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Cervezas()
        }
    }
}


@Composable
fun Cervezas() {
    val cervezas = listOf("Cusqueña", "Pilsen Callao", "Cristal", "Arequipeña", "Trujillo")
    var seleccion by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Cheves",
            fontSize = 22.sp,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF00695C))
                .padding(12.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        cervezas.forEach { cerveza ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                RadioButton(
                    selected = (cerveza == seleccion),
                    onClick = { seleccion = cerveza }
                )
                Text(text = cerveza, fontSize = 18.sp)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                resultado = if (seleccion.isNotEmpty()) {
                    "Has seleccionado: $seleccion"
                } else {
                    "Por favor selecciona una cerveza."
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("SELECCIONAR")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = resultado,
            fontSize = 18.sp,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CerveceriaTheme {
        Cervezas()
    }
}