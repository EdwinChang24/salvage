package io.github.edwinchang24.salvage.ui

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Style
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.navigate
import io.github.edwinchang24.salvage.ui.animation.Transitions
import io.github.edwinchang24.salvage.ui.destinations.MainNavDestination
import io.github.edwinchang24.salvage.ui.destinations.NewBookmarkPageDestination
import io.github.edwinchang24.salvage.ui.destinations.NewTagPageDestination
import io.github.edwinchang24.salvage.ui.destinations.SavedPageDestination
import io.github.edwinchang24.salvage.ui.destinations.TagsPageDestination

@OptIn(ExperimentalMaterial3Api::class)
@RootNavGraph(start = true)
@Destination(style = MainNavTransitions::class)
@Composable
fun MainNav(rootNavigator: DestinationsNavigator) {
    val mainNavController = rememberNavController()
    val currentDestination =
        mainNavController.appCurrentDestinationAsState().value ?: NavGraphs.main.startAppDestination
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    when (currentDestination) {
                        SavedPageDestination -> Text("Saved")
                        TagsPageDestination -> Text("Tags")
                        else -> {}
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(scrolledContainerColor = MaterialTheme.colorScheme.surface),
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    when (currentDestination) {
                        SavedPageDestination -> rootNavigator.navigate(NewBookmarkPageDestination)
                        TagsPageDestination -> rootNavigator.navigate(NewTagPageDestination)
                        else -> rootNavigator.navigate(NewBookmarkPageDestination)
                    }
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = currentDestination == SavedPageDestination,
                    onClick = { mainNavController.navigate(SavedPageDestination) },
                    icon = { Icon(Icons.Default.Bookmarks, contentDescription = null) },
                    label = { Text("Saved") }
                )
                NavigationBarItem(
                    selected = currentDestination == TagsPageDestination,
                    onClick = { mainNavController.navigate(TagsPageDestination) },
                    icon = { Icon(Icons.Default.Style, contentDescription = null) },
                    label = { Text("Tags") }
                )
            }
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { padding ->
        DestinationsNavHost(
            navGraph = NavGraphs.main,
            navController = mainNavController,
            modifier = Modifier.padding(padding)
        )
    }
}

object MainNavTransitions : Transitions(destination = MainNavDestination, enter = { fadeIn() }, exit = { fadeOut() })

