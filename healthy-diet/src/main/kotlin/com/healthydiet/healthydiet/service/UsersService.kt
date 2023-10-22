package com.healthydiet.healthydiet.service

import com.healthydiet.healthydiet.models.Users
import com.healthydiet.healthydiet.models.UsersRepository
import com.healthydiet.healthydiet.models.request.ChangePasswordRequest
import com.healthydiet.healthydiet.models.request.CreateUserRequest
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.io.IOException
import java.security.spec.KeySpec
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec


@Service
data class UsersService(val usersRepository: UsersRepository) {
    private val ALGORITHM = "PBKDF2WithHmacSHA512"
    private val ITERATIONS = 120_000
    private val KEY_LENGTH = 256
    private val SECRET = "HealthyDietSecret"
    var encoder = Argon2PasswordEncoder(32, 64, 1, 15 * 1024, 2)

    fun findAll() : List<Users> = usersRepository.findAll().toList()
    fun findById(id: Long) : Users = usersRepository.findById(id).orElseThrow {IOException("Can't find user with id $id")}
    fun findByEmail(email: String) : Users = usersRepository.findByEmail(email);

    @Transactional
    fun createUser(request: CreateUserRequest) : Users {
        val createdUser =  usersRepository.save(Users(request.email,
            request.userName,
            request.firstName,
            request.lastName,
            getHashedPassword(request.password),
        false))
        createdUser.password = ""
        return createdUser;
    }

    @Transactional
    fun updateUser(request: Users) : Users {
        request.password = getHashedPassword(request.password)
        val updatedUser = usersRepository.save(request);
        updatedUser.password = ""
        return updatedUser
    }

    fun getCurrentUser() : Users {
        val currentUserDetails = getCurrentUserFromContext()
        val email = currentUserDetails.email
        val password = currentUserDetails.password
        var user = usersRepository.findByEmail(email)
        val tmp = getHashedPassword(user.password)
        val isMatch = encoder.matches(user.password, password);
        if (isMatch) {
            throw BadCredentialsException("Wrong password!")
        }
        user.password = ""
        return user
    }

    fun changePassword(request: ChangePasswordRequest) : Users {
        val currentUserDetails = getCurrentUserFromContext()
        val email = currentUserDetails.email
        val user = usersRepository.findByEmail(email)
        val isMatch = encoder.matches(user.password, request.oldPassword)
        if (isMatch) {
            throw BadCredentialsException("Wrong password!")
        }
        user.password = getHashedPassword(request.newPassword)
        usersRepository.save(user)
        user.password = ""
        return user
    }

    fun getCurrentUserFromContext() : Users  {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        return authentication.principal as Users
    }

    fun  getHashedPassword(password: String): String {
        val encodedPassword = encoder.encode(password);
        println(encodedPassword);
        return encodedPassword;
    }

}