package az.siftoshka.habitube.presentation.screens.explore

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import az.siftoshka.habitube.R
import az.siftoshka.habitube.domain.util.Constants.PAGE_SIZE
import az.siftoshka.habitube.presentation.components.ImageCard
import az.siftoshka.habitube.presentation.components.LongImageCard
import az.siftoshka.habitube.presentation.components.Pager
import az.siftoshka.habitube.presentation.components.TopAppBar
import az.siftoshka.habitube.presentation.theme.HabitubeV2Theme
import az.siftoshka.habitube.presentation.util.Padding
import az.siftoshka.habitube.presentation.util.Screen
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * Composable function of the Explore Screen.
 */
@Composable
fun ExploreScreen(
    navController: NavController
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(color = MaterialTheme.colors.background)
    val scrollState = rememberScrollState()

    HabitubeV2Theme {
        Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxSize()) {
                TopAppBar(
                    title = R.string.app_name,
                    icon = R.drawable.ic_launch_icon
                )
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                ) {
                    UpcomingMovies(navController)
                    Spacer(modifier = Modifier.height(Padding.Large))
                    TrendingMovies(navController)
                    Spacer(modifier = Modifier.height(Padding.Regular))
                    TrendingTvShows()
                    Spacer(modifier = Modifier.height(Padding.Regular))
                    AirTodayTvShows()
                    Spacer(modifier = Modifier.height(Padding.Regular))
                }
            }
        }
    }
}

@Composable
fun UpcomingMovies(
    navController: NavController,
    viewModel: ExploreViewModel = hiltViewModel()
) {
    val upcomingMoviesState = viewModel.exploreUpcomingMoviesState.value

    if (upcomingMoviesState.media.isNotEmpty()) {
        TitleText(text = R.string.text_upcoming_movies)
        Pager(
            width = 320.dp,
            context = { lazyListState ->
                LazyRow(
                    state = lazyListState,
                    contentPadding = PaddingValues(horizontal = Padding.Regular),
                    horizontalArrangement = Arrangement.spacedBy(Padding.Small)
                ) {
                    itemsIndexed(upcomingMoviesState.media) { index, movie ->
                        LongImageCard(imageUrl = movie.backdropPath, title = movie.title) {
                            navController.navigate(Screen.MovieScreen.route + "/${movie.id}")
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun TrendingMovies(
    navController: NavController,
    viewModel: ExploreViewModel = hiltViewModel()
) {
    val trendingMoviesState = viewModel.exploreTrendingMoviesState.value
    val page = viewModel.trendingMoviesPage.value

    if (trendingMoviesState.media.isNotEmpty()) {
        TitleText(text = R.string.text_trending_movies)
        LazyRow(
            modifier = Modifier.height(150.dp),
            contentPadding = PaddingValues(horizontal = Padding.Regular),
            horizontalArrangement = Arrangement.spacedBy(Padding.Small),
        ) {
            itemsIndexed(items = trendingMoviesState.media) { index, movie ->
                viewModel.onChangeTrendingMoviesPosition(index)
                if ((index + 1) >= (page * PAGE_SIZE)) {
                    viewModel.getMoreTrendingMovies()
                }
                ImageCard(imageUrl = movie.posterPath, title = movie.title) {
                    navController.navigate(Screen.MovieScreen.route + "/${movie.id}")
                }
            }
        }
    }
}

@Composable
fun TrendingTvShows(
    viewModel: ExploreViewModel = hiltViewModel()
) {
    val trendingTvShowsState = viewModel.exploreTrendingTvShowsState.value
    val page = viewModel.trendingTvShowsPage.value

    if (trendingTvShowsState.media.isNotEmpty()) {
        TitleText(text = R.string.text_trending_shows)
        LazyRow(
            modifier = Modifier.height(150.dp),
            contentPadding = PaddingValues(horizontal = Padding.Regular),
            horizontalArrangement = Arrangement.spacedBy(Padding.Small)
        ) {
            itemsIndexed(items = trendingTvShowsState.media) { index, show ->
                viewModel.onChangeTrendingTvShowsPosition(index)
                if ((index + 1) >= (page * PAGE_SIZE)) {
                    viewModel.getMoreTrendingTvShows()
                }
                ImageCard(imageUrl = show.posterPath, title = show.title) {

                }
            }
        }
    }
}

@Composable
fun AirTodayTvShows(
    viewModel: ExploreViewModel = hiltViewModel()
) {
    val exploreAirTodayState = viewModel.exploreAirTodayTvShowsState.value
    val page = viewModel.airTodayTvShowsPage.value

    if (exploreAirTodayState.media.isNotEmpty()) {
        TitleText(text = R.string.text_air_today)
        LazyRow(
            modifier = Modifier.height(150.dp),
            contentPadding = PaddingValues(horizontal = Padding.Regular),
            horizontalArrangement = Arrangement.spacedBy(Padding.Small)
        ) {
            itemsIndexed(items = exploreAirTodayState.media) { index, show ->
                viewModel.onChangeAirTodayTvShowsPosition(index)
                if ((index + 1) >= (page * PAGE_SIZE)) {
                    viewModel.getMoreAirTodayTvShows()
                }
                ImageCard(imageUrl = show.posterPath, title = show.title) {

                }
            }
        }
    }
}

@Composable
fun TitleText(@StringRes text: Int) {
    Text(
        text = stringResource(id = text),
        style = MaterialTheme.typography.h2,
        color = MaterialTheme.colors.onBackground,
        textAlign = TextAlign.Start,
        modifier = Modifier.padding(horizontal = Padding.Regular, vertical = Padding.ExtraSmall)
    )
}