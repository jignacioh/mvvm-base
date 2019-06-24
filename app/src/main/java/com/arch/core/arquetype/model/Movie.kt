package com.arch.core.arquetype.model

import com.google.gson.annotations.SerializedName

data class Movie(var id: Int){

    @SerializedName("poster_path")
    var urlMovie: String? = null

    @SerializedName("original_title")
    var nameMovie: String? = null

    @SerializedName("release_date")
    var yearMovie: String? = null

    @SerializedName("popularity")
    private var popularity: String? = null

    @SerializedName("backdrop_path")
    var imageMovie: String? = null

    @SerializedName("overview")
    var descriptionMovie: String? = null

   // var listTrailerMovie: List<Trailer>? = null

    @SerializedName("vote_count")
    var voteCount: Int = 0

    @SerializedName("vote_average")
    var voteAverage: Double? = null

}
