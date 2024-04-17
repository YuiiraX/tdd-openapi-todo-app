import { render, screen } from '@testing-library/react'
import userEvent from '@testing-library/user-event'
import { vi } from 'vitest'
import NewTodoForm from './NewTodoForm'
import { TodoContext } from '../contexts/TodoContext'

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
    expect(screen.getByTestId('newTodoDescription')).toBeInTheDocument()
    expect(screen.getByTestId('newTodoButton')).toBeInTheDocument()
  })

  describe('when the user click the button', () => {
    it('should call the addTodo callback', async () => {
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
      await userEvent.type(screen.getByTestId('newTodoDescription'), todoDescription)
      await userEvent.click(screen.getByTestId('newTodoButton'))
      expect(addTodo).toHaveBeenCalledWith(todoDescription)
    })
    it('should show error message when the description is empty', async () => {
      const addTodo = vi.fn()

      render(
        <TodoContext.Provider value={{
          todos: [],
          addTodo
        }}>
          <NewTodoForm />
        </TodoContext.Provider>
      )
      await userEvent.click(screen.getByTestId('newTodoButton'))
      expect(addTodo).not.toHaveBeenCalled()
      expect(screen.getByText('Description is required')).toBeInTheDocument()
    })
  })
})
