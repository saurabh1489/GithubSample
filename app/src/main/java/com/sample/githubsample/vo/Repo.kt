package com.sample.githubsample.vo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.nio.channels.spi.AbstractSelectableChannel

@Entity(primaryKeys = ["author", "name"])
data class Repo(
    @field:SerializedName("author")
    val author: String,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("avatar")
    val avatar: String,
    @field:SerializedName("url")
    val url: String,
    @field:SerializedName("description")
    val description: String,
    @field:SerializedName("language")
    val language: String?,
    @field:SerializedName("languageColor")
    val languageColor: String?,
    @field:SerializedName("stars")
    val stars: Int,
    @field:SerializedName("forks")
    val fork: Int,
    @field:SerializedName("currentPeriodStars")
    val currentPeriodStars: Int
)