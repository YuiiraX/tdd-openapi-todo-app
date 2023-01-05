import { render, screen } from '@testing-library/react'
import { vi } from 'vitest'
import TodoList from './TodoList'
import { TodoContext } from '../contexts/todo.context'

describe('<TodoList />', () => {
  test('should render a list of todos', () => {
    const todos = [
      {
        title: 'Todo 1',
        description: 'Todo 1 description'
      },
      {
        title: 'Todo 2',
        description: 'Todo 2 description'
      }
    ]
    const addTodo = vi.fn()
    render(
      <TodoContext.Provider value={{ todos, addTodo }}>
        <TodoList />
      </TodoContext.Provider>
    )
    expect(screen.getByText('Todo 1')).toBeInTheDocument()
    expect(screen.getByText('Todo 2')).toBeInTheDocument()
  })
})
