package omarjarid.disneyappdemo.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import omarjarid.disneyappdemo.R

@Composable
fun CircularProgressBar(isDisplayed: Boolean) {
    if(isDisplayed) {
        Box {
            CircularProgressIndicator(
                color = MaterialTheme.colors.primary,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(50.dp)
            )

            Column {
                Spacer(modifier = Modifier.height(24.dp))
                Row {
                    Spacer(modifier = Modifier.width(10.dp))
                    Image(
                        painter = if(isSystemInDarkTheme()) {
                            painterResource(id = R.drawable.ic_mickey_logo_bright)
                        } else {
                            painterResource(id = R.drawable.ic_mickey_logo)
                        },
                        contentDescription = null,
                        modifier = Modifier.scale(0.5f)
                    )
                }
            }
        }
    }
}