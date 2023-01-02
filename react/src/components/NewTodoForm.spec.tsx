import { render, screen } from '@testing-library/react'
import userEvent from '@testing-library/user-event'
import NewTodoForm from './NewTodoForm'

describe('<NewTodoForm />', () => {
  it('should render a text input and a button', () => {
    render(<NewTodoForm onAddTodo={() => {}} />)
    expect(screen.getByTestId('todoText')).toBeInTheDocument()
    expect(screen.getByTestId('addButton')).toBeInTheDocument()
  })

  describe('when the user click the button', () => {
    it('should call the onAddTodo callback', () => {
      const onAddTodo = jest.fn()
      render(<NewTodoForm onAddTodo={onAddTodo} />)
      userEvent.type(screen.getByTestId('todoText'), 'New todo')
      userEvent.click(screen.getByTestId('addButton'))
      expect(onAddTodo).toHaveBeenCalledWith('New todo')
    })
  })
})
