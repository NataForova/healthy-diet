package com.healthydiet.healthydiet.service

import com.healthydiet.healthydiet.models.Users
import com.healthydiet.healthydiet.models.request.ChangePasswordRequest
import com.healthydiet.healthydiet.models.request.CreateUserRequest
import com.healthydiet.healthydiet.repositories.UsersRepository
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.io.IOException
import java.security.MessageDigest
import java.security.SecureRandom
import java.security.spec.KeySpec
import java.util.*
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
        val (hashedPassword, salt) = getHashedPassword(request.password)
        val createdUser =  usersRepository.save(Users(request.email,
            request.userName,
            request.firstName,
            request.lastName,
            hashedPassword,
            salt,
        false))
        return createdUser;
    }

    @Transactional
    fun updateUser(request: Users) : Users {
        val (hashedPassword, salt) = getHashedPassword(request.password)
        request.password = hashedPassword
        request.salt = salt
        val updatedUser = usersRepository.save(request);
        return updatedUser
    }

    fun getCurrentUser() : Users {
        val currentUserDetails = getCurrentUserFromContext()
        val email = currentUserDetails.email
        val password = currentUserDetails.password
        var user = usersRepository.findByEmail(email)
        if (password.equals(user.password)) {
            return user
        } else  {
            throw BadCredentialsException("Wrong password!")
        }
    }

    fun changePassword(request: ChangePasswordRequest) : Users {
        val currentUserDetails = getCurrentUserFromContext()
        val user = usersRepository.findByEmail(currentUserDetails.email)
        val unHashedPassword = unSaltPassword(user.password, user.salt)
        if (unHashedPassword.equals(request.oldPassword)) {
            val (hashedPassword, salt) = getHashedPassword(request.newPassword)
            user.password = hashedPassword
            user.salt = salt
            usersRepository.save(user)
            return user
        } else {
            throw BadCredentialsException("Wrong password!")
        }
    }

    fun getCurrentUserFromContext() : Users  {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        return authentication.principal as Users
    }

    fun  getHashedPassword(password: String): Pair<String, String> {
        val salt =  generateSalt()
        val md = MessageDigest.getInstance("SHA-256")
        md.update(salt)
        val hashedPassword = md.digest(password.toByteArray())
        return hashedPassword.toString() to salt.toString()
    }

    fun generateSalt(): ByteArray {
        val random = SecureRandom()
        val salt = ByteArray(16)
        random.nextBytes(salt)
        return salt
    }

    fun unSaltPassword(hashedPassword: String, salt: String): String {
        val md = MessageDigest.getInstance("SHA-256")
        md.update(salt.toByteArray())
        val unsaltedPassword = md.digest(hashedPassword.toByteArray())
        return Base64.getEncoder().encodeToString(unsaltedPassword)
    }

}