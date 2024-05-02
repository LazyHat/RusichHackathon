package org.company.app

import androidx.compose.runtime.Composable
import org.company.app.theme.AppTheme

@Composable
internal fun App() = AppTheme {

}

internal expect fun openUrl(url: String?)