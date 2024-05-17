package com.example.apiavatart2.data.remote.model

import com.google.gson.annotations.SerializedName

data class AvatarDto(
    @SerializedName("allies")
    val allies: List<String>,

    @SerializedName("enemies")
    val enemies: List<String>,

    @SerializedName("_id")
    val id: String,

    @SerializedName("photoUrl")
    val photoUrl: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("gender")
    val gender: String,

    @SerializedName("hair")
    val hair: String,

    @SerializedName("weapon")
    val weapon: String,

    @SerializedName("profession")
    val profession: String,

    @SerializedName("position")
    val position: String,

    @SerializedName("affiliation")
    val affiliation: String,

    @SerializedName("first")
    val first: String
)
