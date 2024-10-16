# Exercise 6: Intro to the Backend App

## JavaScript

### Starting the App

1. Click on the **"Terminal"** tab

    ![Terminal tab](../images/codespace_terminal_tab.png)

2. Click on **"+"** (on the far right of the tab bar) to create a **"New Terminal Session"**.

3. If you opened the workspace from [Exercise 3](./exercise3.md), you will be presented with a list of directories. Select the **"Backend App"** option.

    ![Open Terminal in directory](../images/codespace_new_terminal_in_folder.png)

4. In the new Terminal window, type: `npm run dev`

    ![Run the Backend App](../images/backend_app_run_dev.png)

5.  When you see this message, the Backend app is now running and you can now use the To Do List Backend API.

    ![Vue App started](../images/backend_app_running.png)

6. To access the app, open `docs/todo.http` file in the code editor (just click on it).

    We will be using the [REST Client](https://marketplace.visualstudio.com/items?itemName=humao.rest-client) extension to check the Backend REST API endpoints.

    Click on the **"Send Request"** link to do the API call. The response & result will show up in a **"Response"** tab.

    ![REST Client for todo.http](../images/backend_app_rest_client1.png)

    Here are the API calls you can make:

    - `GET /todos` - will return a JSON array of To Do Items.
    - `POST /todos` - will create a new To Do Item.
    - `PUT /todos/{{task_id}}` - will update an existing To Do Item with a `task_id`.
    - `DELETE /todos/{{task_id}}` - will delete To Do Item with a `task_id`.

7. To stop the app, click into the Terminal where we started the Backend App (in step 4) and press `Ctrl` + `c`. This will stop the Backend App.

    > **Note:** Stopping the app will clear all the To Do List items in the database.

### Running the Unit Test

There are 2 ways to run the Unit Tests for the Frontend App:

#### Using the [Test Explorer](https://code.visualstudio.com/docs/editor/testing#_automatic-test-discovery-in-test-explorer).

1. Click on the **"Testing"** icon on the left sidebar.

    ![Testing icon](../images/vscode_testing_sidebar.png)

2. Open up all the tests in `Vitest` > `todo-app-js` > `test`

    ![Backend Tests](../images/vscode_test_explorer_backend_tests.png)

3. On the row with `todo-app-js` label, click on the **"Play"** button to run the unit tests.

    ![Run the unit tests for Backend app](../images/vscode_test_explorer_play_backend_tests.png)

4. You should see a bunch of green ticks to signify that all the tests are passing.

    ![Passing Backend App tests](../images/vscode_vitest_passing_backend_tests.png)

#### In the Terminal.

1. In the **"Terminal"** tab, click on **"+"** (on the far right of the tab bar) to create a **"New Terminal Session"**.

    ![Terminal tab](../images/codespace_terminal_tab.png)

2. If you opened the workspace from [Exercise 3](./exercise3.md), you will be presented with a list of directories. Select the **"Backend App"** option.

    ![Open Terminal in directory](../images/codespace_new_terminal_in_folder.png)

3. In the new Terminal window, type: `npm run test`

    ![Run the backend test](../images/vscode_terminal_backend_npm_run_test.png)

4. You should see this display if all the tests are passing:

    ![Passing backend app tests](../images/vscode_terminal_backend_passing_npm_test.png)

### Exploring the frontend app code

1. Click on the **"Explorer"** icon on the left sidebar.

    ![Explorer icon](../images/vscode_project_explorer_sidebar.png)

2. Click on **"Backend App"** to see all the folders under this workspace.

3. The application code can be found in `Backend App` directory.

    - Can you figure out how we display the the To Do list?

4. The test code can be found in `Backend App` > `test`.

    - Can you figure out what the test code mean?

## Python

### Starting the App

TODO

### Running the Unit Test

TODO

## Java

### Starting the App

TODO

### Running the Unit Test

TODO

[Next Exercise](./exercise7.md)
