import { TodoContext } from '../contexts/todo.context'
import { useContext } from 'react'

export default function TodoList (): JSX.Element {
  const { todos } = useContext(TodoContext)

  return (
    <ul data-testid="todoList">
      {todos.map((todo, index) =>
        <li key={index}>
          <span>{todo.description}</span>
        </li>
      )}
    </ul>
  )
}
