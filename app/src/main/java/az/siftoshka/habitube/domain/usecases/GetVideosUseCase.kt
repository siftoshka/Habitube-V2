package az.siftoshka.habitube.domain.usecases

import az.siftoshka.habitube.data.remote.dto.toVideo
import az.siftoshka.habitube.domain.model.Video
import az.siftoshka.habitube.domain.repository.RemoteRepository
import az.siftoshka.habitube.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Use-case to get videos from repository call.
 */
class GetVideosUseCase @Inject constructor(
    private val repository: RemoteRepository
) {
    operator fun invoke(movieId: Int): Flow<Resource<List<Video>>> = flow {
        try {
            emit(Resource.Loading())
            val videos = repository.getMovieVideos(movieId).map { it.toVideo() }
            emit(Resource.Success(videos))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "HTTP Error"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}