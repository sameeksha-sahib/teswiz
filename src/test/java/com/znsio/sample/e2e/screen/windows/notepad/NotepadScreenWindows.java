package com.znsio.sample.e2e.screen.windows.notepad;

import com.znsio.e2e.runner.Driver;
import com.znsio.e2e.runner.Visual;
import com.znsio.sample.e2e.screen.notepad.NotepadScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class NotepadScreenWindows
        extends NotepadScreen {
    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(
            NotepadScreenWindows.class.getName());
    private static final By byEditorName = By.name("Text Editor");
    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = NotepadScreenWindows.class.getSimpleName();

    public NotepadScreenWindows(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public NotepadScreen typeMessage(String message) {
        LOGGER.info(String.format("Typing message: '%s'", message));
        driver.findElement(byEditorName).sendKeys(message);
        visually.checkWindow(SCREEN_NAME, "Typed message in Notepad");
        return this;
    }
}
