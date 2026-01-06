package baseClass;

import org.openqa.selenium.WebDriver;

/**
 * ThreadLocal Driver instance for seamless parallel execution and
 * driver instance availability for each TC
 */

public class DriverManager {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver driverInstance) {
        driver.set(driverInstance);
    }

    public static void unload() {
        driver.remove();
    }

}
