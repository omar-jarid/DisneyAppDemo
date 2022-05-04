package omarjarid.disneyappdemo.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import omarjarid.disneyappdemo.presentation.viewmodels.PersonaggioViewModel
import omarjarid.domain.model.Personaggio

// Punto di navigazione.
@Composable
fun DisneyNavHost(
    lista: List<Personaggio>,
    textState: MutableState<String>,
    viewModel: PersonaggioViewModel,
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            BodyContent(
                lista = lista,
                textState = textState,
                viewModel = viewModel,
                navController = navController
            )
        }

        composable(
            route = "personaggio/{id}",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.getInt("id")?.let { id ->
                MimickInstagramDetail(
                    personaggio = lista.first { it._id == id },
                    navController = navController
                )
            }
        }
    }
}

// Composable principale.
@Composable
fun DisneyAppCompose(
    lista: List<Personaggio>,
    textState: MutableState<String>,
    viewModel: PersonaggioViewModel,
    navController: NavHostController
) {
    Scaffold { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            CircularProgressBar(isDisplayed = lista.isEmpty())
            DisneyNavHost(
                lista = lista,
                textState = textState,
                viewModel = viewModel,
                navController = navController
            )
        }
    }
}