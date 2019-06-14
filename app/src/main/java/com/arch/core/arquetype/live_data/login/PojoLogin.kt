package com.arch.core.arquetype.live_data.login

import com.squareup.moshi.JsonClass
import java.util.ArrayList


@JsonClass(generateAdapter = true)
class PojoLogin(){
//data class PojoLogin(var category : ArrayList<String>, var icon_url : String, var id : String, var url : String, var value : String) {
    var category : List<String>? = ArrayList()
    var icon_url : String? = null
    var id : String? = null
    var url : String? = null
    var value : String? = null

    override fun toString(): String {
        return "category= "+category?.get(0)+", icon_url= "+icon_url+", id= "+id+", url= "+url+", value= "+value
    }
}