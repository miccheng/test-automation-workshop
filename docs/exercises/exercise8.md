# Exercise 8: About the new feature

## User Story

``` Gherkin
As a User,
When I have added multiple Tasks to the To Do List,
And I have marked some Task as "Done",
Then the "Clear Completed" button should be enabled.

When I click on "Clear Completed",
Then I the "Done" Tasks should disappear from the To Do list.
```

## Technical Breakdown

1. **Backend** API endpoint to clear completed tasks.
2. **"Clear Completed" button** in the **front-end** that is only enabled when there are some Tasks that are marked a "Done".
3. When we click on the **"Clear Completed" button**, an API call should be trigger to the clear completed API endpoint in the backend.
4. Update the UI with the updated To Do List.

[Next Exercise](./exercise9.md)
