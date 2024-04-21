package com.irasychan.tddopenapitodoapp.core.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*

class TodoItemTest {

    @Test
    fun `equals returns true when comparing same instance`() {
        val todoItem = TodoItem(
            id = UUID.randomUUID(),
            name = "name",
            description = "description",
            status = TodoItemStatus.NOT_STARTED,
            dueDate = null
        )
        @Suppress("ReplaceCallWithBinaryOperator")
        assertTrue(todoItem.equals(todoItem))
    }

    @Test
    fun `equals returns false when comparing to null`() {
        val todoItem = TodoItem(
            id = UUID.randomUUID(),
            name = "name",
            description = "description",
            status = TodoItemStatus.NOT_STARTED,
            dueDate = null
        )
        assertFalse(todoItem.equals(null))
    }

    @Test
    fun `equals returns false when comparing to different class`() {
        val todoItem = TodoItem(
            id = UUID.randomUUID(),
            name = "name",
            description = "description",
            status = TodoItemStatus.NOT_STARTED,
            dueDate = null
        )
        @Suppress("ReplaceCallWithBinaryOperator")
        assertFalse(todoItem.equals(Object()))
    }

    @Test
    fun `equals returns true when comparing to same class with same id`() {
        val id = UUID.randomUUID()
        val todoItem1 = TodoItem(
            id = id,
            name = "name",
            description = "description",
            status = TodoItemStatus.NOT_STARTED,
            dueDate = null
        )
        val todoItem2 = TodoItem(
            id = id,
            name = "name",
            description = "description",
            status = TodoItemStatus.NOT_STARTED,
            dueDate = null
        )
        @Suppress("ReplaceCallWithBinaryOperator")
        assertTrue(todoItem1.equals(todoItem2))
    }

    @Test
    fun `equals returns false when comparing to same class with different id`() {
        val todoItem1 = TodoItem(
            id = UUID.randomUUID(),
            name = "name",
            description = "description",
            status = TodoItemStatus.NOT_STARTED,
            dueDate = null
        )
        val todoItem2 = TodoItem(
            id = UUID.randomUUID(),
            name = "name",
            description = "description",
            status = TodoItemStatus.NOT_STARTED,
            dueDate = null
        )
        @Suppress("ReplaceCallWithBinaryOperator")
        assertFalse(todoItem1.equals(todoItem2))
    }

    @Test
    fun `hashCode returns consistent result for same instance`() {
        val todoItem = TodoItem(
            id = UUID.randomUUID(),
            name = "name",
            description = "description",
            status = TodoItemStatus.NOT_STARTED,
            dueDate = null
        )
        assertEquals(todoItem.hashCode(), todoItem.hashCode())
    }

    @Test
    fun `hashCode returns consistent result for different instances with same id`() {
        val todoItem1 = TodoItem(
            id = UUID.randomUUID(),
            name = "name",
            description = "description",
            status = TodoItemStatus.NOT_STARTED,
            dueDate = null
        )
        val todoItem2 = TodoItem(
            id = UUID.randomUUID(),
            name = "name",
            description = "description",
            status = TodoItemStatus.NOT_STARTED,
            dueDate = null
        )
        assertEquals(todoItem1.hashCode(), todoItem2.hashCode())
    }

    @Test
    fun `toString returns correct format`() {
        val id = UUID.randomUUID()
        val todoItem = TodoItem(
            id = id,
            name = "name",
            description = "description",
            status = TodoItemStatus.NOT_STARTED,
            dueDate = null
        )
        val expectedString =
            "TodoItem(  id = $id   ,   name = name   ,   description = description   ,   status = NOT_STARTED   ,   dueDate = null )"
        assertEquals(expectedString, todoItem.toString())
    }
}