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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.platform.LocalContext
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
import coil.ImageLoader
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.marvel.ui.theme.MarvelTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

data class Character(
    val name: String,
    val painter: Painter,
    val backgroundColor: Color,
    val description: String = "",
    val logoUrl: String = "",
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
    var x = rememberAsyncImagePainter(model = "https://i.postimg.cc/W1Nkbxb2/rei.jpg", imageLoader = ImageLoader(context = LocalContext.current))
    env.characters = arrayOf(
        Character(
            name = "Spider Man",
            painter = rememberAsyncImagePainter("https://cdn.kinocheck.com/i/w=375/lq3jw5b1y2.jpg"),
            backgroundColor = Color.LightGray,
            description = "Peter Parker is a superhero portrayed by Tom Holland in the Marvel Cinematic Universe (MCU) media franchise–based on the Marvel Comics character of the same name—also known by his alias, Spider-Man. Parker is initially depicted as a student at the Midtown School of Science and Technology who received spider-like and superhuman abilities after being bitten by a radioactive spider. Parker initially uses his powers to fight crime as a vigilante in Queens. ",
        ),
        Character(
            name = "Captain America",
            painter = rememberAsyncImagePainter("https://static.wikia.nocookie.net/villains/images/9/91/CaptainUnited..png/revision/latest?cb=20230629133146"),
            backgroundColor = Color.LightGray,
            description = "Captain America is a superhero created by Joe Simon and Jack Kirby who appears in American comic books published by Marvel Comics. The character first appeared in Captain America Comics #1, published on December 20, 1940, by Timely Comics, a corporate predecessor to Marvel. Captain America's civilian identity is Steve Rogers, a frail man enhanced to the peak of human physical perfection by an experimental \"super-soldier serum\" after joining the United States Army to aid the country's efforts in World War II. Equipped with an American flag–inspired costume and a virtually indestructible shield, Captain America and his sidekick Bucky Barnes clashed frequently with the villainous Red Skull and other members of the Axis powers. In the final days of the war, an accident left Captain America frozen in a state of suspended animation until he was revived in modern times. He resumes his exploits as a costumed hero and becomes leader of the superhero team the Avengers, but frequently struggles as a \"man out of time\" to adjust to the new era.",
        ),
        Character(
            name = "Iron Man",
            painter = rememberAsyncImagePainter("https://cdn.britannica.com/49/182849-050-4C7FE34F/scene-Iron-Man.jpg"),
            backgroundColor = Color.LightGray,
            description = "Iron Man is a superhero appearing in American comic books published by Marvel Comics. Co-created by writer and editor Stan Lee, developed by scripter Larry Lieber, and designed by artists Don Heck and Jack Kirby, the character first appeared in Tales of Suspense #39 in 1962 and received his own title with Iron Man #1 in 1968. Shortly after his creation, Iron Man became a founding member of the superhero team, the Avengers, with Thor, Ant-Man, the Wasp, and the Hulk. Iron Man stories, individually and with the Avengers, have been published consistently since the character's creation. ",
        ),
        Character(
            name = "Thanos",
            painter = rememberAsyncImagePainter("https://static.wikia.nocookie.net/marvelcinematicuniverse/images/5/52/Empire_March_Cover_IW_6_Textless.png/revision/latest?cb=20231024024627"),
            backgroundColor = Color.LightGray,
            description = "Thanos is a fictional character portrayed primarily by Josh Brolin in the Marvel Cinematic Universe (MCU) media franchise, based on the Marvel Comics supervillain of the same name. He is depicted as an alien warlord from the doomed planet Titan with a universe-spanning agenda to wipe out half of all life to stabilize overpopulation and prevent what he views as life's inevitable extinction. To do this, he sets out to obtain the six Infinity Stones, cosmic gems with the power to achieve his goal. With the help of his adopted children, Thanos fights against the Avengers, the Guardians of the Galaxy, and their allies, in the Infinity War, succeeds in assembling the Stones, and disintegrates half of all life in the universe in an event that is known as the Blip. After escaping to the Garden and destroying the Stones, he is eventually killed by Thor. Five years later, an alternate version of Thanos from 2014 time travels to 2023 to battle the Avengers once again, but is killed by Tony Stark. ",
        ),
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
    var characterColor by remember { mutableStateOf(Color.Red)}

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
                contentScale = ContentScale.FillBounds,
//                colorFilter = ColorFilter.tint(characterColor),
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
                .padding(bottom = 48.dp, top = 48.dp),
//            horizontalArrangement = Arrangement.spacedBy(24.dp),
            state = lazyListState,
            flingBehavior = snapFlip,
        ) {
            items(env.characters.size) { index ->
                characterColor = Color.Black.copy(alpha = .2f*index)
                Box(
                    modifier = modifier
                        .padding(start = 16.dp, end = 16.dp)
                        .clip(shape = RoundedCornerShape(25.dp))
                        .aspectRatio(360f / 800f)
                        .background(Color.White)
                        .clickable(onClick = { navController.navigate("/characters/$index") }),
                ) {
                    val character = env.characters[index]

                    Image(
                        painter = character.painter,
                        contentDescription = character.name,
                        modifier = modifier.matchParentSize(),

                        contentScale = ContentScale.Crop,
                    )

                    Text(
                        buildAnnotatedString {
                            withStyle(style = ParagraphStyle(lineHeight = 32.sp)) {
                                append(character.name)
                            }
                        },
                        modifier = textModifier
                            .fillMaxHeight()
                            .padding(
                                bottom = 20.dp,
                                start = 12.dp,
                                end = 12.dp,
                            )
                            .wrapContentHeight(Alignment.Bottom)
                            .clip(shape = RoundedCornerShape(25.dp))
                            .background(Color.White.copy(alpha = 1f)),
                        color = Color.Black,
                        textAlign = TextAlign.Center,
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
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
            )
    ) {
        IconButton(
            onClick = { navController.navigateUp() },
            modifier = Modifier
                .padding(top=12.dp)
                .clip(shape = CircleShape)
                .background(color = Color.White.copy(alpha = .4f))
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
                    .background(Color.White.copy(alpha = .8f)),
                color = Color.Black,
            )
        }
    }
}
