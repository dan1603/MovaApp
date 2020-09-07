package com.kalashnyk.denys.movaapp.usecases.repository.remote_datasource.pojo

import com.google.gson.annotations.SerializedName
import com.kalashnyk.denys.movaapp.usecases.repository.datasource.database.entity.SearchHistoryEntity

data class GiphyQueryResponse(
    @SerializedName("data")
    val data: List<GiphySection>
) {
    fun convert(query: String): SearchHistoryEntity {
        val randomSection = data.random()
        return SearchHistoryEntity(
            query,
            query,
            randomSection.images.original.url
        )
    }
}

data class GiphySection(
    @SerializedName("id")
    val id: String,
    @SerializedName("images")
    val images: GiphyImages
)

data class GiphyImages(
    @SerializedName("original")
    val original: GiphyOriginal
)

data class GiphyOriginal(
    @SerializedName("height")
    val height: Int,
    @SerializedName("width")
    val width: Int,
    @SerializedName("size")
    val size: Int,
    @SerializedName("url")
    val url: String
)