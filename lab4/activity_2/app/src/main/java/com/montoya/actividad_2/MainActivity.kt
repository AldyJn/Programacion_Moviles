package com.montoya.actividad_2

import androidx.compose.ui.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.internal.enableLiveLiterals
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.montoya.actividad_2.ui.theme.Actividad_2Theme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ContadorComparacionScreen()

        }
    }
}


@Composable
fun ContadorComparacionScreen() {

    var contadorRemember by remember { mutableStateOf(0) }


    var contadorSaveable by rememberSaveable { mutableStateOf(0) }
    var bloqueado by rememberSaveable { mutableStateOf(false) }
    var mensajeError by rememberSaveable { mutableStateOf("") }

    var mostrandoError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Contador: remember vs rememberSaveable",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 32.dp)
        )


        Button(
            onClick = {
                bloqueado = !bloqueado
                mensajeError = if (bloqueado) "Contadores bloqueados" else ""
                mostrandoError = bloqueado
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(bottom = 24.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (bloqueado)
                    MaterialTheme.colorScheme.error
                else
                    MaterialTheme.colorScheme.secondary
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Icon(
                imageVector = if (bloqueado) Icons.Default.Lock else Icons.Default.Done,
                contentDescription = null,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = if (bloqueado) "Desbloquear Contadores" else "Bloquear Contadores",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }

        if (mensajeError.isNotEmpty()) {
            MensajeEstado(
                mensaje = mensajeError,
                esError = true,
                modifier = Modifier.padding(bottom = 24.dp)
            )
        }

        ContadorCard(
            titulo = "Contador con remember",
            subtitulo = "SE PIERDE al rotar",
            valor = contadorRemember,
            onIncrement = {
                if (bloqueado) {
                    mostrandoError = true
                } else {
                    contadorRemember++
                }
            },
            onDecrement = {
                if (bloqueado) {
                    mostrandoError = true
                } else {
                    contadorRemember--
                }
            },
            bloqueado = bloqueado,
            colorTarjeta = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.3f),
            colorBorde = MaterialTheme.colorScheme.error.copy(alpha = 0.5f)
        )

        Spacer(modifier = Modifier.height(24.dp))

        ContadorCard(
            titulo = "Contador con rememberSaveable",
            subtitulo = "PERSISTE al rotar",
            valor = contadorSaveable,
            onIncrement = {
                if (bloqueado) {
                    mostrandoError = true
                } else {
                    contadorSaveable++
                }
            },
            onDecrement = {
                if (bloqueado) {
                    mostrandoError = true
                } else {
                    contadorSaveable--
                }
            },
            bloqueado = bloqueado,
            colorTarjeta = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f),
            colorBorde = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
        )

        Spacer(modifier = Modifier.height(32.dp))


        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { contadorRemember = 0 },
                enabled = !bloqueado,
                modifier = Modifier.weight(1f).padding(end = 8.dp),
                colors = ButtonDefaults.outlinedButtonColors()
            ) {
                Text("Reset Remember")
            }

            Button(
                onClick = { contadorSaveable = 0 },
                enabled = !bloqueado,
                modifier = Modifier.weight(1f).padding(start = 8.dp),
                colors = ButtonDefaults.outlinedButtonColors()
            ) {
                Text("Reset Saveable")
            }
        }
    }
}


@Composable
fun ContadorCard(
    titulo: String,
    subtitulo: String,
    valor: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    bloqueado: Boolean,
    colorTarjeta: androidx.compose.ui.graphics.Color,
    colorBorde: androidx.compose.ui.graphics.Color,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = colorTarjeta,
                shape = RoundedCornerShape(16.dp)
            )
            .border(
                width = 2.dp,
                color = colorBorde,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = titulo,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Text(
                text = subtitulo,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = valor.toString(),
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = if (bloqueado)
                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                else
                    MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {


                FloatingActionButton(
                    onClick = {
                        if (!bloqueado) onIncrement()
                    },
                    containerColor = if (bloqueado)
                        MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                    else
                        MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(56.dp)
                ) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "Incrementar",
                        tint = if (bloqueado)
                            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                        else
                            MaterialTheme.colorScheme.onPrimary
                    )
                }

            }
        }
    }
}



@Composable
fun MensajeEstado(
    mensaje: String,
    esError: Boolean = false,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()          // Layout
            .background(             // Dibujo
                color = if (esError)
                    MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.3f)
                else
                    MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f),
                shape = RoundedCornerShape(12.dp)
            )
            .border(                 // Dibujo
                width = 1.dp,
                color = if (esError)
                    MaterialTheme.colorScheme.error.copy(alpha = 0.5f)
                else
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp),         // Padding al final
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = mensaje,
            style = MaterialTheme.typography.bodyMedium,
            color = if (esError)
                MaterialTheme.colorScheme.error
            else
                MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium
        )
    }
}




@Preview(showBackground = true)
@Composable
fun ContadorComparacionPreview() {
    MaterialTheme {
        ContadorComparacionScreen()
    }
}
