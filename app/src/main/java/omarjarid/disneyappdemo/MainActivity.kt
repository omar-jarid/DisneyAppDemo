package omarjarid.disneyappdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import omarjarid.disneyappdemo.presentation.viewmodels.PersonaggioViewModel
import omarjarid.disneyappdemo.ui.composables.DisneyAppCompose
import omarjarid.disneyappdemo.ui.theme.DisneyAppDemoTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: PersonaggioViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadPersonaggi()
        setContent {
            val personaggi = viewModel.personaggi.collectAsState()
            DisneyAppDemoTheme {
                DisneyAppCompose(
                    lista = personaggi.value,
                    textState = remember { mutableStateOf("") },
                    viewModel = viewModel,
                    navController = rememberNavController()
                )
            }
        }
    }
}