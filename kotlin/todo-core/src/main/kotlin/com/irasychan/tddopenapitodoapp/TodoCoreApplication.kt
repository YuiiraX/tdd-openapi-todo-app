package com.irasychan.tddopenapitodoapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TodoCoreApplication

fun main(args: Array<String>) {
	runApplication<TodoCoreApplication>(*args)
}
