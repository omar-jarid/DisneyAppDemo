package omarjarid.disneyappdemo.ui.composables

import android.app.Person
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.skydoves.landscapist.glide.GlideImage
import omarjarid.disneyappdemo.R
import omarjarid.disneyappdemo.presentation.viewmodels.PersonaggioViewModel
import omarjarid.disneyappdemo.ui.theme.Disney2
import omarjarid.disneyappdemo.ui.theme.UpsdellRed
import omarjarid.domain.model.Personaggio

@Composable
fun PersonaggioCard(personaggio: Personaggio, navController: NavHostController) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable(
                onClick = {
                    navigateTo(
                        route = "personaggio/${personaggio._id}",
                        navController = navController
                    )
                }
            ),
        elevation = 2.dp,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f, fill = true), 
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                GlideImage(
                    imageModel = personaggio.imageUrl,
                    placeHolder = painterResource(id = R.drawable.ic_mickey_logo_bright),
                    error = painterResource(id = R.drawable.ic_mickey_logo_bright),
                    contentScale = ContentScale.Crop,
                    contentDescription = personaggio.name,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(100.dp)
                        .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
                )

                Text(
                    text = personaggio.name,
                    style = typography.h6,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
        }
    }
}

@Composable
fun SearchBar(state: MutableState<String>, onSearch: (String) -> Unit, isDisplayed: Boolean) {
    if(isDisplayed) {
        val focusManager = LocalFocusManager.current
        TextField(
            value = state.value,
            onValueChange = {state.value = it},
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            maxLines = 1,
            leadingIcon = { Icon(imageVector = Icons.Filled.Search, contentDescription = "Search") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearch(state.value)
                    focusManager.clearFocus()
                }
            )
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BodyContent(
    lista: List<Personaggio>,
    textState: MutableState<String>,
    viewModel: PersonaggioViewModel,
    navController: NavHostController
) {
    Column {
        SearchBar(
            state = textState,
            onSearch = { viewModel.search(it) },
            isDisplayed = lista.isNotEmpty()
        )

        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            content = {
                items(lista.size) { index ->
                    PersonaggioCard(personaggio = lista[index], navController = navController)
                }
            }
        )
    }
}

/**** DETTAGLIO DEL PERSONAGGIO ***/
@Composable
fun MediaList(lista: List<String>, categoria: String) {
    Column(Modifier.padding(start = 16.dp)) {
        Text(
            text = "${stringResource(id = R.string.appears_in_these)} $categoria: ",
            fontSize = 18.sp
        )

        lista.forEach {
            ConstraintLayout {
                val (bullet, tvMedium) = createRefs()
                Image(
                    painter = if(isSystemInDarkTheme()) {
                        painterResource(id = R.drawable.ic_mickey_logo_bright)
                    } else {
                        painterResource(id = R.drawable.ic_mickey_logo)
                    },
                    contentDescription = "Bullet",
                    modifier = Modifier.constrainAs(bullet) {
                        top.linkTo(parent.top, margin = 4.dp)
                        start.linkTo(parent.start)
                        height = Dimension.value(18.dp)
                        width = Dimension.value(18.dp)
                    }
                )

                Text(
                    text = it,
                    fontSize = 18.sp,
                    modifier = Modifier.constrainAs(tvMedium) {
                        top.linkTo(parent.top)
                        start.linkTo(bullet.end, margin = 8.dp)
                        end.linkTo(parent.end, margin = 8.dp)
                    }
                )
            }
        }

        Spacer(modifier = Modifier.padding(8.dp))
    }
}

@Composable
fun TopBar(name: String, modifier: Modifier = Modifier, navController: NavHostController) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier.fillMaxWidth()) {
        IconButton(onClick = { navigateTo(route = "home", navController = navController) }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Main Screen")
        }

        Text(
            text = name,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
    }
}

@Composable
fun RoundImage(personaggio: Personaggio) {
    GlideImage(
        imageModel = personaggio.imageUrl,
        placeHolder = painterResource(id = R.drawable.ic_mickey_logo_bright),
        error = painterResource(id = R.drawable.ic_mickey_logo_bright),
        contentDescription = personaggio.name,
        modifier = Modifier
            .size(150.dp)
            .aspectRatio(ratio = 1f, matchHeightConstraintsFirst = true)
            .border(width = 1.dp, color = Color.LightGray, shape = CircleShape)
            .padding(3.dp)
            .clip(CircleShape)
    )
}

@Composable
fun ProfileDescription(personaggio: Personaggio) {
    val letterSpacing = 0.5.sp
    val lineHeight = 20.sp
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 20.dp)) {
        Text(
            text = personaggio.name,
            fontWeight = FontWeight.Bold,
            letterSpacing = letterSpacing,
            lineHeight = lineHeight
        )

        if(personaggio.alignment.isNotBlank()) {
            Text(
                text = "Alignment: ${personaggio.alignment}",
                color = when(personaggio.alignment) {
                    "Good" -> Disney2
                    "Bad" -> UpsdellRed
                    "Neutral" -> Color.LightGray
                    else -> Color.Black
                },
                letterSpacing = letterSpacing,
                lineHeight = lineHeight
            )
        }

        val sourceUrl = buildAnnotatedString {
            pushStringAnnotation(
                tag = "Link",
                annotation = personaggio.sourceUrl
            )

            withStyle(style = SpanStyle(color = MaterialTheme.colors.primary)) {
                append(personaggio.sourceUrl)
            }

            pop()
        }

        val uriHandler = LocalUriHandler.current
        ClickableText(
            text = sourceUrl,
            onClick = { offset ->
                sourceUrl.getStringAnnotations(
                    tag = "Link",
                    start = offset,
                    end = offset
                ).firstOrNull()?.let { uriHandler.openUri(it.item) }
            }
        )

        Spacer(modifier = Modifier.height(24.dp))
        if(personaggio.films.isNotEmpty()) {
            MediaList(lista = personaggio.films, categoria = "films")
        }

        if(personaggio.shortFilms.isNotEmpty()) {
            MediaList(lista = personaggio.shortFilms, categoria = "short films")
        }

        if(personaggio.tvShows.isNotEmpty()) {
            MediaList(lista = personaggio.tvShows, categoria = "TV shows")
        }

        if(personaggio.videoGames.isNotEmpty()) {
            MediaList(lista = personaggio.videoGames, categoria = "videogames")
        }
    }
}

@Composable
fun MediaStat(numberText: String, text: String, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(text = numberText, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = text)
    }
}

@Composable
fun MediaCounter(personaggio: Personaggio, modifier: Modifier = Modifier) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = modifier
        ) {
            MediaStat(numberText = "${personaggio.films.size}", text = "Films")
            Spacer(modifier = Modifier.width(8.dp))
            MediaStat(numberText = "${personaggio.shortFilms.size}", text = "Shorts")
            Spacer(modifier = Modifier.width(8.dp))
            MediaStat(numberText = "${personaggio.tvShows.size}", text = "Shows")
            Spacer(modifier = Modifier.width(8.dp))
            MediaStat(numberText = "${personaggio.videoGames.size}", text = "Games")
        }
    }
}

@Composable
fun ProfileSection(personaggio: Personaggio) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row {
            RoundImage(personaggio = personaggio)
            Spacer(modifier = Modifier.width(16.dp))
            MediaCounter(personaggio = personaggio)
        }
        
        ProfileDescription(personaggio = personaggio)
    }
}

@Composable
fun MimickInstagramDetail(personaggio: Personaggio, navController: NavHostController) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            TopBar(
                name = personaggio.name,
                modifier = Modifier.padding(10.dp),
                navController = navController
            )

            Spacer(modifier = Modifier.height(4.dp))
            ProfileSection(personaggio = personaggio)
        }
    }
}
/*** FUNZIONE DI NAVIGAZIONE ***/
private fun navigateTo(route: String, navController: NavHostController) {
    navController.navigate(route)
}