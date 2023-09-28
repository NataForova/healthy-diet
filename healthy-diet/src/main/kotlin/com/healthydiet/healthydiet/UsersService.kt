package com.healthydiet.healthydiet

import com.healthydiet.healthydiet.models.CreateUserRequest
import com.healthydiet.healthydiet.models.UpdateUserRequest
import com.healthydiet.healthydiet.models.Users
import com.healthydiet.healthydiet.models.UsersRepository
import org.springframework.boot.autoconfigure.security.SecurityProperties.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.io.IOException

@Service
data class UsersService(val db: UsersRepository) {
    fun findAll() : List<Users> = db.findAll().toList()
    fun findById(id: Int) : Users = db.findById(id).orElseThrow {IOException("Can't find user with id $id")}

    @Transactional
    fun createUser(request: CreateUserRequest) : Users {
        val createdUser =  db.save(Users(request.email,
            request.username,
            request.firstName,
            request.lastName,
            request.password,
        false))
        createdUser.password = ""
        return createdUser;
    }

    @Transactional
    fun updateUser(request: Users) : Users {
        val updatedUser = db.save(request);
        updatedUser.password = ""
        return updatedUser
    }

}