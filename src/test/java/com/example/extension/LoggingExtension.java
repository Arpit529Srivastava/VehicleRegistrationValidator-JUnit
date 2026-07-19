package com.example.extension;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * Custom JUnit 5 extension that logs the start, end, and execution time of
 * every test method it is registered against.
 *
 * <p>Registered per-test-method via {@code @ExtendWith(LoggingExtension.class)}.
 */
public class LoggingExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    private static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(LoggingExtension.class);
    private static final String START_TIME_KEY = "startTime";

    @Override
    public void beforeTestExecution(ExtensionContext context) {
        long startTime = System.nanoTime();
        context.getStore(NAMESPACE).put(START_TIME_KEY, startTime);
        System.out.println("[LoggingExtension] START  -> " + testDescription(context));
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        long startTime = context.getStore(NAMESPACE).remove(START_TIME_KEY, long.class);
        long elapsedMillis = (System.nanoTime() - startTime) / 1_000_000;
        System.out.println("[LoggingExtension] END    -> " + testDescription(context)
                + " (executed in " + elapsedMillis + " ms)");
    }

    private String testDescription(ExtensionContext context) {
        return context.getRequiredTestClass().getSimpleName() + "#" + context.getRequiredTestMethod().getName();
    }
}
