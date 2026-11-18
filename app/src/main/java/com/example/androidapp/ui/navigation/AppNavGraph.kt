package com.example.androidapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.androidapp.ui.login.LoginScreen
import com.example.androidapp.ui.profile.ProfileScreen
import com.example.androidapp.ui.signup.SignUpScreen

object Routes {
    const val PROFILE = "profile"
    const val LOGIN = "login"
    const val SIGN_UP = "sign_up"
}

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.PROFILE) {
        composable(Routes.PROFILE) {
            ProfileScreen(
                onSignInClick = { navController.navigate(Routes.LOGIN) },
                onSignUpClick = { navController.navigate(Routes.SIGN_UP) }
            )
        }
        composable(Routes.LOGIN) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Routes.PROFILE) {
                        popUpTo(Routes.PROFILE) { inclusive = true }
                    }
                },
                onSignUpClick = {
                    navController.navigate(Routes.SIGN_UP) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                }
            )
        }
        composable(Routes.SIGN_UP) {
            SignUpScreen(
                onSignUpSuccess = {
                    navController.navigate(Routes.PROFILE) {
                        popUpTo(Routes.PROFILE) { inclusive = true }
                    }
                },
                onSignInClick = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.SIGN_UP) { inclusive = true }
                    }
                }
            )
        }
    }
}
