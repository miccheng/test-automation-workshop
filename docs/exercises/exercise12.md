# Exercise 12: Updating the End to End Test

Now that we have a working frontend and backend implementation of **"Clear Completed"**, let's add some End-to-End test scenarios to complete the last user story:

- [ ] `AC-4`: Update the UI with the updated To Do List.

## JavaScript

### Update the End to End Test

1. Open the **"End-to-End Tests"** workspace.

2. Open the E2E test file: `tests` > `todo-app.spec.js`.

3. Add this code to the end of the file:

    ```javascript
    test('clear completed To Do items', async ({ page }) => {
      await page.goto(testHost);

      // Add a todo item
      await addItem(page, 'Buy milk');

      // Verify the item is added
      const firstItem = page.locator('.todo-item:nth-child(1)');
      await expect(firstItem).toHaveText(/Buy milk/);

      // Add another todo item
      await addItem(page, 'Buy eggs');

      // Verify the item is added
      const secondItem = page.locator('.todo-item:nth-child(2)');
      await expect(secondItem).toHaveText(/Buy eggs/);

      // Clicks the done checkbox for the 2nd item
      await secondItem.getByRole('checkbox').click();
      const secondItemText = secondItem.locator('span');
      await expect(secondItemText).toHaveClass('completed');

      let clearBtn = page.locator('.clearCompletedBtn'); 
      await expect(clearBtn).toBeVisible();

      // Clicks the Clear Completed button
      await clearBtn.click();

      // Ensure that the 2nd item has been cleared
      await expect(secondItem).not.toBeVisible();
      await expect(firstItem).toBeVisible();
    });
    ```

    It should look like this:

    ![](../images/exercise12/js_e2e1.png)

4. Click the **"Play"** button in the left gutter next to the test function to trigger this end to end test.

    > **Note:** Make sure the **"Frontend App"** and **"Backend App"** are running before you run this test.

## Python

### Updating the Code

TODO

## Java

### Updating the Code

TODO

[Next Exercise](./exercise13.md)
