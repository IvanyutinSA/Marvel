package com.example.marvel

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.gestures.snapping.SnapFlingBehavior
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontSynthesis.Companion.Style
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.marvel.ui.theme.MarvelTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

data class Character(
    val name: String,
    val painter: Painter,
    val backgroundColor: Color,
    val description: String = "",
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MarvelTheme {
                HereNavigation()
            }
        }
    }
}

@Stable
data class Env(
    var backgroundImage: Painter,
    var characters: Array<Character> = arrayOf(),
    val topLogo: Painter,
)

@Composable
fun InitCharacters(env: Env) {
    env.characters = arrayOf(
        Character(
            name = "Langley Asuka",
            painter = painterResource(id = R.drawable.asuka),
            backgroundColor = Color.LightGray,
            description = "is a 14-year-old fictional character from the Neon Genesis Evangelion franchise and one of the main female characters. Asuka is designated as the Second Child (\"Second Children\" in the original Japanese versions) of the Evangelion Project and pilots the Evangelion Unit-02. Her surname is romanized as Soryu in the English manga and Sohryu in the English version of the TV series, the English version of the film, and on GAINAXs website."
        ),
        Character(
            name = "Ayanami Rei",
            painter=  painterResource(id = R.drawable.rei),
            backgroundColor = Color.Blue,
        ),
        Character(
            name = "Misato",
            painter=  painterResource(id = R.drawable.misato),
            backgroundColor = Color.Red,
        )
    )
}

@Composable
fun HereNavigation() {
    val startDestination = "/characters"
    var navController = rememberNavController()
    var env = Env(
        backgroundImage = painterResource(id = R.drawable.background),
        topLogo = painterResource(id = R.drawable.marvel),
    )
    InitCharacters(env = env)

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable("/characters") {
            ScreenCharacters(env = env, navController = navController)
        }

        composable(
            "/characters/{character_id}",
            arguments = listOf(navArgument("character_id") { type = NavType.IntType })
        ) { stackEntry ->
            var characterId = stackEntry.arguments?.getInt("character_id")
            if (characterId != null) {
                ScreenCharacterInformationNoScuffs(env = env, navController = navController, characterId = characterId)
            }
            else {
                ScreenCharacterInformationNoScuffs(env = env, navController = navController, characterId = -1)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScreenCharacters(
    env: Env,
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {

    val lazyListState = rememberLazyListState()

    val textModifier = modifier
        .fillMaxWidth();

    Image(
        painter = env.backgroundImage,
        contentDescription = "sdf",
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
    )

    Box (
        modifier = modifier
            .fillMaxSize()
            .paint(
                painter = env.backgroundImage,
                contentScale = ContentScale.FillBounds
            ),
    )
    {


    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = env.topLogo,
            contentDescription = "dsfds",
            modifier = modifier
                .padding(top = 24.dp)
                .fillMaxWidth()
                .size(200.dp, 50.dp)
                .wrapContentHeight(Alignment.Top),
            contentScale = ContentScale.Crop,
        )

        Text(
            text="Choose Your Hero",
            modifier = textModifier
                .padding(top=12.dp),
            textAlign = TextAlign.Center,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
        )

        val snapFlip = rememberSnapFlingBehavior(lazyListState = lazyListState)

        LazyRow(
            modifier = modifier
                .padding(start = 24.dp, bottom = 48.dp, end = 24.dp, top = 48.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            state = lazyListState,
            flingBehavior = snapFlip,
        ) {
            items(env.characters.size) { index ->
                Box(
                    modifier = modifier
                        .aspectRatio(360f / 800f)
//                        .fillMaxHeight()
                        .border(4.dp, Color.Black)
//                        .wrapContentWidth(Alignment.CenterHorizontally)
                        .background(Color.White)
                        .clickable(onClick = { navController.navigate("/characters/$index") })
                ) {
                    val character = env.characters[index]

                    Image(
                        painter = character.painter,
                        contentDescription = character.name,
                        modifier = modifier.matchParentSize()
                    )
                    Text(
                        buildAnnotatedString {
                            withStyle(style = ParagraphStyle(lineHeight = 32.sp)) {
                                append(character.name)
                            }
                        },
                        modifier = textModifier
                            .fillMaxSize()
                            .padding(
                                bottom = 20.dp,
                                start = 12.dp,
                                end = 24.dp,
                            )
                            .wrapContentHeight(Alignment.Bottom),
                        textAlign = TextAlign.Left,
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp,
                    )
                }

            }
        }
    }
    }
}

@Composable
fun ScreenCharacterInformationNoScuffs(env: Env, navController: NavHostController, characterId: Int) {
    val character = env.characters[characterId]
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = character.painter,
                contentScale = ContentScale.Crop ,
                alignment = Alignment.Center,
            )
    ) {
        IconButton(
            onClick = { navController.navigateUp() },
            modifier = Modifier.padding(top=12.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "dsf",
            )
        }
        Column(
        ) {
            Spacer(Modifier.fillMaxHeight(.4f))
            var scrollState = rememberScrollState()
            Text(
                text =
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(fontSize = 32.sp)) {
                            append(character.name)
                        }
                        withStyle(style = SpanStyle(fontSize = 16.sp)) {
                            append("\n\n")
                            append(character.description)
                        }
                    },
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight(Alignment.Bottom)
                    .verticalScroll(state = scrollState)
                    .background(Color.Black.copy(alpha=.8f)),
                color = Color.White,
            )
        }
    }
}
