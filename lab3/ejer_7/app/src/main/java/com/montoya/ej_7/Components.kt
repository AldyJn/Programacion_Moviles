package com.montoya.ej_7

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.flowlayout.FlowColumn
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import androidx.compose.ui.window.Dialog

// ================================
// CONTENEDORES
// ================================

@Composable
fun LazyColumnExample() {
    Column {
        Text("LazyColumn", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("Lista vertical con scrolling eficiente para grandes datasets")
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier.height(300.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(50) { index ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = 2.dp
                ) {
                    Text(
                        text = "Item #$index",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun LazyRowExample() {
    Column {
        Text("LazyRow", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("Lista horizontal con scrolling eficiente")
        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(20) { index ->
                Card(
                    modifier = Modifier.size(120.dp, 80.dp),
                    elevation = 2.dp
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Item $index")
                    }
                }
            }
        }
    }
}

@Composable
fun GridExample() {
    Column {
        Text("Grid (LazyVerticalGrid)", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("Disposición en cuadrícula con scrolling vertical")
        Spacer(modifier = Modifier.height(8.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.height(200.dp)
        ) {
            items(30) { index ->
                Card(
                    modifier = Modifier.aspectRatio(1f),
                    elevation = 2.dp
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("$index")
                    }
                }
            }
        }
    }
}

@Composable
fun ConstraintLayoutExample() {
    Column {
        Text("ConstraintLayout", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("Layout flexible con relaciones de restricciones")
        Spacer(modifier = Modifier.height(8.dp))

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.LightGray.copy(alpha = 0.3f))
        ) {
            val (button, text, icon) = createRefs()

            Button(
                onClick = { },
                modifier = Modifier.constrainAs(button) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                }
            ) {
                Text("Botón")
            }

            Text(
                "Texto centrado",
                modifier = Modifier.constrainAs(text) {
                    centerTo(parent)
                }
            )

            Icon(
                Icons.Default.Star,
                contentDescription = null,
                modifier = Modifier.constrainAs(icon) {
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                }
            )
        }
    }
}

@Composable
fun ScaffoldExample() {
    Column {
        Text("Scaffold", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("Estructura básica con AppBar, contenido y FAB")
        Spacer(modifier = Modifier.height(8.dp))

        Surface(
            modifier = Modifier.height(300.dp),
            elevation = 4.dp
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("Mi App") }
                    )
                },
                floatingActionButton = {
                    FloatingActionButton(onClick = { }) {
                        Icon(Icons.Default.Add, contentDescription = null)
                    }
                }
            ) { paddingValues ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Contenido principal")
                }
            }
        }
    }
}

@Composable
fun SurfaceExample() {
    Column {
        Text("Surface", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("Contenedor con material design y elevación")
        Spacer(modifier = Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Surface(
                modifier = Modifier.size(100.dp),
                elevation = 2.dp,
                shape = RoundedCornerShape(8.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("2dp")
                }
            }

            Surface(
                modifier = Modifier.size(100.dp),
                elevation = 8.dp,
                shape = RoundedCornerShape(8.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("8dp")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChipExample() {
    Column {
        Text("Chip", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("Elementos compactos para acciones o filtros")
        Spacer(modifier = Modifier.height(8.dp))

        var selectedChip1 by remember { mutableStateOf(false) }
        var selectedChip2 by remember { mutableStateOf(true) }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            // Simulando Chip como Button redondeado ya que Chip requiere Material3
            Button(
                onClick = { selectedChip1 = !selectedChip1 },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (selectedChip1) MaterialTheme.colors.primary
                    else MaterialTheme.colors.surface
                ),
                elevation = ButtonDefaults.elevation(2.dp)
            ) {
                Text("Assist Chip")
            }

            Button(
                onClick = { selectedChip2 = !selectedChip2 },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (selectedChip2) MaterialTheme.colors.primary
                    else MaterialTheme.colors.surface
                ),
                elevation = ButtonDefaults.elevation(2.dp)
            ) {
                Text("Filter Chip")
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BackdropScaffoldExample() {
    Column {
        Text("BackdropScaffold", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("Interfaz con capa posterior revelable")
        Spacer(modifier = Modifier.height(8.dp))

        val scaffoldState = rememberBackdropScaffoldState(BackdropValue.Concealed)

        Surface(
            modifier = Modifier.height(200.dp),
            elevation = 4.dp
        ) {
            BackdropScaffold(
                scaffoldState = scaffoldState,
                appBar = {
                    TopAppBar(
                        title = { Text("Backdrop") },
                        navigationIcon = {
                            IconButton(onClick = { }) {
                                Icon(Icons.Default.Menu, contentDescription = null)
                            }
                        }
                    )
                },
                backLayerContent = {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Contenido posterior")
                        Text("Opciones y configuración")
                    }
                },
                frontLayerContent = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Contenido frontal")
                    }
                }
            )
        }
    }
}

@Composable
fun FlowRowExample() {
    Column {
        Text("FlowRow", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("Disposición horizontal que envuelve automáticamente")
        Spacer(modifier = Modifier.height(8.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            repeat(10) { index ->
                Card(
                    modifier = Modifier.padding(4.dp),
                    elevation = 2.dp
                ) {
                    Text(
                        "Tag $index",
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun FlowColumnExample() {
    Column {
        Text("FlowColumn", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("Disposición vertical que envuelve automáticamente")
        Spacer(modifier = Modifier.height(8.dp))

        FlowColumn(
            modifier = Modifier.height(150.dp)
        ) {
            repeat(8) { index ->
                Card(
                    modifier = Modifier.padding(4.dp),
                    elevation = 2.dp
                ) {
                    Text(
                        "Item $index",
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}

// ================================
// CONTROLES
// ================================

@Composable
fun AlertDialogExample() {
    Column {
        Text("AlertDialog", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("Diálogo modal para alertas y confirmaciones")
        Spacer(modifier = Modifier.height(8.dp))

        var showDialog by remember { mutableStateOf(false) }

        Button(onClick = { showDialog = true }) {
            Text("Mostrar Diálogo")
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Título del Diálogo") },
                text = { Text("Este es el contenido del diálogo de alerta.") },
                confirmButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Confirmar")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }
}

@Composable
fun CardExample() {
    Column {
        Text("Card", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("Contenedor de material design con elevación")
        Spacer(modifier = Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Card(
                modifier = Modifier.size(120.dp, 80.dp),
                elevation = 4.dp
            ) {
                Column(
                    modifier = Modifier.padding(8.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("Card 1", fontWeight = FontWeight.Bold)
                    Text("Contenido", fontSize = 12.sp)
                }
            }

            Card(
                modifier = Modifier.size(120.dp, 80.dp),
                elevation = 8.dp,
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(8.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("Card 2", fontWeight = FontWeight.Bold)
                    Text("Redondeada", fontSize = 12.sp)
                }
            }
        }
    }
}

@Composable
fun CheckboxExample() {
    Column {
        Text("Checkbox", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("Control de selección múltiple")
        Spacer(modifier = Modifier.height(8.dp))

        var checked1 by remember { mutableStateOf(true) }
        var checked2 by remember { mutableStateOf(false) }
        var checked3 by remember { mutableStateOf<Boolean?>(null) }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = checked1, onCheckedChange = { checked1 = it })
            Text("Opción 1 (marcada)")
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = checked2, onCheckedChange = { checked2 = it })
            Text("Opción 2 (desmarcada)")
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            TriStateCheckbox(
                state = when (checked3) {
                    true -> ToggleableState.On
                    false -> ToggleableState.Off
                    null -> ToggleableState.Indeterminate
                },
                onClick = {
                    checked3 = when (checked3) {
                        true -> false
                        false -> null
                        null -> true
                    }
                }
            )
            Text("Opción 3 (indeterminada)")
        }
    }
}

@Composable
fun FloatingActionButtonExample() {
    Column {
        Text("FloatingActionButton", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("Botón de acción flotante principal")
        Spacer(modifier = Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            FloatingActionButton(
                onClick = { },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar")
            }

            ExtendedFloatingActionButton(
                text = { Text("Extendido") },
                icon = { Icon(Icons.Default.Edit, contentDescription = null) },
                onClick = { }
            )
        }
    }
}

@Composable
fun IconExample() {
    Column {
        Text("Icon", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("Iconos vectoriales escalables")
        Spacer(modifier = Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Icon(
                Icons.Default.Favorite,
                contentDescription = "Favorito",
                tint = Color.Red
            )

            Icon(
                Icons.Default.Star,
                contentDescription = "Estrella",
                tint = Color.Yellow,
                modifier = Modifier.size(32.dp)
            )

            Icon(
                Icons.Default.Settings,
                contentDescription = "Configuración",
                tint = MaterialTheme.colors.primary,
                modifier = Modifier.size(48.dp)
            )
        }
    }
}

@Composable
fun ImageExample() {
    Column {
        Text("Image", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("Visualización de imágenes con diferentes escalados")
        Spacer(modifier = Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            // Simulando imagen con un placeholder colorido
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.Blue, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text("IMG", color = Color.White)
            }

            Box(
                modifier = Modifier
                    .size(80.dp, 60.dp)
                    .background(Color.Green, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text("IMG", color = Color.White)
            }

            Box(
                modifier = Modifier
                    .size(60.dp, 80.dp)
                    .clip(CircleShape)
                    .background(Color.Red),
                contentAlignment = Alignment.Center
            ) {
                Text("IMG", color = Color.White, fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun ProgressBarExample() {
    Column {
        Text("ProgressBar", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("Indicadores de progreso linear y circular")
        Spacer(modifier = Modifier.height(8.dp))

        var progress by remember { mutableStateOf(0.7f) }

        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularProgressIndicator(progress = progress)
            CircularProgressIndicator()
        }

        Spacer(modifier = Modifier.height(8.dp))

        Slider(
            value = progress,
            onValueChange = { progress = it }
        )

        Text("Progreso: ${(progress * 100).toInt()}%")
    }
}

@Composable
fun RadioButtonExample() {
    Column {
        Text("RadioButton", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("Selección única entre opciones")
        Spacer(modifier = Modifier.height(8.dp))

        var selectedOption by remember { mutableStateOf("Opción 1") }
        val options = listOf("Opción 1", "Opción 2", "Opción 3")

        options.forEach { option ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable { selectedOption = option }
            ) {
                RadioButton(
                    selected = selectedOption == option,
                    onClick = { selectedOption = option }
                )
                Text(option)
            }
        }
    }
}

@Composable
fun SliderExample() {
    Column {
        Text("Slider", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("Control deslizante para valores numéricos")
        Spacer(modifier = Modifier.height(8.dp))

        var sliderValue by remember { mutableStateOf(50f) }
        var rangeSliderValue by remember { mutableStateOf(20f..80f) }

        Text("Valor simple: ${sliderValue.toInt()}")
        Slider(
            value = sliderValue,
            onValueChange = { sliderValue = it },
            valueRange = 0f..100f
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Rango: ${rangeSliderValue.start.toInt()} - ${rangeSliderValue.endInclusive.toInt()}")
        RangeSlider(
            value = rangeSliderValue,
            onValueChange = { rangeSliderValue = it },
            valueRange = 0f..100f
        )
    }
}

@Composable
fun SpacerExample() {
    Column {
        Text("Spacer", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("Elemento invisible para crear espacios")
        Spacer(modifier = Modifier.height(8.dp))

        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = Color.LightGray.copy(alpha = 0.3f)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row {
                    Text("Texto 1")
                    Spacer(modifier = Modifier.width(32.dp))
                    Text("Texto 2")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row {
                    Text("Sin espaciador")
                    Text("Pegado")
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text("Con espaciador vertical de 24.dp arriba")
            }
        }
    }
}

@Composable
fun SwitchExample() {
    Column {
        Text("Switch", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("Interruptor de encendido/apagado")
        Spacer(modifier = Modifier.height(8.dp))

        var switch1 by remember { mutableStateOf(true) }
        var switch2 by remember { mutableStateOf(false) }
        var switch3 by remember { mutableStateOf(true) }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Switch(checked = switch1, onCheckedChange = { switch1 = it })
            Spacer(modifier = Modifier.width(8.dp))
            Text("Notificaciones")
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Switch(checked = switch2, onCheckedChange = { switch2 = it })
            Spacer(modifier = Modifier.width(8.dp))
            Text("Modo oscuro")
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Switch(
                checked = switch3,
                onCheckedChange = { switch3 = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Green,
                    uncheckedThumbColor = Color.Red
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Switch personalizado")
        }
    }
}

@Composable
fun TopAppBarExample() {
    Column {
        Text("TopAppBar", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("Barra de aplicación superior")
        Spacer(modifier = Modifier.height(8.dp))

        Surface(elevation = 4.dp) {
            TopAppBar(
                title = { Text("Mi Aplicación") },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Search, contentDescription = "Buscar")
                    }
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Más")
                    }
                }
            )
        }
    }
}

@Composable
fun BottomNavigationExample() {
    Column {
        Text("BottomNavigation", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("Navegación inferior con pestañas")
        Spacer(modifier = Modifier.height(8.dp))

        var selectedTab by remember { mutableStateOf(0) }

        BottomNavigation {
            BottomNavigationItem(
                icon = { Icon(Icons.Default.Home, contentDescription = null) },
                label = { Text("Inicio") },
                selected = selectedTab == 0,
                onClick = { selectedTab = 0 }
            )
            BottomNavigationItem(
                icon = { Icon(Icons.Default.Search, contentDescription = null) },
                label = { Text("Buscar") },
                selected = selectedTab == 1,
                onClick = { selectedTab = 1 }
            )
            BottomNavigationItem(
                icon = { Icon(Icons.Default.Person, contentDescription = null) },
                label = { Text("Perfil") },
                selected = selectedTab == 2,
                onClick = { selectedTab = 2 }
            )
        }
    }
}

@Composable
fun DialogExample() {
    Column {
        Text("Dialog", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("Diálogo personalizable")
        Spacer(modifier = Modifier.height(8.dp))

        var showDialog by remember { mutableStateOf(false) }

        Button(onClick = { showDialog = true }) {
            Text("Mostrar Diálogo Personalizado")
        }

        if (showDialog) {
            Dialog(onDismissRequest = { showDialog = false }) {
                Card(
                    modifier = Modifier.padding(16.dp),
                    elevation = 8.dp
                ) {
                    Column(modifier = Modifier.padding(24.dp)) {
                        Icon(
                            Icons.Default.Info,
                            contentDescription = null,
                            modifier = Modifier.size(48.dp),
                            tint = MaterialTheme.colors.primary
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("Diálogo Personalizado", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Este es un diálogo con contenido personalizado y un icono.")
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            TextButton(onClick = { showDialog = false }) {
                                Text("Cerrar")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DividerExample() {
    Column {
        Text("Divider", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("Separador visual entre elementos")
        Spacer(modifier = Modifier.height(8.dp))

        Card(elevation = 4.dp) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Elemento 1")
                Divider(modifier = Modifier.padding(vertical = 8.dp))

                Text("Elemento 2")
                Divider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    thickness = 2.dp,
                    color = MaterialTheme.colors.primary
                )

                Text("Elemento 3")
                Divider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    thickness = 1.dp,
                    color = Color.Red
                )

                Text("Elemento 4")
            }
        }
    }
}

@Composable
fun DropDownMenuExample() {
    Column {
        Text("DropDownMenu", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("Menu desplegable con opciones")
        Spacer(modifier = Modifier.height(8.dp))

        var expanded by remember { mutableStateOf(false) }
        var selectedOption by remember { mutableStateOf("Selecciona una opción") }
        val options = listOf("Opción 1", "Opción 2", "Opción 3", "Opción 4")

        Box {
            Button(onClick = { expanded = true }) {
                Text(selectedOption)
                Spacer(modifier = Modifier.width(8.dp))
                Icon(Icons.Default.ArrowDropDown, contentDescription = null)
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        onClick = {
                            selectedOption = option
                            expanded = false
                        }
                    ) {
                        Text(option)
                    }
                }
            }
        }
    }
}

@Composable
fun LazyVerticalGridExample() {
    Column {
        Text("LazyVerticalGrid", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("Grid vertical con scroll eficiente")
        Spacer(modifier = Modifier.height(8.dp))

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 80.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.height(200.dp)
        ) {
            items(40) { index ->
                Card(
                    modifier = Modifier.aspectRatio(1f),
                    elevation = 2.dp,
                    backgroundColor = when (index % 4) {
                        0 -> Color.Red.copy(alpha = 0.3f)
                        1 -> Color.Green.copy(alpha = 0.3f)
                        2 -> Color.Blue.copy(alpha = 0.3f)
                        else -> Color.Yellow.copy(alpha = 0.3f)
                    }
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("$index", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun NavigationRailExample() {
    Column {
        Text("NavigationRail", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("Navegación lateral para pantallas más amplias")
        Spacer(modifier = Modifier.height(8.dp))

        var selectedItem by remember { mutableStateOf(0) }

        Surface(elevation = 4.dp) {
            NavigationRail(
                modifier = Modifier.width(80.dp)
            ) {
                NavigationRailItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = null) },
                    label = { Text("Inicio") },
                    selected = selectedItem == 0,
                    onClick = { selectedItem = 0 }
                )
                NavigationRailItem(
                    icon = { Icon(Icons.Default.Search, contentDescription = null) },
                    label = { Text("Buscar") },
                    selected = selectedItem == 1,
                    onClick = { selectedItem = 1 }
                )
                NavigationRailItem(
                    icon = { Icon(Icons.Default.Settings, contentDescription = null) },
                    label = { Text("Config") },
                    selected = selectedItem == 2,
                    onClick = { selectedItem = 2 }
                )
            }
        }
    }
}

@Composable
fun OutlinedTextFieldExample() {
    Column {
        Text("OutlinedTextField", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("Campo de texto con borde definido")
        Spacer(modifier = Modifier.height(8.dp))

        var text1 by remember { mutableStateOf("") }
        var text2 by remember { mutableStateOf("Texto con valor inicial") }
        var password by remember { mutableStateOf("") }

        OutlinedTextField(
            value = text1,
            onValueChange = { text1 = it },
            label = { Text("Campo básico") },
            placeholder = { Text("Escribe algo...") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = text2,
            onValueChange = { text2 = it },
            label = { Text("Con valor inicial") },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
            trailingIcon = { Icon(Icons.Default.Visibility, contentDescription = null) },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PagerExample() {
    Column {
        Text("Pager", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("Navegación horizontal por páginas")
        Spacer(modifier = Modifier.height(8.dp))

        val pagerState = rememberPagerState()
        val pages = listOf("Página 1", "Página 2", "Página 3", "Página 4")

        HorizontalPager(
            count = pages.size,
            state = pagerState,
            modifier = Modifier.height(200.dp)
        ) { page ->
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                elevation = 4.dp
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            when (page) {
                                0 -> Icons.Default.Home
                                1 -> Icons.Default.Star
                                2 -> Icons.Default.Favorite
                                else -> Icons.Default.Settings
                            },
                            contentDescription = null,
                            modifier = Modifier.size(48.dp),
                            tint = MaterialTheme.colors.primary
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            pages[page],
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "Contenido de la ${pages[page].lowercase()}",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SnackbarExample() {
    Column {
        Text("Snackbar", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("Mensaje temporal en la parte inferior")
        Spacer(modifier = Modifier.height(8.dp))

        val scaffoldState = rememberScaffoldState()
        val scope = rememberCoroutineScope()

        Surface(elevation = 4.dp) {
            Scaffold(
                scaffoldState = scaffoldState,
                modifier = Modifier.height(200.dp)
            ) { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            kotlinx.coroutines.launch {
                                scaffoldState.snackbarHostState.showSnackbar("Mensaje simple")
                            }
                        }
                    ) {
                        Text("Snackbar Simple")
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = {
                            kotlinx.coroutines.launch {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Mensaje con acción",
                                    actionLabel = "Deshacer"
                                )
                            }
                        }
                    ) {
                        Text("Snackbar con Acción")
                    }
                }
            }
        }
    }
}

@Composable
fun TabRowExample() {
    Column {
        Text("TabRow", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("Pestañas horizontales para navegación")
        Spacer(modifier = Modifier.height(8.dp))

        var selectedTabIndex by remember { mutableStateOf(0) }
        val tabs = listOf("Tab 1", "Tab 2", "Tab 3")

        Column {
            TabRow(selectedTabIndex = selectedTabIndex) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(title) }
                    )
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                elevation = 4.dp
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            when (selectedTabIndex) {
                                0 -> Icons.Default.Home
                                1 -> Icons.Default.Star
                                else -> Icons.Default.Settings
                            },
                            contentDescription = null,
                            modifier = Modifier.size(32.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Contenido de ${tabs[selectedTabIndex]}")
                    }
                }
            }
        }
    }
}

@Composable
fun TooltipExample() {
    Column {
        Text("Tooltip", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("Información emergente al hacer hover")
        Spacer(modifier = Modifier.height(8.dp))

        // En Compose, los tooltips se implementan típicamente con estados personalizados
        var showTooltip1 by remember { mutableStateOf(false) }
        var showTooltip2 by remember { mutableStateOf(false) }

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Box {
                IconButton(
                    onClick = { showTooltip1 = !showTooltip1 }
                ) {
                    Icon(Icons.Default.Info, contentDescription = "Información")
                }

                if (showTooltip1) {
                    Card(
                        modifier = Modifier.offset(y = (-40).dp),
                        elevation = 8.dp
                    ) {
                        Text(
                            "Este es un tooltip",
                            modifier = Modifier.padding(8.dp),
                            fontSize = 12.sp
                        )
                    }
                }
            }

            Box {
                Button(
                    onClick = { showTooltip2 = !showTooltip2 }
                ) {
                    Text("Hover me")
                }

                if (showTooltip2) {
                    Card(
                        modifier = Modifier.offset(y = (-50).dp),
                        elevation = 8.dp,
                        backgroundColor = Color.Black.copy(alpha = 0.8f)
                    ) {
                        Text(
                            "Tooltip personalizado",
                            modifier = Modifier.padding(12.dp),
                            fontSize = 12.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "Nota: En móviles, los tooltips se muestran al tocar los elementos",
            fontSize = 12.sp,
            color = Color.Gray
        )
    }
}