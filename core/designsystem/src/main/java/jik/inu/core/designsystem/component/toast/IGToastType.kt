package jik.inu.core.designsystem.component.toast

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Error
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

enum class ToastType(
    val icon: ImageVector,
    val iconColor: Color,
    val strokeColor: Color,
    val backgroundColor: Color,
    val contentDescription: String = this.toString()
) {
    SUCCESS(
        icon = Icons.Rounded.CheckCircle,
        iconColor = Color(0xFF00B800),
        strokeColor = Color(0xFF78D361),
        backgroundColor = Color(0xFFFEFFFE),
    ),
    ERROR(
        icon = Icons.Rounded.AddCircle,
        iconColor = Color(0xFFFF0000),
        strokeColor = Color(0xFFFF8181),
        backgroundColor = Color(0xFFFFFEFE)
    ),
    WARNING(
        icon = Icons.Rounded.Error,
        iconColor = Color(0xFFFFA800),
        strokeColor = Color(0xFFFFC581),
        backgroundColor = Color(0xFFFFFFFE)
    )
}