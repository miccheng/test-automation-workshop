package com.tddworkshops.todolist;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import com.microsoft.playwright.junit.UsePlaywright;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@UsePlaywright(PlaywrightOptionsFactory.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class TodoAppTest {

  private static final String TEST_HOST = "/";

  @AfterEach
  void teardown(Page page) {
    removeItem(page);
    page.waitForLoadState(LoadState.NETWORKIDLE);
  }

  @Test
  @Order(1)
  void has_title(Page page) {
    page.navigate(TEST_HOST);
    var locator = page.locator("h1");
    assertThat(locator).hasText("To Do List");
  }

  @Test
  @Order(2)
  void add_and_remove_todo_item(Page page) {
    page.navigate(TEST_HOST);
    addItem(page, "Buy milk");
    var locator = page.locator(".todo-item");
    assertThat(locator).containsText("Buy milk");
    removeItem(page);
    locator = page.locator(".todo-item");
    assertThat(locator).not().isVisible();
  }

  @Test
  @Order(3)
  void edit_todo_item(Page page) {
    page.navigate(TEST_HOST);
    addItem(page, "Buy milk");
    var locator = page.locator(".todo-item");
    assertThat(locator).containsText("Buy milk");
    page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Edit")).click();
    page.fill("input", "Buy milk and eggs");
    page.keyboard().press("Enter");

    locator = page.locator(".todo-item");
    assertThat(locator).containsText("Buy milk and eggs");
  }

  @Test
  @Order(4)
  void mark_todo_item_as_done(Page page) {
    page.navigate(TEST_HOST);
    addItem(page, "Buy milk");
    var locator = page.locator(".todo-item");
    assertThat(locator).containsText("Buy milk");
    page.getByRole(AriaRole.CHECKBOX).click();
    locator = page.locator(".todo-item span");
    assertThat(locator).hasClass("completed");
  }
  
  @Test
  @Order(5)
  void add_multiple_todo_items(Page page) {
    page.navigate(TEST_HOST);
    addItem(page, "Buy milk");
    var locator = page.locator(".todo-item:nth-child(1)");
    assertThat(locator).containsText("Buy milk");
    addItem(page, "Buy eggs");
    locator = page.locator(".todo-item:nth-child(2)");
    assertThat(locator).containsText("Buy eggs");
  }
  
  @Test
  @Order(6)
  void check_mark_completed(Page page) {
    page.navigate(TEST_HOST);
    
    addItem(page, "Buy milk");
    var firstItem = page.locator(".todo-item:nth-child(1)");
    assertThat(firstItem).containsText("Buy milk");
    
    addItem(page, "Buy eggs");
    var secondItem = page.locator(".todo-item:nth-child(2)");
    assertThat(secondItem).containsText("Buy eggs");
    secondItem.getByRole(AriaRole.CHECKBOX).click();

    var secondItemStatus = secondItem.locator("span");
    assertThat(secondItemStatus).hasClass("completed");

    var clearBtn = page.locator(".clearCompletedBtn");
    assertThat(clearBtn).isVisible();

    clearBtn.click();

    assertThat(secondItem).not().isVisible();
    assertThat(firstItem).isVisible();
  }

  private void addItem(Page page, String item) {
    page.fill("input", item);
    page.keyboard().press("Enter");
  }

  private void removeItem(Page page) {
    Locator items =  page.getByRole(
        AriaRole.BUTTON,
        new Page.GetByRoleOptions().setName(Pattern.compile("Delete", Pattern.CASE_INSENSITIVE)));
    if (items.count() > 0) {
      for (Locator btn : items.all()) {
        btn.click();
      }
    }
  }
}
