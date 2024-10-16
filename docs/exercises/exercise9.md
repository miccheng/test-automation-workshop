# Exercise 9: About the new feature

## User Story

```Gherkin
As a User,
When I have added multiple Tasks to the To Do List,
And I have marked some Task as "Done",
Then the "Clear Completed" button should be enabled.

When I click on "Clear Completed",
Then I the "Done" Tasks should disappear from the To Do list.
```

## Technical Breakdown

- [ ] `AC-1`: **Backend** API endpoint (`POST /todos/clear-completed`) to clear completed To Do Tasks.
- [ ] `AC-2`: **"Clear Completed" button** in the **front-end** that is only enabled when there are some Tasks that are marked a "Done".
- [ ] `AC-3`: When we click on the **"Clear Completed" button**, an API call should be triggered to the Backend API (`POST /todos/clear-completed`) in `AC-1` above.
- [ ] `AC-4`: Update the UI with the updated To Do List.

[Next Exercise](./exercise10.md)
