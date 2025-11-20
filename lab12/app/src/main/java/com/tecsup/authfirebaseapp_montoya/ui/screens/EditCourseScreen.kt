package com.tecsup.authfirebaseapp_montoya.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditCourseScreen(
    courseId: String,
    onNavigateBack: () -> Unit,
    onCourseSaved: () -> Unit
) {
    val db = FirebaseFirestore.getInstance()
    val context = LocalContext.current

    var nombre by remember { mutableStateOf("") }
    var codigo by remember { mutableStateOf("") }
    var creditos by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var isLoadingData by remember { mutableStateOf(true) }

    LaunchedEffect(courseId) {
        db.collection("courses").document(courseId)
            .get()
            .addOnSuccessListener { document ->
                isLoadingData = false
                nombre = document.getString("nombre") ?: ""
                codigo = document.getString("codigo") ?: ""
                creditos = document.getLong("creditos")?.toString() ?: ""
            }
            .addOnFailureListener {
                isLoadingData = false
                Toast.makeText(
                    context,
                    "Error al cargar curso",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Editar Curso") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, "Volver")
                    }
                }
            )
        }
    ) { paddingValues ->
        if (isLoadingData) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre del curso") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = codigo,
                    onValueChange = { codigo = it },
                    label = { Text("Código del curso") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = creditos,
                    onValueChange = { creditos = it },
                    label = { Text("Créditos") },
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = {
                        if (nombre.isBlank() || codigo.isBlank() || creditos.isBlank()) {
                            Toast.makeText(
                                context,
                                "Complete todos los campos",
                                Toast.LENGTH_SHORT
                            ).show()
                            return@Button
                        }

                        val creditosInt = creditos.toIntOrNull()
                        if (creditosInt == null || creditosInt <= 0) {
                            Toast.makeText(
                                context,
                                "Créditos inválidos",
                                Toast.LENGTH_SHORT
                            ).show()
                            return@Button
                        }

                        isLoading = true
                        val updates = hashMapOf<String, Any>(
                            "nombre" to nombre,
                            "codigo" to codigo,
                            "creditos" to creditosInt
                        )

                        db.collection("courses").document(courseId)
                            .update(updates)
                            .addOnSuccessListener {
                                isLoading = false
                                Toast.makeText(
                                    context,
                                    "Curso actualizado exitosamente",
                                    Toast.LENGTH_LONG
                                ).show()
                                onCourseSaved()
                            }
                            .addOnFailureListener { e ->
                                isLoading = false
                                Toast.makeText(
                                    context,
                                    "Error al actualizar: ${e.message}",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                    },
                    enabled = !isLoading,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(if (isLoading) "Actualizando..." else "Actualizar Curso")
                }
            }
        }
    }
}