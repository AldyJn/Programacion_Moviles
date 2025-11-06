package com.montoya.firebase.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Register : Screen("register")
    object Login : Screen("login")
    object Welcome : Screen("welcome")
    object UserList : Screen("user_list")
    object ImageUpload : Screen("image_upload")
}
