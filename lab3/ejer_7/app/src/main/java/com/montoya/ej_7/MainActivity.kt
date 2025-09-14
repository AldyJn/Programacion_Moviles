package com.montoya.ej_7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.montoya.ej_7.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                ComponentTestScreen()
            }
        }
    }
}

@Composable
fun ComponentTestScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Prueba de Componentes",
            style = MaterialTheme.typography.headlineLarge
        )

        // Descomenta el componente que quieras probar:

        // ConstraintLayout (requiere context, usar ConstraintLayoutExample(this@MainActivity))
        // AndroidView(factory = { ConstraintLayoutExample(it) })

        // LazyColumn
         //LazyColumnExample()

        // LazyRow
        // LazyRowExample()

        // Grid
         //GridExample()

        // Scaffold
        // ScaffoldExample()

        // Surface
        // SurfaceExample()

        // Chip
        // ChipExample()

        // BottomSheetScaffold
        //BottomSheetScaffoldExample()

        // BackdropScaffold
        // BackdropScaffoldExample()

        // FlowRow
        // FlowRowExample()

        // FlowColumn
        // FlowColumnExample()

        // AlertDialog
        // AlertDialogExample()

        // Card
        //CardExample()

        // Checkbox
        // CheckboxExample()

        // FloatingActionButton
        // FloatingActionButtonExample()

        // Icon
         //IconExample()

        // Image
        // ImageExample()

        // ProgressBar
        // ProgressBarExample()

        // RadioButton
        // RadioButtonExample()

        // Slider
        // SliderExample()

        // Spacer
        // SpacerExample()

        // Switch
        // SwitchExample()

        // TopAppBar
        // TopAppBarExample()

        // BottomNavigation
        // BottomNavigationExample()

        // Dialog
        // DialogExample()

        // Divider
        // DividerExample()

        // DropdownMenu
         DropdownMenuExample()

        // LazyVerticalGrid
        // LazyVerticalGridExample()

        // NavigationRail
        // NavigationRailExample()

        // OutlinedTextField
        // OutlinedTextFieldExample()

        // Pager
        // PagerExample()

        // Snackbar
        // SnackbarExample()

        // TabRow
        // TabRowExample()

        // Tooltip
        // TooltipExample()

        // Ejemplo básico para empezar - descomenta para ver algo funcionando
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "¡Componentes listos para probar!",
                    style = MaterialTheme.typography.headlineSmall
                )
                Text("Descomenta cualquier componente de arriba para probarlo.")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        ComponentTestScreen()
    }
}