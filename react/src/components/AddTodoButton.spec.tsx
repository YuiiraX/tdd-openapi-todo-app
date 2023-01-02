import { render, screen } from '@testing-library/react'
import userEvent from '@testing-library/user-event'
import { vi } from 'vitest'
import { AddTodoButton } from './AddTodoButton'

describe('<AddTodoButton />', () => {
  test('should render a button', () => {
    render(<AddTodoButton onClick={() => {
    }}/>)
    expect(screen.getByTestId('addButton')).toBeInTheDocument()
  })
  test('should call the onClick callback when the button is clicked', () => {
    const onClick = vi.fn()
    render(<AddTodoButton onClick={onClick}/>)
    userEvent.click(screen.getByTestId('addButton'))
    expect(onClick).toHaveBeenCalled()
  })
})
