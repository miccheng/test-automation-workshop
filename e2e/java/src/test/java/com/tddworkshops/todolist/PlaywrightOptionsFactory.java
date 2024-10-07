package com.tddworkshops.todolist;

import com.microsoft.playwright.junit.Options;
import com.microsoft.playwright.junit.OptionsFactory;

public class PlaywrightOptionsFactory implements OptionsFactory {

  @Override
  public Options getOptions() {
    return new Options()
        .setHeadless(false)
        .setBaseUrl("http://localhost:5173");
  }
}
