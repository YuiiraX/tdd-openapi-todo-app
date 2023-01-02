import React, { useState } from 'react'

const NewTodoForm = (
  props: {
    showForm: boolean
    onAddTodo: (todoText: string) => void
  }
): JSX.Element => {
  const [todoTitle, setTodoTitle] = useState('')
  const [todoDescription, setTodoDescription] = useState('')

  const handleAddTodo = (e: React.FormEvent<HTMLFormElement>): void => {
    e.preventDefault()
    props.onAddTodo(`${todoTitle} - ${todoDescription}`)
    setTodoTitle('')
    setTodoDescription('')
  }

  return <>
    {props.showForm &&
      <form data-testid="newTodoForm" onSubmit={handleAddTodo}>
        <input
          data-testid="newTodoTitle"
          type="text"
          value={todoTitle}
          onChange={e => setTodoTitle(e.target.value)}
        />
        <input
          data-testid="newTodoDescription"
          type="text"
          value={todoDescription}
          onChange={e => setTodoDescription(e.target.value)}
        />
        <button
          data-testid="newTodoSubmit"
          type="submit"
        >
          Add
        </button>
      </form>
    }
  </>
}

NewTodoForm.defaultProps = {
  showForm: true
}
export default NewTodoForm
