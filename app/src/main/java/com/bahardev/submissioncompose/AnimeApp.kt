package com.bahardev.submissioncompose

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bahardev.submissioncompose.navigation.NavigationItem
import com.bahardev.submissioncompose.navigation.Screen
import com.bahardev.submissioncompose.ui.about.AboutScreen
import com.bahardev.submissioncompose.ui.home.HomeScreen
import com.bahardev.submissioncompose.ui.detail.DetailScreen
import com.bahardev.submissioncompose.ui.theme.SubmissionComposeTheme

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    currentRoute: String
){
    NavigationBar(modifier = modifier) {

        val navigationItems = listOf(
            NavigationItem("Home", Icons.Default.Home, Screen.Home),
            NavigationItem("About", Icons.Default.Info, Screen.About)
        )

        navigationItems.map { item ->
            NavigationBarItem(
                selected = currentRoute == item.screen.route,
                icon = { Icon( item.icon, item.title ) },
                label = { Text(item.title) },
                onClick = {
                      navController.navigate(item.screen.route){
                          popUpTo(navController.graph.findStartDestination().id){
                              saveState = true
                          }
                          restoreState = true
                          launchSingleTop = true
                      }
                },
            )
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun AnimeApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if ( currentRoute !== "home/{id}" ) {
                BottomBar(navController = navController, currentRoute = currentRoute.toString())
            }
        },
        modifier = modifier
    ){innerPadding ->
        NavHost(navController = navController, startDestination = Screen.Home.route, modifier = modifier.padding(innerPadding)){
            composable(Screen.Home.route) { HomeScreen(
                navigateToDetail = { itemId ->
                    navController.navigate(Screen.DetailItem.createRoute(itemId))
                }
            ) }
            composable(
                route = Screen.DetailItem.route,
                arguments = listOf(navArgument("id"){
                    type = NavType.StringType
                })
            ){
                val id = it.arguments?.getString("id") ?: ""
                DetailScreen(id = id, navigateToHome = {
                    navController.navigate(Screen.Home.route){
                        popUpTo(navController.graph.findStartDestination().id){
                            saveState = false
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                })
            }
            composable(Screen.About.route) { AboutScreen() }
        }
    }
}

@Preview(showBackground = true)
@ExperimentalMaterial3Api
@Composable
fun HeroesAppPreview() {
    SubmissionComposeTheme {
        AnimeApp()
    }
}