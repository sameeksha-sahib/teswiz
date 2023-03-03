package com.znsio.teswiz.screen.web;

import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Visual;
import com.znsio.teswiz.screen.ScreenShotScreen;

import static com.znsio.teswiz.tools.Wait.waitFor;

public class ScreenShotScreenWeb
        extends ScreenShotScreen {
    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = ScreenShotScreenWeb.class.getSimpleName();

    public ScreenShotScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public ScreenShotScreen takeScreenshot() {
        visually.checkWindow(SCREEN_NAME, "Take Screenshot");
        driver.getInnerDriver().get("https://github.com/znsio/teswiz");
        waitFor(3);
        visually.checkWindow(SCREEN_NAME, "teswiz");
        return this;
    }
}
