describe('Creating a Todo', function () {
  it('should display new todo form when click new todo button', function () {
    cy.visit('/')

    cy.get('[data-testid="addButton"]')
      .click()

    cy.get('[data-testid="newTodoForm"]')
      .should('exist')
  })
  it('should add todo item to todo list when user entered a title and description for the new todo item', function () {
    cy.visit('/')

    cy.get('[data-testid="addButton"]')
      .click()

    cy.get('[data-testid="newTodoTitle"]')
      .type('Get Milk')

    cy.get('[data-testid="newTodoDescription"]')
      .type('Get Milk from the store')

    cy.get('[data-testid="newTodoSubmit"]')
      .click()

    cy.contains('Get Milk')
      .should('exist')
  })
  it('should display error message when creating todo item without description', function () {
    cy.visit('/')

    cy.get('[data-testid="addButton"]')
      .click()

    cy.get('[data-testid="newTodoTitle"]')
      .type('Get Milk')

    cy.get('[data-testid="newTodoSubmit"]')
      .click()

    cy.contains('Please enter a description')
      .should('exist')
  })
})

export {}
