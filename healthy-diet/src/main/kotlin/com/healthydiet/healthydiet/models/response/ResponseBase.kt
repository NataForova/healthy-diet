package com.healthydiet.healthydiet.models.response

open class ResponseBase<T>(val count: Int, val next: String, val previous: String) {
    var results = mutableListOf<T>()
}