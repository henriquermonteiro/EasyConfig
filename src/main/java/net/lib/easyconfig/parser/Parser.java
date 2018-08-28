package net.lib.easyconfig.parser;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.lib.easyconfig.EasyConfig;
import net.lib.easyconfig.reader.Reader;

/**
 *
 * @author henri
 */
public final class Parser {

    private final Reader reader;

    private final Properties properties;
    private int lineCount;

    public Parser(Reader reader) {
        this.reader = reader;

        lineCount = 1;
        properties = new Properties();
    }

    private void parseLine(String line) {
        line = line.trim();

        if (line.length() == 0 || line.charAt(0) == '#') {
            return;
        }

        if (line.trim().length() > 1) {
            if (line.contains(":")) {
                String[] splitted = line.split(":");
                int index = 2;
                
                String value;
                
                if(splitted.length > 1){
                    value = splitted[1].trim();
                    
                    while (value.charAt(value.length() - 1) == '/') {
                        value = value.substring(0, value.length() - 1).concat(":" + splitted[index++]);
                    }

                    value = value.trim();
                }else{
                    value = "";
                }
                

                if(properties.containsKey(splitted[0])){
                    properties.put(splitted[0].trim(), value);
                    Logger.getLogger(EasyConfig.class.getName()).log(Level.WARNING, "Attribute overriden ({0}) = {1}", new Object[]{splitted[0].trim(), value});
                }else{
                    properties.put(splitted[0].trim(), value);
                    Logger.getLogger(EasyConfig.class.getName()).log(Level.INFO, "Attribute set ({0}) = {1}", new Object[]{splitted[0].trim(), value});
                }
            }else{
                // TODO - Call actions
//                Logger.getLogger(EasyConfig.class.getName()).log(Level.INFO, "Action called: {0}", new Object[]{line});
                // Warn invalid lines
                Logger.getLogger(EasyConfig.class.getName()).log(Level.WARNING, "Invalid line detected ({0}): {1}", new Object[]{lineCount, line});
            }
        }
    }

    public void parse() {
        while (reader.hasMoreLines()) {
            parseLine(reader.getLine());
            lineCount ++;
        }
    }

    public Properties getProperties() {
        return properties;
    }

}
