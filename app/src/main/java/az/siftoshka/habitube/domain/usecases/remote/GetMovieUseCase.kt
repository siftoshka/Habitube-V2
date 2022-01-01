package az.siftoshka.habitube.domain.usecases.remote

import az.siftoshka.habitube.data.remote.dto.toMovie
import az.siftoshka.habitube.domain.model.Movie
import az.siftoshka.habitube.domain.repository.MovieDBApiRepository
import az.siftoshka.habitube.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Use-case to get movie from repository call.
 */
class GetMovieUseCase @Inject constructor(
    private val repository: MovieDBApiRepository
) {
    operator fun invoke(movieId: Int): Flow<Resource<Movie>> = flow {
        try {
            emit(Resource.Loading())
            val movie = repository.getMovie(movieId).toMovie()
            emit(Resource.Success(movie))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "HTTP Error"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}