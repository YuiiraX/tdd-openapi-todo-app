package com.irasychan.tddopenapitodoapp.core.domain

import org.springframework.data.repository.CrudRepository
import java.util.*

/**
 * Repository for [TodoItem] entities.
 * @see TodoItem
 *
 * I did not separate the domain repository interface from the Spring Data JPA repository interface
 * because we are only providing a CRUD repository for the [TodoItem] entity.
 * This is done to speed up the development process a bit.
 */
interface TodoItemRepository : CrudRepository<TodoItem, UUID>
