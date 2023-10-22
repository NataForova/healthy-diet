package com.healthydiet.healthydiet.controller

import com.healthydiet.healthydiet.service.UsersService
import com.healthydiet.healthydiet.models.request.CreateUserRequest
import com.healthydiet.healthydiet.models.Users
import com.healthydiet.healthydiet.models.request.ChangePasswordRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/users")
class UsersController(val service: UsersService) {
    @GetMapping("/")
    fun list() : List<Users> = service.findAll()

    @PostMapping("/")
    fun createUser(@RequestBody request: CreateUserRequest) : Users = service.createUser(request)

    @GetMapping("/me/")
    fun getCurrentUser() : Users = service.getCurrentUser()
    @PostMapping("/set_password/")
    fun createUser(@RequestBody request: ChangePasswordRequest) : Users = service.changePassword(request)
}