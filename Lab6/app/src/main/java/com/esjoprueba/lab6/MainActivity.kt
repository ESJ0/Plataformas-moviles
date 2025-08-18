package com.esjoprueba.lab6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.esjoprueba.lab6.ui.theme.Lab6Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab6Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ContadorApp(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ContadorApp(modifier: Modifier = Modifier) {
    // Estados
    var contador by remember { mutableStateOf(0) }
    var incrementos by remember { mutableStateOf(0) }
    var decrementos by remember { mutableStateOf(0) }
    var cambios by remember { mutableStateOf(0) }
    var maximo by remember { mutableStateOf(0) }
    var minimo by remember { mutableStateOf(0) }

    var historial by remember { mutableStateOf(listOf<Pair<Int, Boolean>>()) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Título
        Text(
            text = "Jose Abril",
            fontSize = 28.sp,
            fontWeight = FontWeight.Light,
            color = Color(0xFF2C3E50),
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Contador principal
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
                .shadow(8.dp, RoundedCornerShape(20.dp)),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                // Botón decrementar
                Button(
                    onClick = {
                        contador--
                        decrementos++
                        cambios++
                        if (contador < minimo) minimo = contador
                        historial = historial + (contador to false)
                    },
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFE74C3C)
                    )
                ) {
                    Text(text = "−", fontSize = 28.sp, color = Color.White)
                }

                // Valor del contador
                Text(
                    text = contador.toString(),
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2C3E50),
                    modifier = Modifier.padding(horizontal = 32.dp)
                )

                // Botón incrementar
                Button(
                    onClick = {
                        contador++
                        incrementos++
                        cambios++
                        if (contador > maximo) maximo = contador
                        historial = historial + (contador to true)
                    },
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF27AE60)
                    )
                ) {
                    Text(text = "+", fontSize = 28.sp, color = Color.White)
                }
            }
        }

        // Estadísticas
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .shadow(4.dp, RoundedCornerShape(16.dp)),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    "Estadísticas",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF2C3E50),
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    StatItem("Incrementos", incrementos, Color(0xFF2C3E50))
                    StatItem("Decrementos", decrementos, Color(0xFF2C3E50))
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    StatItem("Máximo", maximo, Color(0xFF2C3E50))
                    StatItem("Mínimo", minimo, Color(0xFF2C3E50))
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    StatItem("Total cambios", cambios, Color(0xFF2C3E50))
                }
            }
        }

        // Historial
        Text(
            "Historial",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF2C3E50),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 12.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(6),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentPadding = PaddingValues(4.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(historial) { (valor, incremento) ->
                Box(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            if (incremento) Color(0xFF27AE60) else Color(0xFFE74C3C)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = valor.toString(),
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        // Botón Reiniciar
        Button(
            onClick = {
                contador = 0
                incrementos = 0
                decrementos = 0
                cambios = 0
                maximo = 0
                minimo = 0
                historial = emptyList()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF34495E)
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                "Reiniciar",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
        }
    }
}

@Composable
fun StatItem(label: String, value: Int, color: Color) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value.toString(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = color
        )
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color(0xFF7F8C8D)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ContadorAppPreview() {
    Lab6Theme {
        ContadorApp()
    }
}