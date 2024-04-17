package com.irasychan.tddopenapitodoapp.core.domain

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import org.hibernate.proxy.HibernateProxy
import java.time.OffsetDateTime
import java.util.*

@Entity
@Table(
    name = "todo_items",
    indexes = [
        Index(name = "idx_todo_items_status", columnList = "status"),
        Index(name = "idx_todo_items_due_date", columnList = "due_date DESC")
    ],
)
data class TodoItem(
    @Id
    var id: UUID,

    var name: String,

    var description: String?,

    @Enumerated(EnumType.STRING)
    var status: TodoItemStatus,

    var dueDate: OffsetDateTime?,
) {

    @Deprecated("This constructor is used by JPA only, do not use it in the code")
    constructor() : this(
        id = UUID.randomUUID(),
        name = "",
        description = "",
        status = TodoItemStatus.NOT_STARTED,
        dueDate = null
    )

    final override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        val oEffectiveClass =
            if (other is HibernateProxy) other.hibernateLazyInitializer.persistentClass else other.javaClass
        val thisEffectiveClass =
            if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass else this.javaClass
        if (thisEffectiveClass != oEffectiveClass) return false
        other as TodoItem

        return id == other.id
    }

    final override fun hashCode(): Int =
        if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass.hashCode() else javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(  id = $id   ,   name = $name   ,   description = $description   ,   status = $status   ,   dueDate = $dueDate )"
    }
}