package com.arch.core.arquetype.model

import com.arch.core.arquetype.live_data.login.PojoLogin

data class ModelRest(val parts_1:List<Task>) {


}

data class ModelRestLogin(var category : List<String>, var icon_url : String, var id : String, var url : String, var value : String){

}