package com.montoya.happy_birthday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.montoya.happy_birthday.ui.theme.Happy_BirthdayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BirthdayCard("Pasala bien, no sere el mejor hermano pero solo te recuerdo que siempre te querre","Hermanita")

        }
    }
}

@Composable
fun BirthdayCard(mensaje: String, para: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9F9F9)),
        contentAlignment = Alignment.Center
    ){
        Card(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxSize()
                .border(
                    width = 2.dp,
                    color = Color(0xFFFFC0CB),
                    shape = RoundedCornerShape(24.dp)
                ),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ){
            Column(
                modifier = Modifier
                    .padding(32.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = mensaje,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Cursive,
                    color = Color(0xFFFFB6C1),
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp)
                )
                Image(
                    painter = painterResource(R.drawable.happy_birthday),
                    contentDescription = "oño",
                    modifier = Modifier
                        .size(500.dp)
                        .padding(bottom = 10.dp)
                )
                Text(
                    text = para,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                    fontFamily = FontFamily.Monospace,
                    color = Color.Gray
                )



            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Happy_BirthdayTheme {
        BirthdayCard("Pasala bien, cumple todas tus metas y sueños comparte con tu familia y amigos y que te valla bien en la vida","Aldy")
    }
}