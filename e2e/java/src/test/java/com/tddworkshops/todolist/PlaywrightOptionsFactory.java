package com.tddworkshops.todolist;

import com.microsoft.playwright.junit.Options;
import com.microsoft.playwright.junit.OptionsFactory;

public class PlaywrightOptionsFactory implements OptionsFactory {

  @Override
  public Options getOptions() {
    return new Options()
        .setHeadless(true)
        .setBaseUrl("http://localhost:5173");
  }
}
