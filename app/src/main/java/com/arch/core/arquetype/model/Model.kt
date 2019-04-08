package com.arch.core.arquetype.model

import com.arch.core.arquetype.live_data.login.PojoLogin
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Model(var parts:List<Task>) {


   // var parts: List<Task>? = ArrayList<Task>()


}

@JsonClass(generateAdapter = true)
data class LoginLD(var category : List<String>, var icon_url : String, var id : String, var url : String, var value : String){}