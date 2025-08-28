package com.esjoprueba.lab7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.esjoprueba.lab7.ui.theme.Lab7Theme

// Modelo de datos
data class Notification(
    val id: Int,
    val title: String,
    val message: String,
    val type: NotificationType,
    val timestamp: String
)

enum class NotificationType {
    INFORMATIVAS,
    CAPACITACIONES
}

val NotificationType.displayName: String
    get() = when (this) {
        NotificationType.INFORMATIVAS -> "Informativas"
        NotificationType.CAPACITACIONES -> "Capacitaciones"
    }

// Función para generar notificaciones fake
fun generateFakeNotifications(): List<Notification> {
    return listOf(
        Notification(1, "Nueva versión disponible", "La aplicación ha sido actualizada a v1.1.0. Ve a la PlayStore y actualízala!", NotificationType.INFORMATIVAS, "19 ago • 2:30 p. m."),
        Notification(2, "Nueva capacitación", "El día Martes 21 de Agosto tendremos una nueva capacitación en el INTECAP, no faltes!", NotificationType.CAPACITACIONES, "15 ago • 3:00 p. m."),
        Notification(3, "¡Mañana capacitación de ICTA F", "No te olvides de asistir a esta capacitación mañana, a las 6pm, en el Intecap.", NotificationType.CAPACITACIONES, "05 ago • 11:30 a. m."),
        Notification(4, "Nueva versión disponible", "La aplicación ha sido actualizada a v1.0.2. Ve a la PlayStore y actualízala!", NotificationType.INFORMATIVAS, "19 jul • 2:30 p. m."),
        Notification(5, "Capacitación de Marketing Digital", "Únete a nuestra capacitación sobre marketing digital este viernes a las 4:00 p.m.", NotificationType.CAPACITACIONES, "12 ago • 9:15 a. m."),
        Notification(6, "Mantenimiento programado", "El sistema estará en mantenimiento el domingo de 2:00 a 6:00 a.m.", NotificationType.INFORMATIVAS, "10 ago • 5:45 p. m."),
        Notification(7, "Taller de Emprendimiento", "Participa en nuestro taller de emprendimiento este sábado en las instalaciones del INTECAP.", NotificationType.CAPACITACIONES, "08 ago • 1:20 p. m."),
        Notification(8, "Actualización de términos", "Hemos actualizado nuestros términos y condiciones. Revísalos en la configuración.", NotificationType.INFORMATIVAS, "07 ago • 10:30 a. m."),
        Notification(9, "Curso de Excel Avanzado", "Inscríbete al curso de Excel Avanzado que inicia el próximo lunes.", NotificationType.CAPACITACIONES, "06 ago • 3:45 p. m."),
        Notification(10, "Problema resuelto", "El problema con el inicio de sesión ha sido solucionado. Gracias por tu paciencia.", NotificationType.INFORMATIVAS, "05 ago • 8:20 a. m."),
        Notification(11, "Seminario de Liderazgo", "Te invitamos al seminario de liderazgo empresarial este jueves a las 2:00 p.m.", NotificationType.CAPACITACIONES, "04 ago • 4:10 p. m."),
        Notification(12, "Nueva función disponible", "Ahora puedes exportar tus datos desde la sección de reportes.", NotificationType.INFORMATIVAS, "03 ago • 11:55 a. m."),
        Notification(13, "Capacitación en Contabilidad", "Curso básico de contabilidad disponible. Inscripciones abiertas hasta el viernes.", NotificationType.CAPACITACIONES, "02 ago • 7:30 a. m."),
        Notification(14, "Respaldo completado", "El respaldo de tus datos se ha completado exitosamente.", NotificationType.INFORMATIVAS, "01 ago • 6:15 p. m."),
        Notification(15, "Taller de Innovación", "Participa en el taller de innovación tecnológica el próximo miércoles.", NotificationType.CAPACITACIONES, "31 jul • 2:45 p. m."),
        Notification(16, "Actualización de seguridad", "Se ha implementado una nueva capa de seguridad en la aplicación.", NotificationType.INFORMATIVAS, "30 jul • 9:30 a. m."),
        Notification(17, "Curso de Fotografía Digital", "Aprende técnicas de fotografía digital en nuestro nuevo curso.", NotificationType.CAPACITACIONES, "29 jul • 5:20 p. m."),
        Notification(18, "Cambio en horarios", "Los horarios de atención han cambiado. Consulta los nuevos horarios en la app.", NotificationType.INFORMATIVAS, "28 jul • 12:40 p. m."),
        Notification(19, "Workshop de Design Thinking", "Metodología de Design Thinking aplicada a tu negocio. Inscríbete ya.", NotificationType.CAPACITACIONES, "27 jul • 3:25 p. m."),
        Notification(20, "Nuevo centro de ayuda", "Hemos lanzado nuestro nuevo centro de ayuda con más recursos.", NotificationType.INFORMATIVAS, "26 jul • 10:10 a. m."),
        Notification(21, "Capacitación en Ventas", "Técnicas efectivas de ventas para potenciar tu negocio.", NotificationType.CAPACITACIONES, "25 jul • 4:50 p. m."),
        Notification(22, "Mejoras en rendimiento", "La aplicación ahora es 30% más rápida gracias a las optimizaciones.", NotificationType.INFORMATIVAS, "24 jul • 1:35 p. m."),
        Notification(23, "Curso de Redes Sociales", "Maximiza tu presencia en redes sociales con nuestro curso especializado.", NotificationType.CAPACITACIONES, "23 jul • 8:45 a. m."),
        Notification(24, "Política de privacidad", "Hemos actualizado nuestra política de privacidad. Léela aquí.", NotificationType.INFORMATIVAS, "22 jul • 7:20 p. m."),
        Notification(25, "Taller de Gestión Financiera", "Aprende a gestionar las finanzas de tu empresa de manera eficiente.", NotificationType.CAPACITACIONES, "21 jul • 11:15 a. m."),
        Notification(26, "Integración con Google", "Ahora puedes sincronizar tu cuenta de Google con la aplicación.", NotificationType.INFORMATIVAS, "20 jul • 2:30 p. m."),
        Notification(27, "Seminario de E-commerce", "Cómo crear y gestionar tu tienda online exitosamente.", NotificationType.CAPACITACIONES, "19 jul • 6:40 p. m."),
        Notification(28, "Notificaciones mejoradas", "El sistema de notificaciones ahora es más intuitivo y personalizable.", NotificationType.INFORMATIVAS, "18 jul • 9:25 a. m."),
        Notification(29, "Curso de Recursos Humanos", "Gestión eficiente de recursos humanos para pequeñas empresas.", NotificationType.CAPACITACIONES, "17 jul • 4:15 p. m."),
        Notification(30, "Nueva interfaz", "Disfruta de nuestra nueva interfaz más moderna y fácil de usar.", NotificationType.INFORMATIVAS, "16 jul • 12:50 p. m."),
        Notification(31, "Capacitación en Logística", "Optimiza la cadena de suministro de tu negocio.", NotificationType.CAPACITACIONES, "15 jul • 8:30 a. m."),
        Notification(32, "Corrección de errores", "Se han corregido varios errores menores para mejorar tu experiencia.", NotificationType.INFORMATIVAS, "14 jul • 5:45 p. m."),
        Notification(33, "Workshop de Productividad", "Técnicas y herramientas para aumentar la productividad personal y empresarial.", NotificationType.CAPACITACIONES, "13 jul • 3:20 p. m."),
        Notification(34, "Modo oscuro disponible", "Ahora puedes activar el modo oscuro en la configuración de la app.", NotificationType.INFORMATIVAS, "12 jul • 10:35 a. m."),
        Notification(35, "Curso de Análisis de Datos", "Interpreta y utiliza los datos para tomar mejores decisiones de negocio.", NotificationType.CAPACITACIONES, "11 jul • 7:10 p. m."),
        Notification(36, "Exportación mejorada", "La función de exportar reportes ahora incluye más formatos.", NotificationType.INFORMATIVAS, "10 jul • 1:25 p. m."),
        Notification(37, "Taller de Comunicación", "Mejora tus habilidades de comunicación efectiva en el ambiente laboral.", NotificationType.CAPACITACIONES, "09 jul • 9:50 a. m."),
        Notification(38, "Accesibilidad mejorada", "La aplicación ahora es más accesible para usuarios con discapacidades.", NotificationType.INFORMATIVAS, "08 jul • 4:40 p. m."),
        Notification(39, "Capacitación en Calidad", "Implementación de sistemas de gestión de calidad ISO 9001.", NotificationType.CAPACITACIONES, "07 jul • 11:30 a. m."),
        Notification(40, "Optimización móvil", "La versión móvil ha sido optimizada para mejor rendimiento.", NotificationType.INFORMATIVAS, "06 jul • 6:20 p. m."),
        Notification(41, "Curso de Negociación", "Técnicas avanzadas de negociación para cerrar mejores acuerdos.", NotificationType.CAPACITACIONES, "05 jul • 2:15 p. m."),
        Notification(42, "Nuevas métricas", "Accede a nuevas métricas y estadísticas en el panel de control.", NotificationType.INFORMATIVAS, "04 jul • 8:55 a. m."),
        Notification(43, "Seminario de Sostenibilidad", "Implementa prácticas sostenibles en tu modelo de negocio.", NotificationType.CAPACITACIONES, "03 jul • 5:30 p. m."),
        Notification(44, "Soporte 24/7", "Nuestro equipo de soporte ahora está disponible las 24 horas.", NotificationType.INFORMATIVAS, "02 jul • 12:45 p. m."),
        Notification(45, "Taller de Inteligencia Emocional", "Desarrolla tu inteligencia emocional para un mejor liderazgo.", NotificationType.CAPACITACIONES, "01 jul • 10:20 a. m."),
        Notification(46, "API actualizada", "La API ha sido actualizada a la versión 2.0 con nuevas funcionalidades.", NotificationType.INFORMATIVAS, "30 jun • 3:35 p. m."),
        Notification(47, "Curso de Transformación Digital", "Adapta tu empresa a la era digital con nuestro curso especializado.", NotificationType.CAPACITACIONES, "29 jun • 7:45 a. m."),
        Notification(48, "Backup automático", "El backup automático de datos ahora se ejecuta semanalmente.", NotificationType.INFORMATIVAS, "28 jun • 4:25 p. m."),
        Notification(49, "Workshop de Creatividad", "Potencia la creatividad e innovación en tu equipo de trabajo.", NotificationType.CAPACITACIONES, "27 jun • 11:40 a. m."),
        Notification(50, "Versión estable", "La versión 1.2.0 estable ya está disponible para descarga.", NotificationType.INFORMATIVAS, "26 jun • 6:30 p. m.")
    )
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab7Theme {
                NotificationsScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsScreen(
    onNavigateBack: () -> Unit = {}
) {
    val notifications = remember { generateFakeNotifications() }
    var selectedFilter by remember { mutableStateOf<NotificationType?>(null) }

    val filteredNotifications by remember(selectedFilter) {
        derivedStateOf {
            if (selectedFilter == null) {
                notifications
            } else {
                notifications.filter { it.type == selectedFilter }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Top Bar
        TopAppBar(
            title = {
                Text(
                    text = "Notificaciones",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            },
            navigationIcon = {
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Volver",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF4CAF50)
            )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Filtros Section
            FilterSection(
                selectedFilter = selectedFilter,
                onFilterSelected = { filter ->
                    selectedFilter = if (selectedFilter == filter) null else filter
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Notificaciones List
            NotificationsList(
                notifications = filteredNotifications,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun FilterSection(
    selectedFilter: NotificationType?,
    onFilterSelected: (NotificationType) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "Tipos de notificaciones",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterChip(
                type = NotificationType.INFORMATIVAS,
                isSelected = selectedFilter == NotificationType.INFORMATIVAS,
                onClick = { onFilterSelected(NotificationType.INFORMATIVAS) }
            )

            FilterChip(
                type = NotificationType.CAPACITACIONES,
                isSelected = selectedFilter == NotificationType.CAPACITACIONES,
                onClick = { onFilterSelected(NotificationType.CAPACITACIONES) }
            )
        }
    }
}

@Composable
fun FilterChip(
    type: NotificationType,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val containerColor = if (isSelected) {
        Color(0xFF4CAF50)
    } else {
        MaterialTheme.colorScheme.surface
    }

    val contentColor = if (isSelected) {
        Color.White
    } else {
        MaterialTheme.colorScheme.onSurface
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(containerColor)
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = type.displayName,
            color = contentColor,
            fontSize = 14.sp,
            fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal
        )
    }
}

@Composable
fun NotificationsList(
    notifications: List<Notification>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(notifications) { notification ->
            NotificationItem(
                notification = notification,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun NotificationItem(
    notification: Notification,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF9FBE7)
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Icon
            NotificationIcon(
                type = notification.type,
                modifier = Modifier.size(40.dp)
            )

            // Content
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = notification.title,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )

                    Text(
                        text = notification.timestamp,
                        fontSize = 12.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = notification.message,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 18.sp,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun NotificationIcon(
    type: NotificationType,
    modifier: Modifier = Modifier
) {
    val (backgroundColor, icon) = when (type) {
        NotificationType.INFORMATIVAS -> {
            Color(0xFFFFF59D) to Icons.Default.Notifications
        }
        NotificationType.CAPACITACIONES -> {
            Color(0xFF80DEEA) to Icons.Default.DateRange
        }
    }

    val iconColor = Color.Black

    Box(
        modifier = modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNotifications() {
    Lab7Theme {
        NotificationsScreen()
    }
}