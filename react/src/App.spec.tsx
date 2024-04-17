import App from './App'
import { render } from '@testing-library/react'
``
describe('App', () => {
  test('should render', () => {
    render(<App />)
  })
})
