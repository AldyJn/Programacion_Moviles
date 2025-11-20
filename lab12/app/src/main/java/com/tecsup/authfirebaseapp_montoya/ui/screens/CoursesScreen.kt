package com.tecsup.authfirebaseapp_montoya.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tecsup.authfirebaseapp_montoya.model.Course

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoursesScreen(
    onNavigateBack: () -> Unit,
    onAddCourse: () -> Unit,
    onEditCourse: (String) -> Unit
) {
    val auth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()
    val context = LocalContext.current
    val userId = auth.currentUser?.uid ?: ""

    var courses by remember { mutableStateOf<List<Course>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    // Estado para el diálogo de confirmación
    var showDeleteDialog by remember { mutableStateOf(false) }
    var courseToDelete by remember { mutableStateOf<Course?>(null) }

    LaunchedEffect(Unit) {
        db.collection("courses")
            .whereEqualTo("userId", userId)
            .addSnapshotListener { snapshot, error ->
                isLoading = false
                if (error != null) {
                    Toast.makeText(
                        context,
                        "Error: ${error.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@addSnapshotListener
                }

                courses = snapshot?.documents?.mapNotNull { doc ->
                    Course(
                        id = doc.id,
                        nombre = doc.getString("nombre") ?: "",
                        codigo = doc.getString("codigo") ?: "",
                        creditos = doc.getLong("creditos")?.toInt() ?: 0,
                        userId = doc.getString("userId") ?: ""
                    )
                } ?: emptyList()
            }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mis Cursos") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, "Volver")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddCourse) {
                Icon(Icons.Default.Add, "Agregar curso")
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                courses.isEmpty() -> {
                    Text(
                        "No tienes cursos registrados",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(courses) { course ->
                            CourseItem(
                                course = course,
                                onEdit = { onEditCourse(course.id) },
                                onDelete = {
                                    // Mostrar diálogo de confirmación
                                    courseToDelete = course
                                    showDeleteDialog = true
                                }
                            )
                        }
                    }
                }
            }
        }

        // Diálogo de confirmación de eliminación
        if (showDeleteDialog && courseToDelete != null) {
            AlertDialog(
                onDismissRequest = {
                    showDeleteDialog = false
                    courseToDelete = null
                },
                icon = {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Eliminar",
                        tint = MaterialTheme.colorScheme.error
                    )
                },
                title = {
                    Text("Eliminar Curso")
                },
                text = {
                    Text(
                        "¿Estás seguro que deseas eliminar el curso \"${courseToDelete?.nombre}\"?\n\n" +
                                "Esta acción no se puede deshacer."
                    )
                },
                confirmButton = {
                    Button(
                        onClick = {
                            courseToDelete?.let { course ->
                                db.collection("courses").document(course.id)
                                    .delete()
                                    .addOnSuccessListener {
                                        Toast.makeText(
                                            context,
                                            "Curso eliminado exitosamente",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                    .addOnFailureListener { e ->
                                        Toast.makeText(
                                            context,
                                            "Error al eliminar: ${e.message}",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                            }
                            showDeleteDialog = false
                            courseToDelete = null
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.error
                        )
                    ) {
                        Text("Eliminar")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showDeleteDialog = false
                            courseToDelete = null
                        }
                    ) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }
}

@Composable
fun CourseItem(
    course: Course,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(course.nombre, style = MaterialTheme.typography.titleMedium)
                Text("Código: ${course.codigo}", style = MaterialTheme.typography.bodyMedium)
                Text("Créditos: ${course.creditos}", style = MaterialTheme.typography.bodySmall)
            }

            Row {
                IconButton(onClick = onEdit) {
                    Icon(Icons.Default.Edit, "Editar")
                }
                IconButton(onClick = onDelete) {
                    Icon(
                        Icons.Default.Delete,
                        "Eliminar",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}