package com.znsio.teswiz.screen.calculator;

import com.znsio.teswiz.entities.Platform;
import com.znsio.teswiz.runner.Runner;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Visual;
import com.znsio.teswiz.screen.android.calculator.CalculatorScreenAndroid;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

import static com.znsio.teswiz.runner.Runner.fetchDriver;
import static com.znsio.teswiz.runner.Runner.fetchEyes;

public abstract class CalculatorScreen {
    private static final String SCREEN_NAME = CalculatorScreen.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    public static CalculatorScreen get() {
        Driver driver = fetchDriver(Thread.currentThread().getId());
        Platform platform = Runner.fetchPlatform(Thread.currentThread().getId());
        LOGGER.info(SCREEN_NAME + ": Driver type: " + driver.getType() + ": Platform: " + platform);
        Visual visually = fetchEyes(Thread.currentThread().getId());

        switch(platform) {
            case android:
                return new CalculatorScreenAndroid(driver, visually);
        }
        throw new NotImplementedException(
                SCREEN_NAME + " is not implemented in " + Runner.getPlatform());
    }

    public abstract CalculatorScreen handlePopupIfPresent();

    public abstract CalculatorScreen selectNumber(String number);

    public abstract CalculatorScreen pressOperation(String operation);
}