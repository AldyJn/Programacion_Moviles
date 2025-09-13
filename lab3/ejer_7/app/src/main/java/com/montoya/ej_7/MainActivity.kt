package com.montoya.ej_7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.montoya.ej_7.ui.theme.Ej_7Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

        }
    }
}

@Composable
fun ExampleLazyColumn() {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(8.dp)) {
        items(5) { index ->
            Text("Item $index", modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun ExampleLazyRow() {
    LazyRow(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        items(5) { index ->
            Card(modifier = Modifier.size(100.dp).padding(4.dp)) {
                Box(contentAlignment = Alignment.Center) { Text("Item $index") }
            }
        }
    }
}
@Composable
fun ExampleGrid() {
    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.padding(8.dp)) {
        item (6){ index ->
            Card { modifier = Modifier.size() }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Ej_7Theme {

    }
}