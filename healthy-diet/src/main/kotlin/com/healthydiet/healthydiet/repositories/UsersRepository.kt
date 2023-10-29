package com.healthydiet.healthydiet.repositories

import com.healthydiet.healthydiet.models.Users
import org.springframework.data.repository.CrudRepository

interface  UsersRepository : CrudRepository<Users, Long> {
    fun findByEmail(email: String) : Users
}
