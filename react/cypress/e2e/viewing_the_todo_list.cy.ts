describe('Viewing the Todo List', function () {
  it('should display todo list when user visits the home page', function () {
    cy.visit('/')

    cy.get('[data-testid="todoList"]')
      .should('exist')
  })
})

export {}
