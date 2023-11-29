package com.bahardev.submissioncompose.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object About: Screen("about")
    object DetailItem: Screen("home/{id}"){
        fun createRoute(id: String) = "home/$id"
    }
}