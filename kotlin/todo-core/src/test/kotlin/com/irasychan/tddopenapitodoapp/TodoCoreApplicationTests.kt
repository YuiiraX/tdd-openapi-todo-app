package com.irasychan.tddopenapitodoapp

import com.irasychan.tddopenapitodoapp.core.api.TodoItemController
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class TodoCoreApplicationTests {
	companion object {
		@JvmStatic
		@Container
		@ServiceConnection
		val postgresql: PostgreSQLContainer<Nothing> = PostgreSQLContainer("postgres:15.2")
	}

	@Autowired
	private lateinit var todoItemController: TodoItemController


	/**
	 * This function tests whether the application context loads correctly.
	 * This is a basic sanity check to ensure that the Spring Boot application is configured correctly.
	 */
	@Test
	fun contextLoads() {
		// Assert that the todoItemController bean is not null
		assertThat(todoItemController).isNotNull()
	}
}
