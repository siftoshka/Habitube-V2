package az.siftoshka.habitube.data.repository

import az.siftoshka.habitube.data.remote.MovieService
import az.siftoshka.habitube.data.remote.dto.*
import az.siftoshka.habitube.domain.repository.RemoteRepository
import javax.inject.Inject

/**
 * Implementation of the [RemoteRepository] of application.
 */
class RemoteRepositoryImpl @Inject constructor(
    private val service: MovieService
) : RemoteRepository {

    override suspend fun getUpcomingMovies(page: Int): List<MediaLiteDto> {
        return service.getUpcomingMovies(page).results
    }

    override suspend fun getTrendingMovies(page: Int): List<MediaLiteDto> {
        return service.getTrendingMovies(page).results
    }

    override suspend fun getTrendingTvShows(page: Int): List<MediaLiteDto> {
        return service.getTrendingTvShows(page).results
    }

    override suspend fun getAirTodayTvShows(page: Int): List<MediaLiteDto> {
        return service.getAirTodayTvShows(page).results
    }

    override suspend fun getMovie(movieId: Int): MovieDto {
        return service.getMovie(movieId)
    }

    override suspend fun getMovieVideos(movieId: Int): List<VideoDto> {
        return service.getMovieVideos(movieId).results
    }

    override suspend fun getMovieCredits(movieId: Int): CreditDto {
        return service.getMovieCredits(movieId)
    }

    override suspend fun getSimilarMovies(movieId: Int, page: Int): List<MediaLiteDto> {
        return service.getSimilarMovies(movieId, page).results
    }

    override suspend fun getTvShow(showId: Int): TvShowDto {
        return service.getTvShow(showId)
    }

    override suspend fun getTvShowVideos(showId: Int): List<VideoDto> {
        return service.getTvShowVideos(showId).results
    }

    override suspend fun getTvShowCredits(showId: Int): CreditDto {
        return service.getTvShowCredits(showId)
    }

    override suspend fun getSimilarTvShows(showId: Int, page: Int): List<MediaLiteDto> {
        return service.getSimilarTvShows(showId, page).results
    }
}