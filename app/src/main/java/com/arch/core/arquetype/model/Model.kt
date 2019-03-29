package com.arch.core.arquetype.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Model(var parts:List<Task>) {


   // var parts: List<Task>? = ArrayList<Task>()


}