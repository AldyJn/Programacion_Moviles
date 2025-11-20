package com.tecsup.authfirebaseapp_montoya

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tecsup.authfirebaseapp_montoya.ui.screens.*

object Destinations {
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val HOME = "home"
    const val COURSES = "courses"
    const val ADD_COURSE = "add_course"
    const val EDIT_COURSE = "edit_course"
}

@Composable
fun AuthApp() {
    val navController = rememberNavController()
    AuthNavGraph(navController)
}

@Composable
fun AuthNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Destinations.LOGIN
    ) {
        composable(Destinations.LOGIN) {
            LoginScreen(
                onNavigateToRegister = {
                    navController.navigate(Destinations.REGISTER)
                },
                onLoginSuccess = {
                    navController.navigate(Destinations.HOME) {
                        popUpTo(Destinations.LOGIN) { inclusive = true }
                    }
                }
            )
        }

        composable(Destinations.REGISTER) {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate(Destinations.HOME) {
                        popUpTo(Destinations.LOGIN) { inclusive = true }
                    }
                },
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Destinations.HOME) {
            HomeScreen(
                onLogout = {
                    navController.navigate(Destinations.LOGIN) {
                        popUpTo(Destinations.HOME) { inclusive = true }
                    }
                },
                onNavigateToCourses = {
                    navController.navigate(Destinations.COURSES)
                }
            )
        }

        composable(Destinations.COURSES) {
            CoursesScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onAddCourse = {
                    navController.navigate(Destinations.ADD_COURSE)
                },
                onEditCourse = { courseId ->
                    navController.navigate("${Destinations.EDIT_COURSE}/$courseId")
                }
            )
        }

        composable(Destinations.ADD_COURSE) {
            AddCourseScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onCourseSaved = {
                    navController.popBackStack()
                }
            )
        }

        composable("${Destinations.EDIT_COURSE}/{courseId}") { backStackEntry ->
            val courseId = backStackEntry.arguments?.getString("courseId") ?: ""
            EditCourseScreen(
                courseId = courseId,
                onNavigateBack = {
                    navController.popBackStack()
                },
                onCourseSaved = {
                    navController.popBackStack()
                }
            )
        }
    }
}