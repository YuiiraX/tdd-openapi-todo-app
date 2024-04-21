package com.irasychan.tddopenapitodoapp.identity.api

import com.irasychan.tddopenapitodoapp.core.api.IdentityApi
import com.irasychan.tddopenapitodoapp.core.api.model.RegisterUserRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.User
import org.springframework.security.provisioning.UserDetailsManager
import org.springframework.web.bind.annotation.RestController

@RestController
class IdentityController(
    private val userDetailsManager: UserDetailsManager
) : IdentityApi {

    override fun registerUser(registerUserRequest: RegisterUserRequest): ResponseEntity<Unit> {
        with(registerUserRequest) {
            userDetailsManager.createUser(
                User.withUsername(username)
                    .password(password)
                    .roles("USER")
                    .build()
            )
        }

        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

}