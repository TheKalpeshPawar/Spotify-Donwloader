package com.example.dettol.navigation

import android.content.Context
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.dettol.screens.downloadedSongs.DownloadedSongsScreen
import com.example.dettol.screens.downloadedSongs.DownloadedSongsViewModel
import com.example.dettol.screens.error.ErrorScreen
import com.example.dettol.screens.home.HomeScreen
import com.example.dettol.screens.home.HomeScreenViewModel
import com.example.dettol.screens.search.SearchScreen
import com.example.dettol.screens.search.SearchScreenViewModel
import com.example.dettol.screens.songInfo.DownloadViewModel
import com.example.dettol.screens.songInfo.SongInfoScreen
import com.example.dettol.screens.songInfo.SongInfoViewModel


@Composable
fun Navigation (
    context: Context
){
    val sharedPreferences by lazy {
        context.getSharedPreferences("Dettol", 0)
    }

    val navController = rememberNavController()

    val songInfoViewModel = hiltViewModel<SongInfoViewModel>()
    val searchScreenViewModel= hiltViewModel<SearchScreenViewModel>()
    val downloadViewModel= hiltViewModel<DownloadViewModel>()
    val homeScreenViewModel= hiltViewModel<HomeScreenViewModel>()
    val downloadedSongsViewModel= hiltViewModel<DownloadedSongsViewModel>()

    NavHost(
        navController = navController,
        startDestination = Destination.HomeScreen.name
    ){
        composable(
            Destination.HomeScreen.name
        ){
            HomeScreen(
                homeScreenViewModel = homeScreenViewModel,
                navController = navController,
                sharedPreferences = sharedPreferences
            )
        }

        this.composable(route = Destination.SearchScreen.name){
            SearchScreen(
                navController = navController,
                searchScreenViewModel
            )
        }

        this.composable(
            route = "${Destination.SongInfoScreen.name}/{songId}",
            arguments = listOf(
                navArgument(name = "songId"){
                    this.type = NavType.StringType
                }
            )
        ){navBack ->
            navBack.arguments?.getString("songId").let {id ->
                SongInfoScreen(
                    songID = id.toString(),
                    navController =navController,
                    songInfoViewModel = songInfoViewModel,
                    downloadViewModel = downloadViewModel,
                    sharedPreferences = sharedPreferences
                )
            }
        }

        this.composable(
            route = "${Destination.ErrorScreen.name}/{errorMessage}",
            arguments = listOf(
                navArgument(name= "errorMessage"){
                    this.type = NavType.StringType
                }
            )
        ){ navBack ->
            navBack.arguments?.getString("errorMessage").let {msg->
                ErrorScreen(msg ?:"Unknown Error", navController)
            }
        }

        composable(
            route = Destination.DownloadScreen.name
        ){
            DownloadedSongsScreen(
                downloadedSongsViewModel = downloadedSongsViewModel
            )
        }

    }


//    val horizontalPagerState = rememberPagerState(1) { 3 }
//
//    HorizontalPager(
//        state = horizontalPagerState,
//        pageContent = {pageNum ->
//
//            when(pageNum){
//                1 -> {
//
//                }
//                2 -> {
//
//                }
//                3 -> {
//
//                }
//            }
//
//        }
//    )


}