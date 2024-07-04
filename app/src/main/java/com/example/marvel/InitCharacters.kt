package com.example.marvel

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter

@Composable
fun InitCharacters(env: Env) {
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
