import { useState } from 'react'
import NewTodoForm from './components/NewTodoForm'
import TodoList from './components/TodoList'
import { AddTodoButton } from './components/AddTodoButton'

export default function App (): JSX.Element {
  const [todos, setTodos] = useState<string[]>([])
  const [showForm, setShowForm] = useState(false)
  const handleAddTodo = (todoText: string): void => {
    console.log(todoText)
    setTodos([...todos, todoText])
  }

  return (
    <div>
      <AddTodoButton onClick={() => setShowForm(!showForm)}/>
      <NewTodoForm showForm={showForm} onAddTodo={handleAddTodo}/>
      <TodoList todos={todos}/>
    </div>
  )
}
