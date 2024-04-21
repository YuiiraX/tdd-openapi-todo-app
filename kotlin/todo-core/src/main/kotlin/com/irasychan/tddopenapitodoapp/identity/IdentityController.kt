package com.irasychan.tddopenapitodoapp.identity

import com.irasychan.tddopenapitodoapp.core.api.IdentityApi
import com.irasychan.tddopenapitodoapp.core.api.model.RegisterUserRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.User
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.web.bind.annotation.RestController

@RestController
class IdentityController(
    private val userDetailsManager: InMemoryUserDetailsManager
) : IdentityApi {

    override fun registerUser(registerUserRequest: RegisterUserRequest): ResponseEntity<Unit> {
        with(registerUserRequest) {
            @Suppress("DEPRECATION") // We will create a real user store in the next step
            userDetailsManager.createUser(
                User.withDefaultPasswordEncoder()
                .username(username)
                .password(password)
                .roles("USER")
                .build()
            )
        }

        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

}