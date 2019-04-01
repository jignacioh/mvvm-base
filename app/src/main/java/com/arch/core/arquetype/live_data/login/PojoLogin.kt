package com.arch.core.arquetype.live_data.login

import java.util.ArrayList

class PojoLogin() {
    val category : ArrayList<String>? = null
    val icon_url : String? = null
    val id : String? = null
    val url : String? = null
    val value : String? = null

    override fun toString(): String {
        return "category= "+category?.get(0)+", icon_url= "+icon_url+", id= "+id+", url= "+url+", value= "+value
    }
}