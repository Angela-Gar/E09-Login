package com.agc.e09_login.screens.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.agc.e09_login.navigation.Screens
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    //ANIMACION
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.8f,
            //Comportamiento de la animación, efecto rebote
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioHighBouncy,
                stiffness = Spring.StiffnessLow
            )
        )

        delay(2000)

        //ir a la siguiente pantalla
        navController.navigate(Screens.LoginScreen.name)
        //si ya está logueado el usuario no necesita autenticarse de nuevo
        if (FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()) {
            navController.navigate(Screens.LoginScreen.name)
        } else {
            navController.navigate(Screens.HomeScreen.name) {
                //al pulsaar boton atras vuelve a splash, para evitar esto y que no vuelva a salir la animacion
                //sacamos el splash de la lista de pantallas recorridas
                popUpTo(Screens.SplashScreen.name) {
                    inclusive = true
                }
            }
        }
    }

    val color = Color.Green
    val color2 = Color.Black

    Surface(
        modifier = Modifier
            .padding(15.dp)
            .size(250.dp)
            .scale(scale.value), //para la animacion
        shape = CircleShape,
        border = BorderStroke(width = 2.dp, color = color)
    ) {
        Column(
            modifier = Modifier.padding(1.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "SPOTIFY",
                style = MaterialTheme.typography.titleLarge,
                color = color2.copy(alpha = 0.5f)
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "LOADING",
                style = MaterialTheme.typography.titleMedium,
                color = color2.copy(alpha = 0.5f)
            )
        }
    }
}