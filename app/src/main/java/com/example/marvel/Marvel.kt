package com.example.marvel

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Marvel() {
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

