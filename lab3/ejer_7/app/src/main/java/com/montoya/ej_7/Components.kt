package com.montoya.ej_7
import android.content.Context
import android.view.View.generateViewId
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.pager.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import android.widget.Button
import android.widget.TextView
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import kotlinx.coroutines.launch



// LazyColumn: Para mostrar listas largas con scroll eficiente (solo renderiza elementos visibles)
@Composable
fun LazyColumnExample() {
    val items = remember { (1..100).map { "Elemento $it" } }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items) { item ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Text(
                    text = item,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}
// LazyRow: Para mostrar listas horizontales con scroll eficiente
@Composable
fun LazyRowExample() {
    val items = remember { (1..20).map { "Item $it" } }

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items) { item ->
            Card(
                modifier = Modifier
                    .width(120.dp)
                    .height(80.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = item,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

// LazyVerticalGrid: Para mostrar elementos en una cuadrícula con scroll vertical eficiente
@Composable
fun GridExample() {
    val items = remember { (1..50).map { "Item $it" } }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = item,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

// ConstraintLayout: Para crear layouts con restricciones flexibles entre vistas

fun ConstraintLayoutExample(context: Context): ConstraintLayout {

    // Crear el ConstraintLayout principal
    val constraintLayout = ConstraintLayout(context)
    constraintLayout.id = ConstraintLayout.generateViewId()

    // Crear TextView
    val textView = TextView(context).apply {
        id = generateViewId()
        text = "¡Hola ConstraintLayout!"
        textSize = 18f
    }

    // Crear Button
    val button = Button(context).apply {
        id = generateViewId()
        text = "Presionar"
        setOnClickListener {
            textView.text = "¡Botón presionado!"
        }
    }

    // Agregar vistas al layout
    constraintLayout.addView(textView)
    constraintLayout.addView(button)

    // Crear ConstraintSet para definir las restricciones
    val constraintSet = ConstraintSet()
    constraintSet.clone(constraintLayout)

    // Configurar restricciones para el TextView
    constraintSet.connect(textView.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 100)
    constraintSet.connect(textView.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
    constraintSet.connect(textView.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)

    // Configurar restricciones para el Button
    constraintSet.connect(button.id, ConstraintSet.TOP, textView.id, ConstraintSet.BOTTOM, 50)
    constraintSet.connect(button.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
    constraintSet.connect(button.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)

    // Aplicar las restricciones
    constraintSet.applyTo(constraintLayout)

    return constraintLayout
}

// Scaffold: Estructura básica de pantalla con TopBar, BottomBar y contenido principal
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldExample() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi App") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ) {
                Text(
                    text = "Bottom Bar",
                    modifier = Modifier.padding(16.dp)
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Acción */ }
            ) {
                Text("+")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Contenido principal",
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}

// Surface: Contenedor con elevación, color de fondo y forma personalizable
@Composable
fun SurfaceExample() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 8.dp,
        tonalElevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Surface Example",
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = "Este es un contenido dentro de una Surface con sombra y bordes redondeados.",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

// Chip: Elemento compacto para mostrar información, filtros o acciones
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChipExample() {
    var selected by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // AssistChip
        AssistChip(
            onClick = { /* Acción */ },
            label = { Text("Assist Chip") }
        )

        // FilterChip
        FilterChip(
            onClick = { selected = !selected },
            label = { Text("Filter Chip") },
            selected = selected
        )

        // InputChip
        InputChip(
            onClick = { /* Acción */ },
            label = { Text("Input Chip") },
            selected = false,
            trailingIcon = {
                Text("×", style = MaterialTheme.typography.titleMedium)
            }
        )

        // SuggestionChip
        SuggestionChip(
            onClick = { /* Acción */ },
            label = { Text("Suggestion Chip") }
        )
    }
}

// BackdropScaffold: Layout con capa trasera revelable simulado con AnimatedVisibility
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackdropScaffoldExample() {
    var backLayerRevealed by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Back layer
        AnimatedVisibility(
            visible = backLayerRevealed,
            enter = slideInVertically(initialOffsetY = { -it }),
            exit = slideOutVertically(targetOffsetY = { -it })
        ) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.primaryContainer
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Spacer(modifier = Modifier.height(80.dp)) // Space for TopBar
                    Text(
                        text = "Back Layer Content",
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Text("Configuraciones, filtros o navegación")
                }
            }
        }

        // Front layer
        Column {
            TopAppBar(
                title = { Text("Backdrop Example") },
                navigationIcon = {
                    IconButton(onClick = { backLayerRevealed = !backLayerRevealed }) {
                        Text("☰")
                    }
                }
            )
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Front Layer Content",
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Text("Contenido principal de la aplicación")
                }
            }
        }
    }
}

// FlowRow: Layout que organiza elementos en filas, creando nuevas filas cuando no hay espacio
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FlowRowExample() {
    val tags = remember {
        listOf("Kotlin", "Android", "Compose", "Material Design", "UI", "Development", "Mobile", "Programming")
    }

    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        tags.forEach { tag ->
            AssistChip(
                onClick = { /* Acción */ },
                label = { Text(tag) }
            )
        }
    }
}

// FlowColumn: Layout que organiza elementos en columnas, creando nuevas columnas cuando no hay espacio
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FlowColumnExample() {
    val items = remember {
        listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7", "Item 8")
    }

    FlowColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        maxItemsInEachColumn = 4
    ) {
        items.forEach { item ->
            Card(
                modifier = Modifier.width(100.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Text(
                    text = item,
                    modifier = Modifier.padding(12.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

// Controles

// AlertDialog: Diálogo modal para mostrar alertas, confirmaciones o información importante
@Composable
fun AlertDialogExample() {
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { showDialog = true }) {
            Text("Mostrar Diálogo")
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = {
                    Text("Confirmar Acción")
                },
                text = {
                    Text("¿Estás seguro de que quieres realizar esta acción?")
                },
                confirmButton = {
                    TextButton(
                        onClick = { showDialog = false }
                    ) {
                        Text("Confirmar")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = { showDialog = false }
                    ) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }
}

// Card: Contenedor elevado con bordes redondeados para agrupar contenido relacionado
@Composable
fun CardExample() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Card Title",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Este es el contenido de la card. Puede incluir texto, imágenes, botones y otros elementos.",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(onClick = { /* Acción */ }) {
                    Text("Acción 1")
                }
                OutlinedButton(onClick = { /* Acción */ }) {
                    Text("Acción 2")
                }
            }
        }
    }
}

// Checkbox: Control de selección para opciones múltiples
@Composable
fun CheckboxExample() {
    var checkedStates by remember {
        mutableStateOf(
            listOf(
                "Opción 1" to false,
                "Opción 2" to true,
                "Opción 3" to false
            )
        )
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Selecciona opciones:",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(8.dp))

        checkedStates.forEachIndexed { index, (text, isChecked) ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = { newValue ->
                        checkedStates = checkedStates.toMutableList().apply {
                            set(index, text to newValue)
                        }
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = text)
            }
        }
    }
}

// FloatingActionButton: Botón flotante circular para la acción principal de la pantalla
@Composable
fun FloatingActionButtonExample() {
    var clickCount by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Clicks: $clickCount",
                style = MaterialTheme.typography.headlineMedium
            )
        }

        // FAB Normal
        FloatingActionButton(
            onClick = { clickCount++ },
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Text("+")
        }

        // FAB Extendido
        ExtendedFloatingActionButton(
            onClick = { clickCount = 0 },
            modifier = Modifier.align(Alignment.BottomStart)
        ) {
            Text("Reset")
        }

        // FAB Pequeño
        SmallFloatingActionButton(
            onClick = { clickCount-- },
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Text("-")
        }
    }
}

// Icon: Componente para mostrar iconos vectoriales con tamaño y color personalizables
@Composable
fun IconExample() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Iconos de ejemplo:",
            style = MaterialTheme.typography.headlineSmall
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favorito",
                tint = Color.Red
            )

            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Estrella",
                modifier = Modifier.size(32.dp),
                tint = Color.Yellow
            )

            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Configuración",
                modifier = Modifier.size(48.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

// Image: Componente para mostrar imágenes con opciones de escalado y recorte
@Composable
fun ImageExample() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Imágenes de ejemplo:",
            style = MaterialTheme.typography.headlineSmall
        )

        // Imagen desde recursos
        Image(
            painter = painterResource(id = android.R.drawable.ic_menu_gallery),
            contentDescription = "Galería",
            modifier = Modifier.size(64.dp),
            contentScale = ContentScale.Fit
        )

        // Imagen con forma circular
        Image(
            painter = painterResource(id = android.R.drawable.ic_menu_camera),
            contentDescription = "Cámara",
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        // Imagen con bordes redondeados
        Image(
            painter = painterResource(id = android.R.drawable.ic_menu_info_details),
            contentDescription = "Info",
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Inside
        )
    }

    // ProgressBar: Indicador de progreso lineal y circular para mostrar el estado de una tarea
    @Composable
    fun ProgressBarExample() {
        var progress by remember { mutableFloatStateOf(0.3f) }

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Progress Bars:",
                style = MaterialTheme.typography.headlineSmall
            )

            // Progress lineal determinado
            Text("Progreso: ${(progress * 100).toInt()}%")
            LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier.fillMaxWidth(),
            color = ProgressIndicatorDefaults.linearColor,
            trackColor = ProgressIndicatorDefaults.linearTrackColor,
            strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
            )

            // Progress lineal indeterminado
            Text("Cargando...")
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth()
            )

            // Progress circular determinado
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CircularProgressIndicator(
                progress = { progress },
                    color = ProgressIndicatorDefaults.circularColor,
                strokeWidth = ProgressIndicatorDefaults.CircularStrokeWidth,
                trackColor = ProgressIndicatorDefaults.circularTrackColor,
                strokeCap = ProgressIndicatorDefaults.CircularDeterminateStrokeCap,
                )
                Text("${(progress * 100).toInt()}%")
            }

            // Progress circular indeterminado
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CircularProgressIndicator()
                Text("Procesando...")
            }

            // Botón para cambiar progreso
            Button(
                onClick = { progress = if (progress >= 1f) 0f else progress + 0.1f }
            ) {
                Text("Cambiar Progreso")
            }
        }
    }
}

// RadioButton: Control de selección única para elegir una opción entre varias
@Composable
fun RadioButtonExample() {
    val options = listOf("Opción A", "Opción B", "Opción C", "Opción D")
    var selectedOption by remember { mutableStateOf(options[0]) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Selecciona una opción:",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(8.dp))

        options.forEach { option ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (option == selectedOption),
                        onClick = { selectedOption = option }
                    )
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (option == selectedOption),
                    onClick = { selectedOption = option }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = option)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Seleccionado: $selectedOption",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

// Slider: Control deslizable para seleccionar un valor dentro de un rango
@Composable
fun SliderExample() {
    var sliderValue by remember { mutableStateOf(50f) }
    var rangeSliderValue by remember { mutableStateOf(20f..80f) }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Sliders:",
            style = MaterialTheme.typography.headlineSmall
        )

        // Slider simple
        Text("Valor: ${sliderValue.toInt()}")
        Slider(
            value = sliderValue,
            onValueChange = { sliderValue = it },
            valueRange = 0f..100f,
            steps = 10
        )

        // RangeSlider
        Text("Rango: ${rangeSliderValue.start.toInt()} - ${rangeSliderValue.endInclusive.toInt()}")
        RangeSlider(
            value = rangeSliderValue,
            onValueChange = { rangeSliderValue = it },
            valueRange = 0f..100f
        )
    }
}

// Spacer: Elemento invisible para crear espacio entre componentes
@Composable
fun SpacerExample() {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text("Primer elemento")
        Spacer(modifier = Modifier.height(16.dp))
        Text("Segundo elemento")
        Spacer(modifier = Modifier.height(32.dp))

        Row {
            Text("Izquierda")
            Spacer(modifier = Modifier.width(24.dp))
            Text("Derecha")
        }

        Spacer(modifier = Modifier.height(48.dp))
        Text("Elemento final")
    }
}

// Switch: Control de encendido/apagado para alternar entre dos estados
@Composable
fun SwitchExample() {
    var switches by remember {
        mutableStateOf(
            listOf(
                "Notificaciones" to true,
                "Modo oscuro" to false,
                "Sonido" to true,
                "Vibración" to false
            )
        )
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Configuraciones:",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(8.dp))

        switches.forEachIndexed { index, (label, isChecked) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = label)
                Switch(
                    checked = isChecked,
                    onCheckedChange = { newValue ->
                        switches = switches.toMutableList().apply {
                            set(index, label to newValue)
                        }
                    }
                )
            }
        }
    }
}

// TopAppBar: Barra superior de navegación con título y acciones
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarExample() {
    var showMenu by remember { mutableStateOf(false) }

    Column {
        // TopAppBar básica
        TopAppBar(
            title = { Text("Mi Aplicación") },
            navigationIcon = {
                IconButton(onClick = { /* Navegación */ }) {
                    Icon(Icons.Default.Menu, contentDescription = "Menú")
                }
            },
            actions = {
                IconButton(onClick = { /* Buscar */ }) {
                    Icon(Icons.Default.Search, contentDescription = "Buscar")
                }
                IconButton(onClick = { showMenu = !showMenu }) {
                    Icon(Icons.Default.MoreVert, contentDescription = "Más")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        )

        // Contenido principal
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Contenido de la aplicación",
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}

// BottomNavigation: Barra de navegación inferior para alternar entre secciones principales
@Composable
fun BottomNavigationExample() {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("Inicio", "Buscar", "Favoritos", "Perfil")
    val icons = listOf(
        Icons.Default.Home,
        Icons.Default.Search,
        Icons.Default.Favorite,
        Icons.Default.Person
    )

    Column {
        // Contenido principal
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Pantalla: ${items[selectedItem]}",
                style = MaterialTheme.typography.headlineMedium
            )
        }

        // Bottom Navigation
        NavigationBar {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    icon = { Icon(icons[index], contentDescription = item) },
                    label = { Text(item) },
                    selected = selectedItem == index,
                    onClick = { selectedItem = index }
                )
            }
        }
    }
}

// Dialog: Ventana modal personalizable para mostrar contenido sobre la UI principal
@Composable
fun DialogExample() {
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { showDialog = true }) {
            Text("Mostrar Diálogo Personalizado")
        }

        if (showDialog) {
            Dialog(
                onDismissRequest = { showDialog = false }
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            Icons.Default.Info,
                            contentDescription = "Info",
                            modifier = Modifier.size(48.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Diálogo Personalizado",
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Este es un diálogo completamente personalizable con cualquier contenido.")
                        Spacer(modifier = Modifier.height(20.dp))
                        Button(
                            onClick = { showDialog = false },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Cerrar")
                        }
                    }
                }
            }
        }
    }
}

// Divider: Línea divisoria para separar visualmente secciones de contenido
@Composable
fun DividerExample() {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text("Sección 1")
        Text("Contenido de la primera sección")

        Divider(modifier = Modifier.padding(vertical = 16.dp))

        Text("Sección 2")
        Text("Contenido de la segunda sección")

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 16.dp),
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.primary
        )

        Text("Sección 3")
        Text("Contenido de la tercera sección")

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text("Izquierda")
            VerticalDivider(
                modifier = Modifier
                    .height(40.dp)
                    .padding(horizontal = 16.dp)
            )
            Text("Derecha")
        }
    }
}

// DropdownMenu: Menú desplegable que se muestra al hacer clic en un elemento
@Composable
fun DropdownMenuExample() {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("Seleccionar opción") }
    val options = listOf("Opción 1", "Opción 2", "Opción 3", "Opción 4")

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Menú desplegable:",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(8.dp))

        Box {
            OutlinedButton(
                onClick = { expanded = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(selectedOption)
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            selectedOption = option
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Seleccionado: $selectedOption")
    }
}

// LazyVerticalGrid: Cuadrícula vertical con scroll eficiente para grandes cantidades de datos
@Composable
fun LazyVerticalGridExample() {
    val items = remember { (1..100).map { "Item $it" } }

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 120.dp),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = item,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

// NavigationRail: Navegación lateral vertical para pantallas medianas y grandes
@Composable
fun NavigationRailExample() {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Inicio", "Buscar", "Favoritos", "Perfil")
    val icons = listOf(
        Icons.Default.Home,
        Icons.Default.Search,
        Icons.Default.Favorite,
        Icons.Default.Person
    )

    Row {
        NavigationRail {
            items.forEachIndexed { index, item ->
                NavigationRailItem(
                    icon = { Icon(icons[index], contentDescription = item) },
                    label = { Text(item) },
                    selected = selectedItem == index,
                    onClick = { selectedItem = index }
                )
            }
        }

        // Contenido principal
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Pantalla: ${items[selectedItem]}",
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}
// OutlinedTextField: Campo de texto con borde delineado para entrada de datos
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedTextFieldExample() {
    var text by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Campos de texto:",
            style = MaterialTheme.typography.headlineSmall
        )

        // Campo básico
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Nombre") },
            placeholder = { Text("Ingresa tu nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        // Campo de email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            leadingIcon = {
                Icon(Icons.Default.Email, contentDescription = "Email")
            },
            modifier = Modifier.fillMaxWidth()
        )

        // Campo de contraseña
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Text(if (isPasswordVisible) "👁" else "👁‍🗨")
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
// Pager: Navegación deslizable horizontal entre páginas de contenido
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerExample() {
    val pagerState = rememberPagerState(pageCount = { 5 })

    Column {
        // Indicador de páginas
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(5) { index ->
                val isSelected = pagerState.currentPage == index
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(
                            color = if (isSelected) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.outline,
                            shape = CircleShape
                        )
                )
                if (index < 4) Spacer(modifier = Modifier.width(8.dp))
            }
        }

        // Pager
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Página ${page + 1}",
                        style = MaterialTheme.typography.headlineLarge
                    )
                }
            }
        }
    }
}

// Snackbar: Mensaje temporal en la parte inferior para mostrar feedback
@Composable
fun SnackbarExample() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    scope.launch {
                        snackbarHostState.showSnackbar("Mensaje simple")
                    }
                }
            ) {
                Text("Snackbar Simple")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    scope.launch {
                        val result = snackbarHostState.showSnackbar(
                            message = "¿Deshacer acción?",
                            actionLabel = "Deshacer"
                        )
                        if (result == SnackbarResult.ActionPerformed) {
                            snackbarHostState.showSnackbar("Acción deshecha")
                        }
                    }
                }
            ) {
                Text("Snackbar con Acción")
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

// TabRow: Fila de pestañas para navegación entre secciones relacionadas
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabRowExample() {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Tab 1", "Tab 2", "Tab 3", "Tab 4")

    Column {
        TabRow(selectedTabIndex = selectedTabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index }
                )
            }
        }

        // Contenido de las tabs
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            when (selectedTabIndex) {
                0 -> Text("Contenido de Tab 1", style = MaterialTheme.typography.headlineMedium)
                1 -> Text("Contenido de Tab 2", style = MaterialTheme.typography.headlineMedium)
                2 -> Text("Contenido de Tab 3", style = MaterialTheme.typography.headlineMedium)
                3 -> Text("Contenido de Tab 4", style = MaterialTheme.typography.headlineMedium)
            }
        }
    }
}

// Tooltip: Mensaje emergente que aparece al mantener presionado un elemento
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TooltipExample() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Tooltips:",
            style = MaterialTheme.typography.headlineSmall
        )

        // Tooltip básico
        TooltipBox(
            positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
            tooltip = {
                PlainTooltip {
                    Text("Este es un tooltip básico")
                }
            },
            state = rememberTooltipState()
        ) {
            IconButton(onClick = { /* Acción */ }) {
                Icon(Icons.Default.Info, contentDescription = "Información")
            }
        }

        // Rich Tooltip
        TooltipBox(
            positionProvider = TooltipDefaults.rememberRichTooltipPositionProvider(),
            tooltip = {
                RichTooltip(
                    title = { Text("Título del Tooltip") },
                    action = {
                        TextButton(onClick = { /* Acción */ }) {
                            Text("Acción")
                        }
                    }
                ) {
                    Text("Este es un tooltip enriquecido con título y acción")
                }
            },
            state = rememberTooltipState()
        ) {
            Button(onClick = { /* Acción */ }) {
                Text("Botón con Rich Tooltip")
            }
        }
    }
}