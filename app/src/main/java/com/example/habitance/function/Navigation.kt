package com.example.habitance.function


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.habitance.activity.LoginPage
import com.example.habitance.navbar.BottomNavGraph
import com.example.habitance.ui.screens.auth.register.RegisterScreen
import com.example.habitance.ui.screens.auth.sign_up.SignupPage
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.delay
import kotlinx.coroutines.tasks.await


@Composable
fun Navigation( modifier : Modifier = Modifier, authManager: AuthManager) {
    val navController = rememberNavController()
    var startDestination by remember { mutableStateOf("login") }
    var isLoading by remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        if (Firebase.auth.currentUser != null) {
            val userExists = Firebase.firestore.collection("users")
                .document(Firebase.auth.currentUser!!.uid).get().await()
                .exists()
            if (userExists) {
                startDestination = "home"
            } else {
                startDestination = "register"
            }
        } else {
            startDestination = "login"
        }
        delay(500)
        isLoading = false
    }

    Loader(isLoading){
        NavHost(
            navController = navController,
            startDestination =startDestination
        ) {
            composable("login") {
                LoginPage(modifier, navController)
            }
            composable("signup") {
                SignupPage(modifier, navController)
            }
            composable("register") {
                RegisterScreen(modifier, navController)
            }
            composable("home") {
                BottomNavGraph(navController)
            }
        }
    }
}