import { render, screen } from '@testing-library/react'
import userEvent from '@testing-library/user-event'
import { vi } from 'vitest'
import NewTodoForm from './NewTodoForm'
import { TodoContext } from '../contexts/todo.context'

describe('<NewTodoForm />', () => {
  it('should render a text input and a button', () => {
    render(
      <TodoContext.Provider value={{
        todos: [],
        addTodo: vi.fn()
      }}>
        <NewTodoForm />
      </TodoContext.Provider>
    )
    expect(screen.getByTestId('newTodoTitle')).toBeInTheDocument()
    expect(screen.getByTestId('newTodoDescription')).toBeInTheDocument()
    expect(screen.getByTestId('newTodoButton')).toBeInTheDocument()
  })

  describe('when the user click the button', () => {
    it('should call the onAddTodo callback', () => {
      const addTodo = vi.fn()
      const todoTitle = 'Todo 1'
      const todoDescription = 'Todo 1 description'

      render(
        <TodoContext.Provider value={{
          todos: [],
          addTodo
        }}>
          <NewTodoForm />
        </TodoContext.Provider>
      )
      userEvent.type(screen.getByTestId('newTodoTitle'), todoTitle)
      userEvent.type(screen.getByTestId('newTodoDescription'), todoDescription)
      userEvent.click(screen.getByTestId('newTodoButton'))
      expect(addTodo).toHaveBeenCalledWith(todoTitle, todoDescription)
    })
    it('should show error message when the title is empty', () => {
      const addTodo = vi.fn()
      const todoDescription = 'Todo 1 description'

      render(
        <TodoContext.Provider value={{
          todos: [],
          addTodo
        }}>
          <NewTodoForm />
        </TodoContext.Provider>
      )
      userEvent.type(screen.getByTestId('newTodoDescription'), todoDescription)
      userEvent.click(screen.getByTestId('newTodoButton'))
      expect(addTodo).not.toHaveBeenCalled()
      expect(screen.getByText('Title is required')).toBeInTheDocument()
    })
    it('should show error message when the description is empty', () => {
      const addTodo = vi.fn()
      const todoTitle = 'Todo 1'

      render(
        <TodoContext.Provider value={{
          todos: [],
          addTodo
        }}>
          <NewTodoForm />
        </TodoContext.Provider>
      )
      userEvent.type(screen.getByTestId('newTodoTitle'), todoTitle)
      userEvent.click(screen.getByTestId('newTodoButton'))
      expect(addTodo).not.toHaveBeenCalled()
      expect(screen.getByText('Description is required')).toBeInTheDocument()
    })
  })
})
