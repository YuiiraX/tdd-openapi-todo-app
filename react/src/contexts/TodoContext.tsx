import React, { useEffect } from 'react'

export interface TodoItem {
  description: string
}

export interface TodoContextType {
  todos: TodoItem[]
  addTodo: (description: string) => void
}

export const TodoContext = React.createContext<TodoContextType>({
  todos: [],
  addTodo: () => {
  }
})

const TodoContextProvider = ({ children }: { children: React.ReactNode }): JSX.Element => {
  const [todos, setTodos] = React.useState<TodoItem[]>([])

  const addTodo = (description: string): void => {
    setTodos([...todos, {
      description
    }])
  }

  return (
    <TodoContext.Provider value={{
      todos,
      addTodo
    }}>
      {children}
    </TodoContext.Provider>
  )
}

export const useTodoContext = (): TodoContextType => React.useContext(TodoContext)

export default TodoContextProvider
