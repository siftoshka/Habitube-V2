package az.siftoshka.habitube.domain.usecases.remote

import az.siftoshka.habitube.data.remote.dto.toCredit
import az.siftoshka.habitube.domain.model.PersonCredit
import az.siftoshka.habitube.domain.repository.RemoteRepository
import az.siftoshka.habitube.domain.util.MediaType
import az.siftoshka.habitube.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Use-case to get person credits from repository call.
 */
class GetPersonCreditsUseCase @Inject constructor(
    private val repository: RemoteRepository
) {
    operator fun invoke(mediaId: Int, mediaType: MediaType): Flow<Resource<PersonCredit>> = flow {
        try {
            emit(Resource.Loading())
            var credits = when (mediaType) {
                MediaType.Movie -> repository.getPersonMovieCredits(mediaId).toCredit()
                MediaType.TvShow -> repository.getPersonTvShowCredits(mediaId).toCredit()
            }
            credits = credits.copy(cast = credits.cast?.filterNot { it.posterPath.isNullOrBlank() })
            credits = credits.copy(crew = credits.crew?.filterNot { it.posterPath.isNullOrBlank() })
            emit(Resource.Success(credits))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "HTTP Error"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}