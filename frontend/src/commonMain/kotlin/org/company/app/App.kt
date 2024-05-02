package org.company.app

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.company.app.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HorizontallyCenteredColumn(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Center,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(modifier, verticalArrangement, horizontalAlignment = Alignment.CenterHorizontally, content)
}

@Composable
fun HomeScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        HorizontallyCenteredColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.align(Alignment.Center)
        ) {
            UserLoginFormScreen()
        }
    }
}

@Preview
@Composable
fun UserLoginFormScreen() {
    HorizontallyCenteredColumn(verticalArrangement = Arrangement.spacedBy(20.dp)) {
        Text("Login")
        var login by remember { mutableStateOf("") }
        var pass by remember { mutableStateOf("") }
        TextField(login, { login = it })
        TextField(pass, { pass = it })
        Button({ }) {
            Text("Authorize")
        }
    }
}

@Composable
fun AdminLoginFormScreen() {

}

@Composable
internal fun App() = AppTheme {
    HomeScreen()
}

internal expect fun openUrl(url: String?)