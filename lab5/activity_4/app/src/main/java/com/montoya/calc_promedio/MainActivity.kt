package com.montoya.calc_promedio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.montoya.calc_promedio.ui.theme.Calc_promedioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PromedioAPP()
        }
    }
}

@Composable
fun PromedioAPP() {
    var listaAlumnos by remember { mutableStateOf(mutableListOf<Alumno>()) }
    var nombreAlumno by remember { mutableStateOf("") }
    var examanes by remember { mutableStateOf(List(4) { "" }) }
    var lab by remember { mutableStateOf(List(8) { "" }) }
    var mostrarResultado by remember { mutableStateOf(false) }
    var alumnosCalculado by remember { mutableStateOf<Alumno?>(null) }
    var currentPage by remember { mutableStateOf(1) }

    val scroll = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD))
            .padding(20.dp)
            .verticalScroll(scroll),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (currentPage) {
            1 -> {
                Text(
                    "Registro de Alumno",
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = Color(0xFF01579B),
                        fontSize = 22.sp
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(16.dp))

                OutlinedTextField(
                    value = nombreAlumno,
                    onValueChange = { nombreAlumno = it },
                    label = { Text("Nombre del alumno") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(20.dp))
                Text(
                    "Exámenes (4)",
                    style = MaterialTheme.typography.bodyLarge.copy(color = Color(0xFF0277BD))
                )

                examanes.forEachIndexed { i, valor ->
                    var error by remember { mutableStateOf<String?>(null) }

                    OutlinedTextField(
                        value = valor,
                        onValueChange = { nuevoValor ->
                            if (nuevoValor.all { it.isDigit() }) {
                                val numero = nuevoValor.toIntOrNull()
                                if (numero != null && numero in 0..20) {
                                    val nuevaLista = examanes.toMutableList()
                                    nuevaLista[i] = nuevoValor
                                    examanes = nuevaLista
                                    error = null
                                } else if (nuevoValor.isNotEmpty()) {
                                    error = "Debe estar entre 0 y 20"
                                } else {
                                    val nuevaLista = examanes.toMutableList()
                                    nuevaLista[i] = ""
                                    examanes = nuevaLista
                                    error = null
                                }
                            }
                        },
                        label = { Text("Examen ${i + 1}") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        isError = error != null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 6.dp)
                    )

                    if (error != null) {
                        Text(error!!, color = Color.Red, fontSize = 12.sp)
                    }
                }

                Spacer(Modifier.height(20.dp))
                Button(
                    onClick = { currentPage = 2 },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0288D1)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Siguiente →", color = Color.White)
                }
            }

            2 -> {
                Text(
                    "Laboratorios (8)",
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = Color(0xFF01579B),
                        fontSize = 22.sp
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(20.dp))

                lab.forEachIndexed { i, valor ->
                    var error by remember { mutableStateOf<String?>(null) }

                    OutlinedTextField(
                        value = valor,
                        onValueChange = { nuevoValor ->
                            if (nuevoValor.all { it.isDigit() }) {
                                val numero = nuevoValor.toIntOrNull()
                                if (numero != null && numero in 0..20) {
                                    val nuevaLista = lab.toMutableList()
                                    nuevaLista[i] = nuevoValor
                                    lab = nuevaLista
                                    error = null
                                } else if (nuevoValor.isNotEmpty()) {
                                    error = "Debe estar entre 0 y 20"
                                } else {
                                    val nuevaLista = lab.toMutableList()
                                    nuevaLista[i] = ""
                                    lab = nuevaLista
                                    error = null
                                }
                            }
                        },
                        label = { Text("Laboratorio ${i + 1}") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        isError = error != null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 6.dp)
                    )

                    if (error != null) {
                        Text(error!!, color = Color.Red, fontSize = 12.sp)
                    }
                }

                Spacer(Modifier.height(20.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = { currentPage = 1 },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4FC3F7))
                    ) {
                        Text("← Volver", color = Color.White)
                    }

                    Button(
                        onClick = {
                            val notasExamen =
                                examanes.mapNotNull { it.toDoubleOrNull() }.filter { it in 0.0..20.0 }
                            val notasLab =
                                lab.mapNotNull { it.toDoubleOrNull() }.filter { it in 0.0..20.0 }

                            if (notasExamen.size == 4 && notasLab.size == 8 && nombreAlumno.isNotBlank()) {
                                val alumno = Alumno(nombreAlumno, notasExamen, notasLab)
                                alumnosCalculado = alumno
                                listaAlumnos.add(alumno) // Guardamos en la lista
                                mostrarResultado = true
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0288D1))
                    ) {
                        Text("Calcular", color = Color.White)
                    }
                }
            }
        }

        if (mostrarResultado && alumnosCalculado != null) {
            Spacer(Modifier.height(24.dp))
            Text(
                "Resultado del alumno",
                style = MaterialTheme.typography.titleLarge.copy(color = Color(0xFF0277BD)),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(12.dp))
            Text("Alumno: ${alumnosCalculado!!.nombre}", fontSize = 18.sp)
            Text("Promedio Exámenes: ${"%.2f".format(alumnosCalculado!!.examanes.sortedDescending().take(3).average())}")
            Text("Promedio Laboratorios: ${"%.2f".format(alumnosCalculado!!.laboratorio.average())}")
            Text("Promedio Final: ${"%.2f".format(alumnosCalculado!!.calcularPromedio())}")
            Text(
                "Estado: ${alumnosCalculado!!.estadoFinal()}",
                fontSize = 18.sp,
                color = Color(0xFF01579B)
            )
        }

        // Mostrar ranking si hay al menos 3 alumnos
        if (listaAlumnos.size >= 3) {
            Spacer(Modifier.height(32.dp))
            Text(
                "Ranking de Promedios",
                style = MaterialTheme.typography.titleLarge.copy(color = Color(0xFF004D40)),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(12.dp))

            val ranking = listaAlumnos.sortedByDescending { it.calcularPromedio() }
            ranking.forEachIndexed { index, alumno ->
                Text(
                    "${index + 1}. ${alumno.nombre} - Promedio: ${"%.2f".format(alumno.calcularPromedio())} (${alumno.estadoFinal()})",
                    fontSize = 16.sp
                )
            }
        }
    }
}

data class Alumno(
    val nombre: String,
    val examanes: List<Double>,
    val laboratorio: List<Double>
) {
    fun calcularPromedio(): Double {
        val mejoresExamanes = examanes.sortedDescending().take(3)
        val promedioExamenes =
            if (mejoresExamanes.isNotEmpty()) mejoresExamanes.average() else 0.0
        val promedioLabs = if (laboratorio.isNotEmpty()) laboratorio.average() else 0.0
        return (promedioExamenes * 0.30) + (promedioLabs * 0.70)
    }

    fun estadoFinal(): String {
        val prom = calcularPromedio()
        return if (prom >= 10.5) "APROBADO" else "DESAPROBADO"
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Calc_promedioTheme {
        PromedioAPP()
    }
}
