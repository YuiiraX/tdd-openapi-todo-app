describe('Creating a Todo', function () {
  it('should display the todo in the list', function () {
    cy.visit('http://localhost:3000')

    cy.get('[data-testid="addButton"]')
      .click()

    cy.get('[data-testid="todoTitle"]')
      .type('New Todo')

    cy.get('[data-testid="todoDescription"]')
      .type('Get Milk')

    cy.get('[data-testid="todoText"]')
      .should('have.value', '')

    cy.contains('New todo')
  })
})

export {}
