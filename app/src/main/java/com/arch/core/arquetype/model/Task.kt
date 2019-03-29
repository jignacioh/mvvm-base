package com.arch.core.arquetype.model

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Task (var id: Int){

    var detail: String? = null
    var count: Int? = null
    var itemName : String ?=null
    var userId : Int?=null
    var title : String ?=null
    var body : String ?=null


    constructor(detail: String, count: Int?) : this(1) {
        this.detail = detail
        this.count = count
    }
    constructor(count: Int,itemName: String) : this(1) {
        this.itemName = itemName
        this.count = count
    }
    constructor(userId: Int?, title: String?, body: String?) : this(1) {
        this.userId = userId
        this.title = title
        this.body = body
    }

}
