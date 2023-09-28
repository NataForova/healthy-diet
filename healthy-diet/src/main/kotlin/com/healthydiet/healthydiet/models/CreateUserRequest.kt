package com.healthydiet.healthydiet.models

open class CreateUserRequest(val email: String,
                             val username: String,
                             val firstName: String,
                             val lastName: String,
                             val password: String)