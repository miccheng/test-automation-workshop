// @ts-check
const { test, expect } = require('@playwright/test');

const testHost = 'http://localhost:5173/';

const addItem = async (page, item) => {
  await page.fill('input', item);
  await page.keyboard.press('Enter');
}

const removeItem = async (page) => {
  await page.getByRole('button', { name: /Delete/i }).click();
}

test.afterEach(async ({ page }) => {
  const locator = page.locator('.todo-item');

  if (await locator.isVisible()) {
    await removeItem(page);
    await expect(locator).not.toBeVisible();
  }
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
