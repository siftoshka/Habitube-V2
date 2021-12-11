package az.siftoshka.habitube.data.remote.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PersonDto(
    @SerializedName("adult") @Expose val adult: Boolean,
    @SerializedName("also_known_as") @Expose val alsoKnownAs: List<String>,
    @SerializedName("biography") @Expose val biography: String,
    @SerializedName("birthday") @Expose val birthday: String,
    @SerializedName("deathday") @Expose val deathday: String,
    @SerializedName("gender") @Expose val gender: Int,
    @SerializedName("homepage") @Expose val homepage: String,
    @SerializedName("id") @Expose val id: Int,
    @SerializedName("imdb_id") @Expose val imdbId: String,
    @SerializedName("known_for_department") @Expose val knownForDepartment: String,
    @SerializedName("name") @Expose val name: String,
    @SerializedName("place_of_birth") @Expose val placeOfBirth: String,
    @SerializedName("popularity") @Expose val popularity: Double,
    @SerializedName("profile_path") @Expose val profilePath: String
)