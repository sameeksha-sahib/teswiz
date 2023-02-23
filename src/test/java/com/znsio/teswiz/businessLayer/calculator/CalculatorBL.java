package com.znsio.teswiz.businessLayer.calculator;

import com.context.TestExecutionContext;
import com.znsio.teswiz.entities.Platform;
import com.znsio.teswiz.runner.Runner;
import com.znsio.teswiz.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.teswiz.screen.calculator.CalculatorScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

public class CalculatorBL {
    private static final Logger LOGGER = Logger.getLogger(CalculatorBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public CalculatorBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public CalculatorBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.getPlatform();
    }

    public CalculatorBL startCalculator() {
        CalculatorScreen.get().handlePopupIfPresent();
        return this;
    }

    public CalculatorBL selectNumber(String number) {
        CalculatorScreen.get().selectNumber(number);
        return this;
    }

    public CalculatorBL pressOperation(String operation) {
        CalculatorScreen.get().pressOperation(operation);
        return this;
    }
}
