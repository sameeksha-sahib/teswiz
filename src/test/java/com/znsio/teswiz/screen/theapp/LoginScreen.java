package com.znsio.teswiz.screen.theapp;

import com.znsio.teswiz.entities.Platform;
import com.znsio.teswiz.runner.Runner;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Visual;
import com.znsio.teswiz.screen.android.theapp.LoginScreenAndroid;
import com.znsio.teswiz.screen.web.theapp.LoginScreenWeb;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

import static com.znsio.teswiz.runner.Runner.fetchDriver;
import static com.znsio.teswiz.runner.Runner.fetchEyes;

public abstract class LoginScreen {
    private static final String SCREEN_NAME = LoginScreen.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    public static LoginScreen get() {
        Driver driver = fetchDriver(Thread.currentThread().getId());
        Platform platform = Runner.fetchPlatform(Thread.currentThread().getId());
        LOGGER.info(SCREEN_NAME + ": Driver type: " + driver.getType() + ": Platform: " + platform);
        Visual visually = fetchEyes(Thread.currentThread().getId());

        switch(platform) {
            case android:
                return new LoginScreenAndroid(driver, visually);
            case web:
                return new LoginScreenWeb(driver, visually);
        }
        throw new NotImplementedException(
                SCREEN_NAME + " is not implemented in " + Runner.getPlatform());
    }

    public abstract LoginScreen enterLoginDetails(String username, String password);

    public abstract LoginScreen login();

    public abstract String getInvalidLoginError();

    public abstract LoginScreen dismissAlert();
}