package com.metra.notesapp.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.metra.notesapp.app.model.BackNavigationEvent
import com.metra.notesapp.app.model.ForwardNavigationEvent
import com.metra.notesapp.app.model.NavigationEvent
import com.metra.notesapp.app.model.Route
import com.metra.notesapp.app.presentation.MainViewModel
import com.metra.notesapp.feature.home.ui.HomeScreen
import com.metra.notesapp.library.ui.NotesAppTheme
import org.koin.androidx.compose.koinViewModel

/**
 * Entry point for the application's UI.
 *
 * This function initializes the [MainViewModel] using Koin and
 * delegates to [MainScreenImpl] for rendering the UI.
 */
@Composable
fun MainScreen() {
    val viewModel = koinViewModel<MainViewModel>()
    MainScreenImpl(
        viewModel = viewModel
    )
}

/**
 * The main implementation of the screen, responsible for UI layout and navigation setup.
 * @param viewModel The ViewModel providing navigation events and state management.
 */
@Composable
private fun MainScreenImpl(
    viewModel: MainViewModel
) {
    NotesAppTheme {
        val navController = rememberNavController()

        // Observes navigation events and performs navigation actions
        NavigationEffect(
            navController = navController,
            viewModel = viewModel,
            onNavigationEventConsumed = viewModel::onNavigationEventConsumed
        )

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Screens(
                navController = navController,
                modifier = Modifier
                    .weight(1f)
                    .background(MaterialTheme.colorScheme.background)
            )
        }
    }
}

/**
 * Defines the available screens in the navigation graph
 */
@Composable
private fun Screens(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Route.Initial(),
        modifier = modifier
    ) {
        composable(Route.Home()) { HomeScreen() }
    }
}

/**
 * Observes navigation events and performs navigation actions accordingly.
 * Uses [SideEffect] to ensure navigation changes are executed once per recomposition.
 *
 * @param navController The navigation controller for handling navigation actions
 * @param viewModel The ViewModel that provides navigation events
 * @param onNavigationEventConsumed Callback to mark the navigation event as handled
 */
@Composable
private fun NavigationEffect(
    navController: NavHostController,
    viewModel: MainViewModel,
    onNavigationEventConsumed: () -> Unit
) {
    val state = viewModel.states.collectAsState()
    val navigationEvent = state.value.navigationEvent

    SideEffect {
        when (navigationEvent) {
            /**
             * Handles back navigation events.
             *
             * - Calls `navController.navigateUp()` to return to the previous screen.
             * - Marks the navigation event as consumed to prevent duplicate navigation
             */
            is BackNavigationEvent -> {
                navController.navigateUp()
                onNavigationEventConsumed()
            }

            /**
             * Handles forward navigation events.
             *
             * - Checks if the current destination is already the target route.
             * - If not, prepares `NavOptions` to handle back stack clearing if needed.
             * - Calls `navController.navigate()` to navigate to the new destination.
             * - Marks the navigation event as consumed after navigation is performed
             */
            is ForwardNavigationEvent -> {
                if (navController.currentDestination?.route != navigationEvent.route()) {
                    val navOptions = prepareNavOptions(navigationEvent)

                    navController.navigate(navigationEvent.route(), navOptions)
                    onNavigationEventConsumed()
                }
            }

            null -> Unit
        }
    }
}

/**
 * Prepares navigation options based on the provided [ForwardNavigationEvent].
 *
 * If [NavigationEvent.ForwardEvent.clearBackStack] is `true`, the navigation stack is cleared
 * up to the specified route (or completely if no route is provided).
 *
 * @param navigationEvent The navigation event containing back stack clearing details.
 * @return A [NavOptions] object if back stack clearing is needed, otherwise `null`.
 */
private fun prepareNavOptions(navigationEvent: ForwardNavigationEvent): NavOptions? {
    return if (navigationEvent.clearBackStack) {
        NavOptions.Builder().also { navOptionsBuilder ->
            navigationEvent.clearBackStackRoute?.let {
                navOptionsBuilder.setPopUpTo(it(), false)
            } ?: navOptionsBuilder.setPopUpTo(0, false)
        }.build()
    } else {
        null
    }
}
