package com.healthydiet.healthydiet

import com.healthydiet.healthydiet.models.CreateUserRequest
import com.healthydiet.healthydiet.models.Users
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UsersController(val service: UsersService) {
    @GetMapping("/")
    fun list() : List<Users> = service.findAll()

    @PostMapping("/")
    fun tag(@RequestBody request: CreateUserRequest) : Users =service.createUser(request)
}