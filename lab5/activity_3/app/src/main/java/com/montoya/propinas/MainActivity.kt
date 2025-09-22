package com.montoya.propinas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.montoya.propinas.ui.theme.PropinasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraPropinas()
        }
    }
}

@Composable
fun CalculadoraPropinas() {
    var cuenta by remember { mutableStateOf("") }
    var personas by remember { mutableStateOf("1") }
    var propinaSeleccionada by remember { mutableStateOf(10) } // default 10%

    val cuentaDouble = cuenta.toDoubleOrNull() ?: 0.0
    val personasInt = personas.toIntOrNull()?.coerceAtLeast(1) ?: 1
    val montoPropina = cuentaDouble * (propinaSeleccionada / 100.0)
    val total = cuentaDouble + montoPropina
    val porPersona = total / personasInt

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Calculadora de Propinas",
            fontSize = 22.sp,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF00695C))
                .padding(12.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = cuenta,
            onValueChange = { cuenta = it },
            label = { Text("Monto de la cuenta") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Seleccione el porcentaje de propina:", fontSize = 16.sp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            listOf(5, 10, 15, 20).forEach { porcentaje ->
                OutlinedButton(
                    onClick = { propinaSeleccionada = porcentaje },
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = if (propinaSeleccionada == porcentaje) Color(0xFF80CBC4) else Color.Transparent
                    )
                ) {
                    Text("$porcentaje%")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = personas,
            onValueChange = { personas = it },
            label = { Text("Número de personas") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F8E9)),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Monto de propina: S/. ${"%.2f".format(montoPropina)}", fontSize = 16.sp)
                Text("Total a pagar: S/. ${"%.2f".format(total)}", fontSize = 16.sp)
                Text("Por persona: S/. ${"%.2f".format(porPersona)}", fontSize = 16.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PropinasTheme {
        CalculadoraPropinas()
    }
}