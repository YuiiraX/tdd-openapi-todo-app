package com.irasychan.tddopenapitodoapp.core.domain

import org.springframework.data.repository.CrudRepository
import java.util.*

// this is in domain layer only because we are using JPA to speed up the development a bit
interface TodoItemRepository : CrudRepository<TodoItem, UUID>{
}
