package com.healthydiet.healthydiet.controller

import com.healthydiet.healthydiet.service.TokenService
import com.healthydiet.healthydiet.service.UsersService
import com.healthydiet.healthydiet.models.request.AuthToken
import com.healthydiet.healthydiet.models.request.LoginRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/auth")
class AuthController(private val tokenService: TokenService, private val usersService: UsersService) {
    @PostMapping("/token/login/")
    fun login(@RequestBody request: LoginRequest) : ResponseEntity<AuthToken> {
        val user = usersService.findByEmail(request.email)
        val token = tokenService.createToken(user);
        return ResponseEntity.ok(AuthToken(token));
    }
}