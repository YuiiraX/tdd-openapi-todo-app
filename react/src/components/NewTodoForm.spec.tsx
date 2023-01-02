import { render, screen } from '@testing-library/react'
import userEvent from '@testing-library/user-event'
import { vi } from 'vitest'
import NewTodoForm from './NewTodoForm'

describe('<NewTodoForm />', () => {
  it('should render a text input and a button', () => {
    render(<NewTodoForm onAddTodo={() => {}} />)
    expect(screen.getByTestId('newTodoTitle')).toBeInTheDocument()
    expect(screen.getByTestId('newTodoDescription')).toBeInTheDocument()
    expect(screen.getByTestId('newTodoSubmit')).toBeInTheDocument()
  })

  describe('when the user click the button', () => {
    it('should call the onAddTodo callback', () => {
      const onAddTodo = vi.fn()
      render(<NewTodoForm onAddTodo={onAddTodo} />)
      userEvent.type(screen.getByTestId('newTodoTitle'), 'New todo')
      userEvent.type(screen.getByTestId('newTodoDescription'), 'New todo')
      userEvent.click(screen.getByTestId('newTodoSubmit'))
      expect(onAddTodo).toHaveBeenCalledWith('New todo - New todo')
    })
  })
})
