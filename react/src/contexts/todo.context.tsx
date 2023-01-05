import React from 'react'

export interface TodoItem {
  title: string
  description: string
}

export interface TodoContextType {
  todos: TodoItem[]
  addTodo: (title: string, description: string) => void
}

const TodoContext = React.createContext<TodoContextType>({
  todos: [],
  addTodo: () => {
  }
})

const TodoContextProvider = ({ children }: { children: React.ReactNode }): JSX.Element => {
  const [todos, setTodos] = React.useState<TodoItem[]>([])

  const addTodo = (title: string, description: string): void => {
    setTodos([...todos, {
      title,
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

export { TodoContext, TodoContextProvider }
