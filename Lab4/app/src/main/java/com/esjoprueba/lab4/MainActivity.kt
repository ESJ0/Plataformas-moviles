package com.esjoprueba.lab4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.esjoprueba.lab4.ui.theme.Lab4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab4Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Portada()
                }
            }
        }
    }
}

@Composable
fun Portada() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .border(
                BorderStroke(6.dp, Color(0xFF006400)),
                shape = RoundedCornerShape(0.dp)
            )
            .padding(40.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            // Título
            Text(
                text = "Universidad del Valle de Guatemala",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                textAlign = TextAlign.Center
            )

            // Subtítulo
            Text(
                text = "Programación de plataformas móviles, Sección 30",
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )

            // Integrantes
            Row(
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = "INTEGRANTES",
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(20.dp))
                Column {
                    Text("Jose Abril")
                    Text("Josue Garcia")
                    Text("Lionel Messi")
                }
            }

            // Catedrático
            Row(
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = "CATEDRÁTICO",
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text("Juan Carlos Durini")
            }


            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Jose Abril")
                Text("24585")
            }
        }
    }
}
