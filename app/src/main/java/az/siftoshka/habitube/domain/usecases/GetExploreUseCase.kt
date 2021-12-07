package az.siftoshka.habitube.domain.usecases

import az.siftoshka.habitube.data.remote.dto.toMediaLite
import az.siftoshka.habitube.domain.model.MediaLite
import az.siftoshka.habitube.domain.repository.RemoteRepository
import az.siftoshka.habitube.domain.util.ExploreType
import az.siftoshka.habitube.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Use-case to get trending movies from repository call.
 */
class GetExploreUseCase @Inject constructor(
    private val repository: RemoteRepository
) {
    operator fun invoke(page: Int, type: ExploreType) : Flow<Resource<List<MediaLite>>> = flow {
        try {
            emit(Resource.Loading())
            val movies = when (type) {
                ExploreType.Upcoming -> repository.getUpcomingMovies(page).map { it.toMediaLite() }
                ExploreType.TrendingMovies -> repository.getTrendingMovies(page).map { it.toMediaLite() }
                ExploreType.TrendingTvShows -> repository.getTrendingTvShows(page).map { it.toMediaLite() }
                ExploreType.AirToday -> repository.getAirTodayTvShows(page).map { it.toMediaLite() }
            }
            emit(Resource.Success(movies))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "HTTP Error"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}