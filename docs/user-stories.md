# User Stories

As a user, I want to be able to create a new todo item by entering a title and description, so that I can keep track of
my tasks.

Given that I am on the todo list page, when I click the "Create Todo" button, then a form should be displayed
  allowing me to enter a title and description for my new todo item.
Given that I have entered a description for my new todo item, when I click the "Save" button, then the todo item
  should be added to my list of todo items.
Given that I have not entered a description for my new todo item, when I click the "Save" button, then an error
  message should be displayed indicating that a title is required.

As a user, I want to be able to view a list of all my todo items, so that I can see what tasks I have to complete.

Given that the user has created one or more todo items, When the user navigates to the todo list page, Then the user
should see a list of all their todo items, with the most recently created items appearing at the top of the list.

Given that the user has created one or more todo items, When the user navigates to the todo list page, Then the user
should see the title and a short description of each todo item in the list.

Given that the user has created one or more todo items, When the user navigates to the todo list page, Then the user
should see a visual indicator (such as a checkmark or a different background color) next to the items that have been
marked as complete.

As a user, I want to be able to mark a todo item as complete, so that I can keep track of my progress.

Given that I am viewing a list of todo items
When I click on the "mark as complete" button for a todo item
Then the todo item should be marked as complete and the "mark as complete" button should be replaced with a "mark as
incomplete" button
Given that I am viewing a list of todo items
When I click on the "mark as incomplete" button for a todo item
Then the todo item should be marked as incomplete and the "mark as incomplete" button should be replaced with a "mark as
complete" button
Given that I am viewing a list of todo items
When I click on the "mark as complete" button for a todo item
Then the todo item should be moved to the bottom of the list, below all the incomplete todo items
Given that I am viewing a list of todo items
When I click on the "mark as incomplete" button for a todo item
Then the todo item should be moved to the top of the list, above all the complete todo items

As a user, I want to be able to delete a todo item, so that I can remove tasks that are no longer relevant.

Given a list of todo items, when a user selects the delete button for a specific todo item, then the todo item should be
removed from the list.
Given a list of todo items, when a user selects the delete button for a specific todo item, then a confirmation message
should be displayed to confirm the deletion.
Given a list of todo items, when a user selects the delete button for a specific todo item and confirms the deletion,
then the todo item should be permanently removed from the list and not recoverable.
Given a list of todo items, when a user selects the delete button for a specific todo item and cancels the deletion,
then the todo item should remain in the list and no changes should be made.

As a user, I want to be able to edit the title and description of a todo item, so that I can update the details of a
task.

Given that I am viewing a list of my todo items, when I click on the edit button for a specific todo item, then a form
should appear allowing me to update the title and description of that item.
Given that I am viewing a form for editing a todo item, when I enter a new title and description and click the save
button, then the todo item should be updated with the new information.
Given that I am viewing a form for editing a todo item, when I click the cancel button, then the form should be closed
and the todo item should not be updated.
Given that I am viewing a form for editing a todo item, when I leave the form open for more than 30 seconds without
making any changes, then a warning message should appear asking if I want to save my changes.
Given that I am viewing a form for editing a todo item, when I click the save button and the title or description is
blank, then an error message should appear indicating that the title and description are required fields.

As a user, I want to be able to sort my todo items by title, so that I can easily find a specific task.

Given that I have a list of todo items, when I select the option to sort by title, then the todo items should be
reordered alphabetically by title.
Given that I have a list of todo items, when I select the option to sort by title, then the todo items should be sorted
in ascending order by default.
Given that I have a list of todo items, when I select the option to sort by title, then the sort order should be
persisted across sessions.
Given that I have a list of todo items, when I select the option to sort by title, then the sort order should be
immediately reflected in the user interface.
Given that I have a list of todo items, when I select the option to sort by title, then the sort order should be
reversible by selecting the option to sort in descending order.

As a user, I want to be able to filter my todo items by status (complete or incomplete), so that I can focus on a
specific set of tasks.

Given that I am on the todo list page, when I select the "incomplete" option from the filter dropdown, then I should
only see the todo items that are marked as incomplete.
Given that I am on the todo list page, when I select the "complete" option from the filter dropdown, then I should only
see the todo items that are marked as complete.
Given that I have both complete and incomplete todo items, when I select the "all" option from the filter dropdown, then
I should see all of my todo items.
Given that I have no todo items that are marked as complete, when I select the "complete" option from the filter
dropdown, then I should see a message indicating that there are no todo items that match the filter criteria.
Given that I have no todo items that are marked as incomplete, when I select the "incomplete" option from the filter
dropdown, then I should see a message indicating that there are no todo items that match the filter criteria.
