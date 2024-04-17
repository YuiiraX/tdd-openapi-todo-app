package com.irasychan.tddopenapitodoapp.core

import com.irasychan.tddopenapitodoapp.core.api.TodoItemController
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodoCoreApplicationTests {
	companion object {
		@JvmStatic
		val postgresql: PostgreSQLContainer<Nothing> = PostgreSQLContainer("postgres:15.2")

		@BeforeAll
		@JvmStatic
		fun beforeAll() {
			postgresql.start()
		}

		@AfterAll
		@JvmStatic
		fun afterAll() {
			postgresql.stop()
		}

		@JvmStatic
		@DynamicPropertySource
		fun configureProperties(registry: DynamicPropertyRegistry) {
			registry.add("spring.datasource.url", postgresql::getJdbcUrl)
			registry.add("spring.datasource.username", postgresql::getUsername)
			registry.add("spring.datasource.password", postgresql::getPassword)
		}
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
