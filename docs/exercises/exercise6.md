# Exercise 6: Intro to the Backend App

Skip to the portion for your preferred programming language:

- [JavaScript](#javascript)
- [Python](#python)
- [Java](#java)

<details>
<summary>JavaScript</summary>

## JavaScript

### Starting the App

1. Click on the **"Terminal"** tab

   ![Terminal tab](../images/codespace_terminal_tab.png)

2. Click on **"+"** (on the far right of the tab bar) to create a **"New Terminal Session"**.

3. If you opened the workspace from [Exercise 3](./exercise3.md), you will be presented with a list of directories. Select the **"Backend App"** option.

   ![Open Terminal in directory](../images/codespace_new_terminal_in_folder.png)

4. In the new Terminal window, type: `npm run dev`

   ![Run the Backend App](../images/backend_app_run_dev.png)

5. When you see this message, the Backend app is now running and you can now use the To Do List Backend API.

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

There are 2 ways to run the Unit Tests for the **"Backend App"**:

- Using the [Test Explorer](https://code.visualstudio.com/docs/editor/testing#_automatic-test-discovery-in-test-explorer).
  - This method is convenient when you are using VSCode and you can use your mouse to click on the test button.
- In the Terminal.
  - This method is recommended when you are running the test in Continuous Integration (CI) pipelines.

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

### Exploring the backend app code

1. Click on the **"Explorer"** icon on the left sidebar.

   ![Explorer icon](../images/vscode_project_explorer_sidebar.png)

2. Click on **"Backend App"** to see all the folders under this workspace.

3. The application code can be found in `Backend App` directory.

   - Can you figure out how we display the To Do list?

4. The test code can be found in `Backend App` > `test`.

   - Can you figure out what the test code mean?

</details>

<details>
<summary>Python</summary>

## Python

### Starting the App

1. Click on the **"Terminal"** tab

   ![Terminal tab](../images/codespace_terminal_tab.png)

2. Click on **"+"** (on the far right of the tab bar) to create a **"New Terminal Session"**.

3. If you opened the workspace from [Exercise 3](./exercise3.md), you will be presented with a list of directories. Select the **"Backend App"** option.

   ![Open Terminal in directory](../images/codespace_new_terminal_in_folder.png)

4. In the new Terminal window, type: `make run`

   ![Run the Backend App](../images/backend_app_python_make_run.png)

5. When you see this message, the Backend app is now running and you can now use the To Do List Backend API.

   ![Vue App started](../images/backend_app_python_uvicorn_running.png)

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

2. First, we need to configure the Python testing in VSCode.

   Press `ctrl` + `shift` + `p` (windows) / `cmd` + `shift` + `p` (macOS) to bring up the [Command Palette](https://code.visualstudio.com/api/ux-guidelines/command-palette).

   Next, type: `pytest`

   You should see this option show up in the Command Palette:

   ![Command Palette - configure Python Testing](../images/backend_app_python_configure_test.png)

3. In the list of workspaces, select the **"Backend App"**.

   ![Select a workspace](../images/backend_app_python_configure_test_select_workspace.png)

4. Next select the **"pytest"** Test Framework

   ![Select test framework](../images/backend_app_python_configure_test_select_test_framework.png)

5. Next select the **"Root directory"** as the directory containing the tests.

   ![Select test directory](../images/backend_app_python_configure_test_select_directory.png)

6. You should see the Python tests appear in the **"Test Explorer"**.

   ![Pytest suite for backend](../images/backend_app_python_test_explorer.png)

7. Open up all the tests in `Python Tests` > `todo-app-python` > `test_todos.py`

8. On the row with `todo-app-python` label, click on the **"Play"** button to run the unit tests.

9. You should see a bunch of green ticks to signify that all the tests are passing.

   ![Passing Backend App tests](../images/backend_app_python_run_tests.png)

#### In the Terminal.

1. In the **"Terminal"** tab, click on **"+"** (on the far right of the tab bar) to create a **"New Terminal Session"**.

   ![Terminal tab](../images/codespace_terminal_tab.png)

2. If you opened the workspace from [Exercise 3](./exercise3.md), you will be presented with a list of directories. Select the **"Backend App"** option.

   ![Open Terminal in directory](../images/codespace_new_terminal_in_folder.png)

3. In the new Terminal window, type: `make test`

   ![Run the backend test](../images/backend_app_python_make_test.png)

4. You should see this display if all the tests are passing:

   ![Passing backend app tests](../images/backend_app_python_make_test_result.png)

### Exploring the backend app code

1. Click on the **"Explorer"** icon on the left sidebar.

   ![Explorer icon](../images/vscode_project_explorer_sidebar.png)

2. Click on **"Backend App"** to see all the folders under this workspace.

3. The application code can be found in `Backend App` directory.

   - Can you figure out how we display the To Do list?

4. The test code can be found in `Backend App` > `test_todos.py`.

   - Can you figure out what the test code mean?

</details>

<details>
<summary>Java</summary>

## Java

### Starting the App

1. Open the **"Backend App"** workspace.

2. Open this file: `src/main/java/com/tddworkshops/todolist/TodolistApplication.java`

3. Click on **"Run"** at the top right hand corner.

   ![Run Java app](../images/vscode_java_run_todolist.png)

4. If you see this option, its due to Gradle importing all the dependencies.

   ![Opening Java Project](../images/vscode_java_opening_java_project.png)

5. If you see these text in th your terminal, the **"Backend App"** is running now.

   ![Spring ASCII art](../images/vscode_java_run_todolist_spring.png)

   ![Spring Boot Running](../images/vscode_java_run_todolist_initialized.png)

6. To access the app, open `docs/todo.http` file in the code editor (just click on it).

   We will be using the [REST Client](https://marketplace.visualstudio.com/items?itemName=humao.rest-client) extension to check the Backend REST API endpoints.

   Click on the **"Send Request"** link to do the API call. The response & result will show up in a **"Response"** tab.

   ![REST Client for todo.http](../images/backend_app_rest_client1.png)

   Here are the API calls you can make:

   - `GET /todos` - will return a JSON array of To Do Items.
   - `POST /todos` - will create a new To Do Item.
   - `PUT /todos/{{task_id}}` - will update an existing To Do Item with a `task_id`.
   - `DELETE /todos/{{task_id}}` - will delete To Do Item with a `task_id`.

7. To stop the app, click into the **"Stop button"**. This will stop the Backend App.

   ![Stop button](../images/vscode_java_stop_app.png)

   > **Note:** Stopping the app will clear all the To Do List items in the database.

### Running the Unit Test

1. Click on the **"Testing"** icon on the left sidebar.

   ![Testing icon](../images/vscode_testing_sidebar.png)

2. Open up all the tests in `Java Test` > `todolist-todo-app-java`

   ![Backend Tests](../images/vscode_test_explorer_java.png)

3. On the row with `todolist-todo-app-java` label, click on the **"Play"** button to run the unit tests.

4. You should see a bunch of green ticks to signify that all the tests are passing.

   ![Passing Backend App tests](../images/vscode_test_explorer_java_backend_passing.png)

### Exploring the backend app code

1. Click on the **"Explorer"** icon on the left sidebar.

   ![Explorer icon](../images/vscode_project_explorer_sidebar.png)

2. Click on **"Backend App"** to see all the folders under this workspace.

   ![Java Project Explorer](../images/backend_java_project_explorer.png)

3. The application code can be found in `main` directory.

   - Can you figure out how we display the To Do list?

4. The test code can be found in `test` directory.

   - Can you figure out what the test code mean?

</details>

[Next Exercise](./exercise7.md)
