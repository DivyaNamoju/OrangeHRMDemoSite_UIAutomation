package baseClass;

import utils.ReadProperties;

/**
 * Enviroment paramaters
 */

public class EnvParams {

    public static final String ENV= ReadProperties.getConfig("env");
    public static final String BROWSER= ReadProperties.getConfig("browser");
    public static final String EXECUTION_ENV= ReadProperties.getConfig("execution.env");

}
