package com.example.apiavatart2.data.remote

import com.example.apiavatart2.data.remote.model.AvatarDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AvatarApi {

    @GET("api/v1/characters?perPage=497")
    fun getCharacters(): Call<List<AvatarDto>>

    @GET("api/v1/characters/{id}")
    fun getCharacterDetail(
        @Path("id") id: String
    ): Call<AvatarDto>
}
