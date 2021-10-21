package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {

    // こいつは1つかけばそれでいい様にしたい。
    public static Properties getLogicTextProperty(String fileName) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return properties;
    }

    // こいつを呼び出した側がどのファイルを指定してどの文言を呼んでいるのかがわかった方がバグ修正簡単だし、変更も簡単だな。
    public static String getText(String fileName, String key) {
        return getLogicTextProperty(fileName).getProperty(key);
    }
}
