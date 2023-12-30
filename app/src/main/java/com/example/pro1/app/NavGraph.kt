package com.example.pro1.app

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pro1.models.Quotes
import com.example.pro1.views.DetailedQuotes
import com.example.pro1.views.QuotesListScreen

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route){
        composable(route = Screen.MainScreen.route){
            QuotesListScreen{
                navController.navigate(Screen.DetailScreen.withArgs(it.author,it.quote))
            }
        }

        composable(
            route = Screen.DetailScreen.route + "/{author}/{quote}",
            arguments = listOf(
                navArgument("author"){
                    type = NavType.StringType
                },
                navArgument("quote"){
                    type = NavType.StringType
                }
            )
        ){entry->
//            Log.d("Bhosda", entry.arguments?.getString("quote").toString())
            DetailedQuotes(
                quote = Quotes(
                    entry.arguments?.getString("quote") ?: "",
                    entry.arguments?.getString("author") ?: "",
                ),
            )
        }

    }
}