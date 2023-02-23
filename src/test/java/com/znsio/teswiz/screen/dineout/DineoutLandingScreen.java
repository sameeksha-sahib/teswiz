package com.znsio.teswiz.screen.dineout;

import com.znsio.teswiz.entities.Platform;
import com.znsio.teswiz.runner.Runner;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Visual;
import com.znsio.teswiz.screen.android.dineout.DineoutLandingScreenAndroid;
import com.znsio.teswiz.screen.web.dineout.DineoutLandingScreenWeb;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

import static com.znsio.teswiz.runner.Runner.fetchDriver;
import static com.znsio.teswiz.runner.Runner.fetchEyes;

public abstract class DineoutLandingScreen {
    private static final String SCREEN_NAME = DineoutLandingScreen.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    public static DineoutLandingScreen get() {
        Driver driver = fetchDriver(Thread.currentThread().getId());
        Platform platform = Runner.fetchPlatform(Thread.currentThread().getId());
        LOGGER.info(SCREEN_NAME + ": Driver type: " + driver.getType() + ": Platform: " + platform);
        Visual visually = fetchEyes(Thread.currentThread().getId());

        switch(platform) {
            case android:
                return new DineoutLandingScreenAndroid(driver, visually);
            case web:
                return new DineoutLandingScreenWeb(driver, visually);
        }
        throw new NotImplementedException(
                SCREEN_NAME + " is not implemented in " + Runner.getPlatform());
    }

    public abstract DineoutLandingScreen selectDefaultCity();

    public abstract DineoutLandingScreen selectCity(String city);

    public abstract DineoutLandingScreen searchCuisine(String cusine);
}