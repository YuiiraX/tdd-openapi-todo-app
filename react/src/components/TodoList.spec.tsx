import { render, screen } from '@testing-library/react'
import { vi } from 'vitest'
import TodoList from './TodoList'
import { TodoContext, TodoItem } from '../contexts/TodoContext'

describe('<TodoList />', () => {
  test('should render a list of todos', () => {
    const todos: TodoItem[] = [
      {
        description: 'Todo 1 description'
      },
      {
        description: 'Todo 2 description'
      }
    ]
    const addTodo = vi.fn()
    render(
      <TodoContext.Provider value={{
        todos,
        addTodo
      }}>
        <TodoList />
      </TodoContext.Provider>
    )
  })
})
