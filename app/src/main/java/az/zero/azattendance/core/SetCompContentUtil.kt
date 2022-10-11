package az.zero.azattendance.core

import android.content.Context
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import az.zero.azattendance.presentation.theme.AZAttendanceTheme


fun setCompContent(context: Context, content: @Composable () -> Unit): View {
    return ComposeView(context).apply {
        setContent {
            AZAttendanceTheme {
                content()
            }
        }
    }
}