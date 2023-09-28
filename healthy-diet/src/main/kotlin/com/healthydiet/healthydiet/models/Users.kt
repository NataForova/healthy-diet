package com.healthydiet.healthydiet.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.CrudRepository
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

@Table("users")
data class Users(val email : String,
                 val username: String,
                 val firstName: String,
                 val last_name: String,
                 var password: String,
                 val isSubscribed: Boolean) {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="ID_SEQUENCE")
    var id: Long? = null
        private set
}

interface  UsersRepository : CrudRepository<Users, Int>
