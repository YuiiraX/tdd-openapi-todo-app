export default function TodoList (props: { todos: string[] }): JSX.Element {
  return <ul>
    {props.todos.map(todo => <li key={todo}>{todo}</li>)}
  </ul>
}
