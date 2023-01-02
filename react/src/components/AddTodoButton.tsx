export function AddTodoButton (props: { onClick: () => void }): JSX.Element {
  return <button
    data-testid="addButton"
    onClick={props.onClick}
  >
    Add
  </button>
}
