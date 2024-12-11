package com.example.lemonade

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.ColorRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.component1
import androidx.core.graphics.component2
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    LemonadeApp()
                }
            }
        }
    }
}
@Composable
fun LemonadeApp(modifier: Modifier = Modifier){
    var result by remember { mutableIntStateOf(1)}
    var pressCount by remember { mutableIntStateOf(0)}
    var targetPressCount by remember { mutableIntStateOf((2..4).random())}

    // Create a map of image resources and text strings
    val (imageResource,stringResource) = when(result){
        1 -> Pair( R.drawable.lemon_tree ,R.string.lemon_tree)
        2 -> Pair(R.drawable.lemon_squeeze, R.string.lemon)
        3 -> Pair (R.drawable.lemon_drink, R.string.glass)
        else -> Pair(R.drawable.lemon_restart, R.string.empty)
    }
    val textResource = when(result){
        1 -> R.string.select_lemon
        2 -> R.string.squeeze_lemon
        3 -> R.string.drink_lemon
        else -> R.string.start_again
    }

    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxSize()){
        // titre de l'application
        Surface(
            modifier = Modifier
                .fillMaxWidth(),
            color = colorResource(R.color.titreImage)
        ){
            Text(
                text = stringResource(R.string.titre),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(16.dp)
            )
        }
        //corps de l'application
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()

        ){
            Image(
                painter = painterResource(imageResource),
                contentDescription = stringResource(stringResource),
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(16.dp)) // Ajout des coins arrondis
                    .border(
                        width = 2.dp,// Epaisseur de la bordure
                        color = colorResource(R.color.fondImage), // Couleur de la bordure
                        shape = RoundedCornerShape(16.dp)
                    )
                    .background(colorResource(R.color.fondImage))
                    .padding(16.dp) // Ajout d'un padding autour de l'image
                    .clickable {
                        when (result) {
                            1 -> {
                                result = 2
                                pressCount = 0
                                targetPressCount = (2..4).random()

                            }

                            2 -> {
                                pressCount++
                                if (pressCount >= targetPressCount) {
                                    result = 3
                                    pressCount = 0
                                }
                            }

                            3 -> result = 4
                            4 -> result = 1

                        }
                    }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(textResource),
                fontSize = 18.sp,
                textAlign = TextAlign.Center,

                )

        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonadeTheme {
        LemonadeApp()
    }
}

