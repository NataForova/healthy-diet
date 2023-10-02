package com.healthydiet.healthydiet.service

import com.healthydiet.healthydiet.models.Users
import com.healthydiet.healthydiet.models.UsersRepository
import com.healthydiet.healthydiet.models.request.CreateUserRequest
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.io.IOException


@Service
data class UsersService(val usersRepository: UsersRepository) {
    fun findAll() : List<Users> = usersRepository.findAll().toList()
    fun findById(id: Long) : Users = usersRepository.findById(id).orElseThrow {IOException("Can't find user with id $id")}
    fun findByEmail(email: String) : Users = usersRepository.findByEmail(email);

    @Transactional
    fun createUser(request: CreateUserRequest) : Users {
        val createdUser =  usersRepository.save(Users(request.email,
            request.userName,
            request.firstName,
            request.lastName,
            request.password,
        false))
        createdUser.password = ""
        return createdUser;
    }

    @Transactional
    fun updateUser(request: Users) : Users {
        val updatedUser = usersRepository.save(request);
        updatedUser.password = ""
        return updatedUser
    }

    fun getCurrentUser() : Users {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        val currentUserDetails = authentication.principal as Users
        val email = currentUserDetails.email
        val password = currentUserDetails.password
        var user = usersRepository.findByEmail(email)
        if (user.password != password) {
            throw BadCredentialsException("Wrong password!")
        }
        user.password = ""
        return user
    }

}