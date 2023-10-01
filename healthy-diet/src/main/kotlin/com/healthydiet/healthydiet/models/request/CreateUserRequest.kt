package com.healthydiet.healthydiet.models.request

import com.fasterxml.jackson.annotation.JsonAlias

open class CreateUserRequest(val email: String,
                             @JsonAlias("username")
                             val userName: String,
                             @JsonAlias("first_name")
                             val firstName: String,
                             @JsonAlias("last_name")
                             val lastName: String,
                             val password: String)