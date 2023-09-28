package com.healthydiet.healthydiet.models

class UpdateUserRequest(id: String, email: String, username: String, firstName: String, lastName: String, password: String) :
    CreateUserRequest(email, username, firstName, lastName, password) {
}