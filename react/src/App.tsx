import { useState } from 'react'
import NewTodoForm from './components/NewTodoForm'
import TodoList from './components/TodoList'

export default function App (): JSX.Element {
  const [todos, setTodos] = useState<string[]>([])
  const handleAddTodo = (todoText: string): void => {
    console.log(todoText)
    setTodos([...todos, todoText])
  }

  return (
    <div>
      <NewTodoForm onAddTodo={handleAddTodo}/>
      <TodoList todos={todos}/>
    </div>
  )
}
