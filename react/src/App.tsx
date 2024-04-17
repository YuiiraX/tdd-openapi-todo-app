import { useState } from 'react'
import TodoContextProvider from './contexts/TodoContext'
import NewTodoForm from './components/NewTodoForm'
import TodoList from './components/TodoList'

export default function App (): JSX.Element {
  const [isShowForm, setIsShowForm] = useState(false)

  return <>
    <TodoContextProvider>
      <h1>Todo List</h1>
      <button
        data-testid="addButton"
        onClick={() => {
          setIsShowForm(!isShowForm)
        }}
      >
        {isShowForm ? 'Hide' : 'Show'} Form
      </button>
      {isShowForm && <NewTodoForm />}
      <TodoList />
    </TodoContextProvider>
  </>
}
