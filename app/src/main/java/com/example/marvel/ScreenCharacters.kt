package com.example.marvel

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScreenCharacters(
    env: Env,
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {

    val lazyListState = rememberLazyListState()
    var characterColor by remember { mutableStateOf(Color.Red) }

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
