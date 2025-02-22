from playwright.sync_api import expect
import pytest

testHost = 'http://localhost:5173'


def addItem(page, item):
    page.fill('input', item)
    page.keyboard.press('Enter')


def removeItem(page):
    itemsToDelete = page.get_by_role('button', name='Delete')
    if itemsToDelete.count() > 0:
        for btn in itemsToDelete.all():
            btn.click()


@pytest.fixture(autouse=True)
def cleanup(page):
    yield
    removeItem(page)
    page.wait_for_load_state('networkidle')


def test_has_title(page):
    page.goto(testHost)
    locator = page.locator('h1')
    expect(locator).to_have_text('To Do List')


def test_add_and_remove_todo_item(page):
    page.goto(testHost)
    addItem(page, 'Buy milk')
    locator = page.locator('.todo-item span')
    expect(locator).to_have_text('Buy milk')
    removeItem(page)
    expect(locator).not_to_be_visible()


def test_edit_todo_item(page):
    page.goto(testHost)
    addItem(page, 'Buy milk')
    locator = page.locator('.todo-item span')
    expect(locator).to_have_text('Buy milk')
    page.get_by_role('button', name='Edit').click()
    page.fill('input', 'Buy milk and eggs')
    page.keyboard.press('Enter')
    expect(locator).to_have_text('Buy milk and eggs')


def test_mark_todo_item_as_done(page):
    page.goto(testHost)
    addItem(page, 'Buy milk')
    locator = page.locator('.todo-item span')
    expect(locator).to_have_text('Buy milk')
    page.get_by_role('checkbox').click()
    locator = page.locator('.todo-item span')
    expect(locator).to_have_class('completed')


def test_add_multiple_todo_items(page):
    page.goto(testHost)
    addItem(page, 'Buy milk')
    locator = page.locator('.todo-item:nth-child(1) span')
    expect(locator).to_have_text('Buy milk')
    addItem(page, 'Buy eggs')
    locator = page.locator('.todo-item:nth-child(2) span')
    expect(locator).to_have_text('Buy eggs')

def test_clear_completed_todo_items(page):
    page.goto(testHost)

    addItem(page, 'Buy milk')
    firstItemText = page.locator('.todo-item:nth-child(1) span')
    expect(firstItemText).to_have_text('Buy milk')

    addItem(page, 'Buy eggs')
    secondItem = page.locator('.todo-item:nth-child(2)')
    secondItemText = page.locator('.todo-item:nth-child(2) span')
    expect(secondItemText).to_have_text('Buy eggs')

    secondItem.get_by_role('checkbox').click()
    expect(secondItemText).to_have_class('completed')

    clearBtn = page.locator('.clearCompletedBtn')
    expect(clearBtn).to_be_visible()

    clearBtn.click()
    expect(secondItem).not_to_be_visible()
    expect(firstItemText).to_be_visible()
