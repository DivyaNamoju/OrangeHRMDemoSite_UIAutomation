package utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {

    static Properties prop1, prop2;
    static FileReader fr1,fr2;
    static{
        prop1 = new Properties();
        prop2 = new Properties();
        try {
            fr1 = new FileReader("config.properties");
            fr2 = new FileReader("testData/TestData.properties");
            prop1.load(fr1);
            prop2.load(fr2);
        }
        catch (IOException e)
        {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public static String getConfig(String key)
    {
        return prop1.getProperty(key);
    }

    public static String getTestData(String key)
    {
        return prop2.getProperty(key);
    }

}
