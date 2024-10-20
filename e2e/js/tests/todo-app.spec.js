// @ts-check
const { test, expect } = require('@playwright/test');

const testHost = '/';

const addItem = async (page, item) => {
  await page.fill('input', item);
  await page.keyboard.press('Enter');
}

const removeItem = async (page) => {
  const itemsToDelete = await page.getByRole('button', { name: /Delete/i })

  if (await itemsToDelete.count() > 0) {
    for (const btn of await itemsToDelete.all())
      await btn.click();
  }
}

test.afterEach(async ({ page }) => {
  await removeItem(page);
  await page.waitForLoadState('networkidle')
});

test('has title', async ({ page }) => {
  await page.goto(testHost);

  // Expect a title "to contain" a substring.
  const locator = page.locator('h1');
  await expect(locator).toHaveText(/To Do List/);
});

// Write a test for adding and removing a todo item
test('add and remove todo item', async ({ page }) => {
  await page.goto(testHost);

  // Add a todo item
  await addItem(page, 'Buy milk');

  // Verify the item is added
  let locator = page.locator('.todo-item');
  await expect(locator).toHaveText(/Buy milk/);

  // Remove the item
  await removeItem(page);

  // Verify the item is removed
  locator = page.locator('.todo-item');
  await expect(locator).not.toBeVisible();
});

// Write a test to edit a todo item
test('edit todo item', async ({ page }) => {
  await page.goto(testHost);

  // Add a todo item
  await addItem(page, 'Buy milk');

  // Verify the item is added
  let locator = page.locator('.todo-item');
  await expect(locator).toHaveText(/Buy milk/);

  // Edit the item
  await page.getByRole('button', { name: /Edit/i }).click();
  await page.fill('input', 'Buy milk and eggs');
  await page.keyboard.press('Enter');

  // Verify the item is edited
  locator = page.locator('.todo-item');
  await expect(locator).toHaveText(/Buy milk and eggs/);
});

// Write test to mark a todo item as done
test('mark todo item as done', async ({ page }) => {
  await page.goto(testHost);

  // Add a todo item
  await addItem(page, 'Buy milk');

  // Verify the item is added
  let locator = page.locator('.todo-item');
  await expect(locator).toHaveText(/Buy milk/);

  // Mark the item as done
  await page.getByRole('checkbox').click();

  // Verify the item is marked as done
  locator = page.locator('.todo-item span');
  await expect(locator).toHaveClass('completed');
});

// Write test to add multiple todo items
test('add multiple todo items', async ({ page }) => {
  await page.goto(testHost);

  // Add a todo item
  await addItem(page, 'Buy milk');

  // Verify the item is added
  let locator = page.locator('.todo-item:nth-child(1)');
  await expect(locator).toHaveText(/Buy milk/);

  // Add another todo item
  await addItem(page, 'Buy eggs');

  // Verify the item is added
  locator = page.locator('.todo-item:nth-child(2)');
  await expect(locator).toHaveText(/Buy eggs/);
});

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