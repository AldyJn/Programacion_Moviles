package com.tecsup.authfirebaseapp_montoya.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCourseScreen(
    onNavigateBack: () -> Unit,
    onCourseSaved: () -> Unit
) {
    val auth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()
    val context = LocalContext.current
    val userId = auth.currentUser?.uid ?: ""

    var nombre by remember { mutableStateOf("") }
    var codigo by remember { mutableStateOf("") }
    var creditos by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Agregar Curso") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, "Volver")
                    }
                }
            )
        }
    ) { paddingValues ->
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
                        Toast.makeText(context, "Complete todos los campos", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    val creditosInt = creditos.toIntOrNull()
                    if (creditosInt == null || creditosInt <= 0) {
                        Toast.makeText(context, "Créditos inválidos", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    isLoading = true
                    val courseData = hashMapOf(
                        "nombre" to nombre,
                        "codigo" to codigo,
                        "creditos" to creditosInt,
                        "userId" to userId
                    )

                    db.collection("courses")
                        .add(courseData)
                        .addOnSuccessListener {
                            isLoading = false
                            Toast.makeText(
                                context,
                                "Curso guardado exitosamente",
                                Toast.LENGTH_LONG
                            ).show()
                            onCourseSaved()
                        }
                        .addOnFailureListener { e ->
                            isLoading = false
                            Toast.makeText(
                                context,
                                "Error al guardar: ${e.message}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                },
                enabled = !isLoading,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (isLoading) "Guardando..." else "Guardar Curso")
            }
        }
    }
}