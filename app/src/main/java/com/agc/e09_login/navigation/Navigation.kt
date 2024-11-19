package com.agc.e09_login.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.agc.e09_login.screens.home.HomeScreen
import com.agc.e09_login.screens.login.LoginScreen
import com.agc.e09_login.screens.splash.SplashScreen

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.name
    ){
        composable(Screens.SplashScreen.name){
            SplashScreen(navController = navController)
        }

        composable(Screens.LoginScreen.name){
            LoginScreen(navController = navController)
        }

        composable(Screens.HomeScreen.name){
            HomeScreen(navController = navController)
        }
    }
}