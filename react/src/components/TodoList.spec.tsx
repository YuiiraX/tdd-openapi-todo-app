import { render, screen } from '@testing-library/react'
import TodoList from './TodoList'

describe('<TodoList />', () => {
  it('should render a list of todos', () => {
    render(<TodoList todos={['Todo 1', 'Todo 2']} />)
    expect(screen.getByText('Todo 1')).toBeInTheDocument()
    expect(screen.getByText('Todo 2')).toBeInTheDocument()
  })
})
