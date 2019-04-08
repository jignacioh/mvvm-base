package com.arch.core.arquetype.live_data.login

import com.squareup.moshi.JsonClass
import java.util.ArrayList


//@JsonClass(generateAdapter = true)
class PojoLogin(){//(var category : ArrayList<String>, var icon_url : String, var id : String, var url : String, var value : String) {
    val category : ArrayList<String>? = null
    val icon_url : String? = null
    val id : String? = null
    val url : String? = null
    val value : String? = null

    override fun toString(): String {
        return "category= "+category?.get(0)+", icon_url= "+icon_url+", id= "+id+", url= "+url+", value= "+value
    }
}