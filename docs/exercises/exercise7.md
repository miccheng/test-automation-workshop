# Exercise 7: Running the To Do List app

## Update Frontend App to use the Backend App

1. Select the **"Ports"** tab in the bottom panel:

    ![Exposed Ports](../images/codespace_exposed_ports.png)

2. Right click on Port `3000` and select **""**

    ![Copy Local Address](../images/codespace_ports_copy_local_address.png)

    This copies the public URL of the Backend App to your clipboard.

3. Open the **"Frontend App"** workspace and open the file: `src/App.vue`

4. Replace `http://localhost:3000` with the public URL of the Backend App.

    - Select `http://localhost:3000` in Line 31.
    - Right click and select **"Paste"**. (or just `Ctrl` + `v` (Windows) / `Cmd` + `v` (MacOS)).
    - Remove the trailing slash (`/`) from the URL.

    ![Replace with public URL](../images/frontend_app_update_backend_host.png)

    It should look like the text in line 32. 

5. Restart the **"Frontend App"** and **"Backend App"**.

6. Open the **"Frontend App"** public URL in your browser.

7. You should be able to interact with your To Do List app now.

    ![Walk through](../images/todolist-app-walkthru.gif)

    > **Note:** Remember to delete all the To Do List Items before you run the E2E tests below. <br> (**Pro-Tip**: Try restarting the **"Backend App"**.)

[Next Exercise](./exercise8.md)
