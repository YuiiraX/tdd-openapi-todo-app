package com.irasychan.tddopenapitodoapp.identity.api

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.http.MediaType
import org.springframework.security.provisioning.UserDetailsManager
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IdentityControllerTests {

    companion object {
        @Container
        @ServiceConnection
        val postgresql: PostgreSQLContainer<Nothing> = PostgreSQLContainer("postgres:15.2")

        const val API_BASE_PATH = "/v1"
    }

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var userDetailsManager: UserDetailsManager

    @Test
    @DisplayName("POST /register should return 201 Created and create a new user")
    fun `POST register should return 201 and create a new user`() {
        mockMvc.perform(post("$API_BASE_PATH/identity/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                {
                    "username": "test_user",
                    "password": "password"
                }
            """.trimIndent())
        )
            .andExpect(status().isCreated)

        assert(userDetailsManager.userExists("test_user"))
    }
}