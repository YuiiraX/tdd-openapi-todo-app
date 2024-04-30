package com.irasychan.tddopenapitodoapp.core.api

import com.irasychan.tddopenapitodoapp.core.model.TodoItem
import com.irasychan.tddopenapitodoapp.core.data.TodoItemRepository
import com.irasychan.tddopenapitodoapp.core.model.TodoItemStatus
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.IndicativeSentencesGeneration
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import java.time.OffsetDateTime
import java.util.*

@Testcontainers
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodoItemControllerTests {

    companion object {
        @Container
        @ServiceConnection
        val postgresql: PostgreSQLContainer<Nothing> = PostgreSQLContainer("postgres:15.2")

        const val API_BASE_PATH = "/v1"
    }

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var todoItemRepository: TodoItemRepository

    @BeforeEach
    fun beforeEach() {
        todoItemRepository.deleteAll()
    }

    @Nested
    @WithMockUser
    @DisplayName("Given authenticated")
    inner class Authenticated {
        @Nested
        @DisplayName("POST /todos")
        @IndicativeSentencesGeneration(separator = " -> ")
        inner class PostTodos {
            @Test
            fun `should return 201 on success`() {
                mockMvc.perform(
                    post("${API_BASE_PATH}/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""{"name": "Test Todo", "description": "Test Description", "status": "NOT_STARTED", "dueDate": "2022-12-31T23:59:59Z"}""")
                )
                    .andExpect(status().isCreated)
            }

            @Test
            fun `should return the created TodoItem`() {
                mockMvc.perform(
                    post("${API_BASE_PATH}/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""{"name": "Test Todo", "description": "Test Description", "status": "NOT_STARTED", "dueDate": "2022-12-31T23:59:59Z"}""")
                )
                    .andExpect(status().isCreated)
                    .andExpect(jsonPath("$.id").isString)
                    .andExpect(jsonPath("$.name").value("Test Todo"))
                    .andExpect(jsonPath("$.description").value("Test Description"))
                    .andExpect(jsonPath("$.status").value("NOT_STARTED"))
                    .andExpect(jsonPath("$.dueDate").value("2022-12-31T23:59:59Z"))
            }

            @Test
            fun `should persist the TodoItem`() {
                mockMvc.perform(
                    post("${API_BASE_PATH}/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""{"name": "Test Todo", "description": "Test Description", "status": "NOT_STARTED", "dueDate": "2022-12-31T23:59:59Z"}""")
                )
                    .andExpect(status().isCreated)

                val todoItem = todoItemRepository.findAll().first()
                assert(todoItem.name == "Test Todo")
                assert(todoItem.description == "Test Description")
                assert(todoItem.status.name == "NOT_STARTED")
                assert(todoItem.dueDate.toString() == "2022-12-31T23:59:59Z")
            }

            @Test
            fun `should return 400 on invalid input`() {
                mockMvc.perform(
                    post("${API_BASE_PATH}/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""{"name": "", "description": "Test Description", "status": "NOT_STARTED", "dueDate": "2022-12-31T23:59:59Z"}""")
                )
                    .andExpect(status().isBadRequest)
            }
        }

        @Nested
        @DisplayName("GET /todos")
        @IndicativeSentencesGeneration(separator = " -> ")
        inner class GetTodos {
            @Test
            fun `should return 200 and a list of TodoItems`() {
                // given
                // seed the database with some TodoItems
                todoItemRepository.saveAll(
                    listOf(
                        TodoItem(
                            id = UUID.fromString("00000000-0000-0000-0000-000000000001"),
                            name = "Test Todo 1",
                            description = "Test Description 1",
                            status = TodoItemStatus.NOT_STARTED,
                            dueDate = OffsetDateTime.parse("2022-12-31T23:59:59Z")
                        ),
                        TodoItem(
                            id = UUID.fromString("00000000-0000-0000-0000-000000000002"),
                            name = "Test Todo 2",
                            description = "Test Description 2",
                            status = TodoItemStatus.IN_PROGRESS,
                            dueDate = OffsetDateTime.parse("2022-12-31T23:59:59Z")
                        ),
                        TodoItem(
                            id = UUID.fromString("00000000-0000-0000-0000-000000000003"),
                            name = "Test Todo 3",
                            description = "Test Description 3",
                            status = TodoItemStatus.COMPLETED,
                            dueDate = OffsetDateTime.parse("2022-12-31T23:59:59Z")
                        )
                    )
                )
                mockMvc.perform(
                    get("${API_BASE_PATH}/todos")
                )
                    .andExpect(status().isOk)
                    .andExpect(jsonPath("$").isArray)
                    .andExpect(jsonPath("$[0].id").value("00000000-0000-0000-0000-000000000001"))
                    .andExpect(jsonPath("$[0].name").value("Test Todo 1"))
                    .andExpect(jsonPath("$[0].description").value("Test Description 1"))
                    .andExpect(jsonPath("$[0].status").value("NOT_STARTED"))
                    .andExpect(jsonPath("$[0].dueDate").value("2022-12-31T23:59:59Z"))
                    .andExpect(jsonPath("$[1].id").value("00000000-0000-0000-0000-000000000002"))
                    .andExpect(jsonPath("$[1].name").value("Test Todo 2"))
                    .andExpect(jsonPath("$[1].description").value("Test Description 2"))
                    .andExpect(jsonPath("$[1].status").value("IN_PROGRESS"))
                    .andExpect(jsonPath("$[1].dueDate").value("2022-12-31T23:59:59Z"))
                    .andExpect(jsonPath("$[2].id").value("00000000-0000-0000-0000-000000000003"))
                    .andExpect(jsonPath("$[2].name").value("Test Todo 3"))
                    .andExpect(jsonPath("$[2].description").value("Test Description 3"))
                    .andExpect(jsonPath("$[2].status").value("COMPLETED"))
                    .andExpect(jsonPath("$[2].dueDate").value("2022-12-31T23:59:59Z"))
            }

            @Test
            fun `should return 200 on success`() {
                // given
                // seed the database with a TodoItem
                todoItemRepository.save(
                    TodoItem(
                        id = UUID.fromString("00000000-0000-0000-0000-000000000001"),
                        name = "Test Todo",
                        description = "Test Description",
                        status = TodoItemStatus.NOT_STARTED,
                        dueDate = OffsetDateTime.parse("2022-12-31T23:59:59Z")
                    )
                )

                mockMvc.perform(
                    get("${API_BASE_PATH}/todos/00000000-0000-0000-0000-000000000001")
                )
                    .andExpect(status().isOk)
                    .andExpect(jsonPath("$.id").value("00000000-0000-0000-0000-000000000001"))
                    .andExpect(jsonPath("$.name").value("Test Todo"))
                    .andExpect(jsonPath("$.description").value("Test Description"))
                    .andExpect(jsonPath("$.status").value("NOT_STARTED"))
                    .andExpect(jsonPath("$.dueDate").value("2022-12-31T23:59:59Z"))
            }

            @Test
            fun `should return 404 when the TodoItem is not found`() {
                mockMvc.perform(
                    get("${API_BASE_PATH}/todos/00000000-0000-0000-0000-000000000001")
                )
                    .andExpect(status().isNotFound)
            }

            @Test
            fun `should return 400 when the id is invalid`() {
                mockMvc.perform(
                    get("${API_BASE_PATH}/todos/invalid-id")
                )
                    .andExpect(status().isBadRequest)
            }

            // Filtering test cases
            @Test
            fun `with status filter, should return todo items with status matching filter`() {
                // given
                // seed the database with some TodoItems
                todoItemRepository.saveAll(
                    listOf(
                        TodoItem(
                            id = UUID.fromString("00000000-0000-0000-0000-000000000001"),
                            name = "Test Todo 1",
                            description = "Test Description 1",
                            status = TodoItemStatus.NOT_STARTED,
                            dueDate = OffsetDateTime.parse("2022-12-31T23:59:59Z")
                        ),
                        TodoItem(
                            id = UUID.fromString("00000000-0000-0000-0000-000000000002"),
                            name = "Test Todo 2",
                            description = "Test Description 2",
                            status = TodoItemStatus.IN_PROGRESS,
                            dueDate = OffsetDateTime.parse("2022-12-31T23:59:59Z")
                        ),
                        TodoItem(
                            id = UUID.fromString("00000000-0000-0000-0000-000000000003"),
                            name = "Test Todo 3",
                            description = "Test Description 3",
                            status = TodoItemStatus.COMPLETED,
                            dueDate = OffsetDateTime.parse("2022-12-31T23:59:59Z")
                        )
                    )
                )
                mockMvc.perform(
                    get("${API_BASE_PATH}/todos?statuses=NOT_STARTED")
                )
                    .andExpect(status().isOk)
                    .andExpect(jsonPath("$").isArray)
                    .andExpect(jsonPath("$[0].id").value("00000000-0000-0000-0000-000000000001"))
                    .andExpect(jsonPath("$[0].name").value("Test Todo 1"))
                    .andExpect(jsonPath("$[0].description").value("Test Description 1"))
                    .andExpect(jsonPath("$[0].status").value("NOT_STARTED"))
                    .andExpect(jsonPath("$[0].dueDate").value("2022-12-31T23:59:59Z"))
            }

            @Test
            fun `with dueDateStart filter, should return todo items with dueDate after filter`() {
                // given
                // seed the database with some TodoItems
                todoItemRepository.saveAll(
                    listOf(
                        TodoItem(
                            id = UUID.fromString("00000000-0000-0000-0000-000000000001"),
                            name = "Test Todo 1",
                            description = "Test Description 1",
                            status = TodoItemStatus.NOT_STARTED,
                            dueDate = OffsetDateTime.parse("2022-12-30T23:59:59Z")
                        ),
                        TodoItem(
                            id = UUID.fromString("00000000-0000-0000-0000-000000000002"),
                            name = "Test Todo 2",
                            description = "Test Description 2",
                            status = TodoItemStatus.IN_PROGRESS,
                            dueDate = OffsetDateTime.parse("2022-12-31T23:59:59Z")
                        ),
                        TodoItem(
                            id = UUID.fromString("00000000-0000-0000-0000-000000000003"),
                            name = "Test Todo 3",
                            description = "Test Description 3",
                            status = TodoItemStatus.COMPLETED,
                            dueDate = OffsetDateTime.parse("2023-01-01T23:59:59Z")
                        )
                    )
                )
                mockMvc.perform(
                    get("${API_BASE_PATH}/todos?dueDateStart=2022-12-31T22:59:59Z")
                )
                    .andExpect(status().isOk)
                    .andExpect(jsonPath("$").isArray)
                    .andExpect(jsonPath("$[0].id").value("00000000-0000-0000-0000-000000000002"))
                    .andExpect(jsonPath("$[0].name").value("Test Todo 2"))
                    .andExpect(jsonPath("$[0].description").value("Test Description 2"))
                    .andExpect(jsonPath("$[0].status").value("IN_PROGRESS"))
                    .andExpect(jsonPath("$[0].dueDate").value("2022-12-31T23:59:59Z"))
            }

            @Test
            fun `with dueDateEnd filter, should return todo items with dueDate before filter`() {
                // given
                // seed the database with some TodoItems
                todoItemRepository.saveAll(
                    listOf(
                        TodoItem(
                            id = UUID.fromString("00000000-0000-0000-0000-000000000001"),
                            name = "Test Todo 1",
                            description = "Test Description 1",
                            status = TodoItemStatus.NOT_STARTED,
                            dueDate = OffsetDateTime.parse("2022-12-31T23:59:59Z")
                        ),
                        TodoItem(
                            id = UUID.fromString("00000000-0000-0000-0000-000000000002"),
                            name = "Test Todo 2",
                            description = "Test Description 2",
                            status = TodoItemStatus.IN_PROGRESS,
                            dueDate = OffsetDateTime.parse("2022-12-31T23:59:59Z")
                        ),
                        TodoItem(
                            id = UUID.fromString("00000000-0000-0000-0000-000000000003"),
                            name = "Test Todo 3",
                            description = "Test Description 3",
                            status = TodoItemStatus.COMPLETED,
                            dueDate = OffsetDateTime.parse("2022-12-30T23:59:59Z")
                        )
                    )
                )
                mockMvc.perform(
                    get("${API_BASE_PATH}/todos?dueDateEnd=2022-12-31T22:59:59Z")
                )
                    .andExpect(status().isOk)
                    .andExpect(jsonPath("$").isArray)
                    .andExpect(jsonPath("$[0].id").value("00000000-0000-0000-0000-000000000003"))
            }

            @Test
            fun `with sort by dueDate, should return todo items ordered by dueDate`() {
                // given
                // seed the database with some TodoItems
                todoItemRepository.saveAll(
                    listOf(
                        TodoItem(
                            id = UUID.fromString("00000000-0000-0000-0000-000000000001"),
                            name = "Test Todo 1",
                            description = "Test Description 1",
                            status = TodoItemStatus.NOT_STARTED,
                            dueDate = OffsetDateTime.parse("2022-12-29T23:59:59Z")
                        ),
                        TodoItem(
                            id = UUID.fromString("00000000-0000-0000-0000-000000000002"),
                            name = "Test Todo 2",
                            description = "Test Description 2",
                            status = TodoItemStatus.IN_PROGRESS,
                            dueDate = OffsetDateTime.parse("2022-12-30T23:59:59Z")
                        ),
                        TodoItem(
                            id = UUID.fromString("00000000-0000-0000-0000-000000000003"),
                            name = "Test Todo 3",
                            description = "Test Description 3",
                            status = TodoItemStatus.COMPLETED,
                            dueDate = OffsetDateTime.parse("2022-12-31T23:59:59Z")
                        )
                    )
                )
                mockMvc.perform(
                    get("${API_BASE_PATH}/todos?sort=dueDate&order=desc")
                )
                    .andExpect(status().isOk)
                    .andExpect(jsonPath("$").isArray)
                    .andExpect(jsonPath("$[0].id").value("00000000-0000-0000-0000-000000000003"))
                    .andExpect(jsonPath("$[1].id").value("00000000-0000-0000-0000-000000000002"))
                    .andExpect(jsonPath("$[2].id").value("00000000-0000-0000-0000-000000000001"))
            }

            @Test
            fun `with sort by name, should return todo items order by name`() {
                // given
                // seed the database with some TodoItems
                todoItemRepository.saveAll(
                    listOf(
                        TodoItem(
                            id = UUID.fromString("00000000-0000-0000-0000-000000000001"),
                            name = "Test Todo 1",
                            description = "Test Description 1",
                            status = TodoItemStatus.NOT_STARTED,
                            dueDate = OffsetDateTime.parse("2022-12-29T23:59:59Z")
                        ),
                        TodoItem(
                            id = UUID.fromString("00000000-0000-0000-0000-000000000002"),
                            name = "Test Todo 2",
                            description = "Test Description 2",
                            status = TodoItemStatus.IN_PROGRESS,
                            dueDate = OffsetDateTime.parse("2022-12-30T23:59:59Z")
                        ),
                        TodoItem(
                            id = UUID.fromString("00000000-0000-0000-0000-000000000003"),
                            name = "Test Todo 3",
                            description = "Test Description 3",
                            status = TodoItemStatus.COMPLETED,
                            dueDate = OffsetDateTime.parse("2022-12-31T23:59:59Z")
                        )
                    )
                )
                mockMvc.perform(
                    get("${API_BASE_PATH}/todos?sort=name&order=desc")
                )
                    .andExpect(status().isOk)
                    .andExpect(jsonPath("$").isArray)
                    .andExpect(jsonPath("$[0].id").value("00000000-0000-0000-0000-000000000003"))
                    .andExpect(jsonPath("$[1].id").value("00000000-0000-0000-0000-000000000002"))
                    .andExpect(jsonPath("$[2].id").value("00000000-0000-0000-0000-000000000001"))
            }

            @Test
            fun `with dueDateStart filter, should return todo items with no dueDate`() {
                // given
                // seed the database with some TodoItems
                todoItemRepository.saveAll(
                    listOf(
                        TodoItem(
                            id = UUID.fromString("00000000-0000-0000-0000-000000000001"),
                            name = "Test Todo 1",
                            description = "Test Description 1",
                            status = TodoItemStatus.NOT_STARTED,
                            dueDate = OffsetDateTime.parse("2022-12-29T23:59:59Z")
                        ),
                        TodoItem(
                            id = UUID.fromString("00000000-0000-0000-0000-000000000002"),
                            name = "Test Todo 2",
                            description = "Test Description 2",
                            status = TodoItemStatus.IN_PROGRESS,
                            dueDate = OffsetDateTime.parse("2022-12-30T23:59:59Z")
                        ),
                        TodoItem(
                            id = UUID.fromString("00000000-0000-0000-0000-000000000003"),
                            name = "Test Todo 3",
                            description = "Test Description 3",
                            status = TodoItemStatus.COMPLETED,
                            dueDate = null
                        )
                    )
                )
                mockMvc.perform(
                    get("${API_BASE_PATH}/todos?dueDateStart=2022-12-31T22:59:59Z")
                )
                    .andExpect(status().isOk)
                    .andExpect(jsonPath("$").isArray)
                    .andExpect(jsonPath("$[0].id").value("00000000-0000-0000-0000-000000000003"))
            }

            @Test
            fun `with dueDateEnd filter, should return todo items with no dueDate`() {
                // given
                // seed the database with some TodoItems
                todoItemRepository.saveAll(
                    listOf(
                        TodoItem(
                            id = UUID.fromString("00000000-0000-0000-0000-000000000001"),
                            name = "Test Todo 1",
                            description = "Test Description 1",
                            status = TodoItemStatus.NOT_STARTED,
                            dueDate = OffsetDateTime.parse("2023-12-29T23:59:59Z")
                        ),
                        TodoItem(
                            id = UUID.fromString("00000000-0000-0000-0000-000000000002"),
                            name = "Test Todo 2",
                            description = "Test Description 2",
                            status = TodoItemStatus.IN_PROGRESS,
                            dueDate = OffsetDateTime.parse("2023-12-30T23:59:59Z")
                        ),
                        TodoItem(
                            id = UUID.fromString("00000000-0000-0000-0000-000000000003"),
                            name = "Test Todo 3",
                            description = "Test Description 3",
                            status = TodoItemStatus.COMPLETED,
                            dueDate = null
                        )
                    )
                )
                mockMvc.perform(
                    get("${API_BASE_PATH}/todos?dueDateEnd=2022-12-31T22:59:59Z")
                )
                    .andExpect(status().isOk)
                    .andExpect(jsonPath("$").isArray)
                    .andExpect(jsonPath("$[0].id").value("00000000-0000-0000-0000-000000000003"))
            }
        }

        @Nested
        @DisplayName("PUT /todos")
        @IndicativeSentencesGeneration(separator = " -> ")
        inner class PutTodos {
            @Test
            fun `should return 200 on success with the updated TodoItem`() {
                // given
                // seed the database with a TodoItem
                todoItemRepository.save(
                    TodoItem(
                        id = UUID.fromString("00000000-0000-0000-0000-000000000001"),
                        name = "Test Todo",
                        description = "Test Description",
                        status = TodoItemStatus.NOT_STARTED,
                        dueDate = OffsetDateTime.parse("2022-12-31T23:59:59Z")
                    )
                )

                mockMvc.perform(
                    put("${API_BASE_PATH}/todos/00000000-0000-0000-0000-000000000001")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""{"name": "Updated Todo", "description": "Updated Description", "status": "IN_PROGRESS", "dueDate": "2022-12-31T23:59:59Z"}""")
                )
                    .andExpect(status().isOk)
                    .andExpect(jsonPath("$.id").value("00000000-0000-0000-0000-000000000001"))
                    .andExpect(jsonPath("$.name").value("Updated Todo"))
                    .andExpect(jsonPath("$.description").value("Updated Description"))
                    .andExpect(jsonPath("$.status").value("IN_PROGRESS"))
                    .andExpect(jsonPath("$.dueDate").value("2022-12-31T23:59:59Z"))
            }

            @Test
            fun `should return 404 when the TodoItem is not found`() {
                mockMvc.perform(
                    put("${API_BASE_PATH}/todos/00000000-0000-0000-0000-000000000001")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""{"name": "Updated Todo", "description": "Updated Description", "status": "IN_PROGRESS", "dueDate": "2022-12-31T23:59:59Z"}""")
                )
                    .andExpect(status().isNotFound)
            }

            @Test
            fun `should return 400 when the id is invalid`() {
                mockMvc.perform(
                    put("${API_BASE_PATH}/todos/invalid-id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""{"name": "Updated Todo", "description": "Updated Description", "status": "IN_PROGRESS", "dueDate": "2022-12-31T23:59:59Z"}""")
                )
                    .andExpect(status().isBadRequest)
            }

            @Test
            fun `should keep the existing value if the request body name field is empty`() {
                // given
                // seed the database with a TodoItem
                todoItemRepository.save(
                    TodoItem(
                        id = UUID.fromString("00000000-0000-0000-0000-000000000001"),
                        name = "Test Todo",
                        description = "Test Description",
                        status = TodoItemStatus.NOT_STARTED,
                        dueDate = OffsetDateTime.parse("2022-12-31T23:59:59Z")
                    )
                )

                mockMvc.perform(
                    put("${API_BASE_PATH}/todos/00000000-0000-0000-0000-000000000001")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""{"description": "Updated Description", "status": "IN_PROGRESS", "dueDate": "2022-12-31T23:59:59Z"}""")
                )
                    .andExpect(status().isOk)
                    .andExpect(jsonPath("$.id").value("00000000-0000-0000-0000-000000000001"))
                    .andExpect(jsonPath("$.name").value("Test Todo"))
                    .andExpect(jsonPath("$.description").value("Updated Description"))
                    .andExpect(jsonPath("$.status").value("IN_PROGRESS"))
                    .andExpect(jsonPath("$.dueDate").value("2022-12-31T23:59:59Z"))
            }
        }

        @Nested
        @DisplayName("DELETE /todos")
        @IndicativeSentencesGeneration(separator = " -> ")
        inner class DeleteTodos {
            @Test
            fun `should return 204 and delete the todoItem by id`() {
                // given
                // seed the database with a TodoItem
                todoItemRepository.save(
                    TodoItem(
                        id = UUID.fromString("00000000-0000-0000-0000-000000000001"),
                        name = "Test Todo",
                        description = "Test Description",
                        status = TodoItemStatus.NOT_STARTED,
                        dueDate = OffsetDateTime.parse("2022-12-31T23:59:59Z")
                    )
                )

                mockMvc.perform(
                    delete("${API_BASE_PATH}/todos/00000000-0000-0000-0000-000000000001")
                )
                    .andExpect(status().isNoContent)
            }

            @Test
            fun `should return 404 when the TodoItem is not found`() {
                mockMvc.perform(
                    delete("${API_BASE_PATH}/todos/00000000-0000-0000-0000-000000000001")
                )
                    .andExpect(status().isNotFound)
            }
        }
    }

    @Nested
    @DisplayName("Given not authenticated")
    @IndicativeSentencesGeneration(separator = " -> ")
    inner class NotAuthenticated {
        @Test
        fun `should return 401 when POST`() {
            mockMvc.perform(
                post("${API_BASE_PATH}/todos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("""{"name": "Test Todo", "description": "Test Description", "status": "NOT_STARTED", "dueDate": "2022-12-31T23:59:59Z"}""")
            )
                .andExpect(status().isUnauthorized)
        }

        @Test
        fun `should return 401 when GET`() {
            mockMvc.perform(
                get("${API_BASE_PATH}/todos")
            )
                .andExpect(status().isUnauthorized)
        }

        @Test
        fun `should return 401 when PUT`() {
            mockMvc.perform(
                put("${API_BASE_PATH}/todos/00000000-0000-0000-0000-000000000001")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("""{"name": "Updated Todo", "description": "Updated Description", "status": "IN_PROGRESS", "dueDate": "2022-12-31T23:59:59Z"}""")
            )
                .andExpect(status().isUnauthorized)
        }

        @Test
        fun `should return 401 when DELETE`() {
            mockMvc.perform(
                delete("${API_BASE_PATH}/todos/00000000-0000-0000-0000-000000000001")
            )
                .andExpect(status().isUnauthorized)
        }
    }
}