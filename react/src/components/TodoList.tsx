import { useTodoContext } from '../contexts/TodoContext'

export default function TodoList (): JSX.Element {
  const { todos } = useTodoContext()

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
