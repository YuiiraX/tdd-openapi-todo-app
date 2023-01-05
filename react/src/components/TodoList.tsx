import { TodoContext } from '../contexts/todo.context'
import { useContext } from 'react'

export default function TodoList (): JSX.Element {
  const { todos } = useContext(TodoContext)

  return (
    <ul>
      {todos.map((todo, index) =>
        <li key={index}>
          <h3>{todo.title}</h3>
          <p>{todo.description}</p>
        </li>
      )}
    </ul>
  )
}
