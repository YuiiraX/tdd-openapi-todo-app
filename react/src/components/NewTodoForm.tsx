import { useState } from 'react'

export default function NewTodoForm (
  props: { onAddTodo: (todoText: string) => void }
): JSX.Element {
  const [todoText, setTodoText] = useState('')

  const handleAddTodo = (): void => {
    props.onAddTodo(todoText)
    setTodoText('')
  }

  return (
    <div>
      <input
        data-testid="todoText"
        type="text"
        value={todoText}
        onChange={e => setTodoText(e.target.value)}
      />
      <button
        data-testid="addButton"
        onClick={handleAddTodo}
      >
        Add
      </button>
    </div>
  )
}
