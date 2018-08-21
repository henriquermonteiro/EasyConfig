package net.lib.easyconfig;

import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.lib.easyconfig.parser.Parser;
import net.lib.easyconfig.reader.Reader;

/**
 *
 * @author henri
 */
public class EasyConfig {
    private final String source;
    private final Reader reader;
    private final Parser parser;
    private final Properties model;

    public EasyConfig(String source) throws FileNotFoundException {
        this(source, null);
    }
    
    public EasyConfig(String source, Properties model) throws FileNotFoundException {
        this.source = source;
        
        this.model = model;
        
        reader = new Reader(source);
        parser = new Parser(reader);
    }
    
    public Properties getConfigs(boolean strict){
        Logger.getLogger(EasyConfig.class.getName()).log(Level.INFO, "Parsing started: file ({0})", source);
        parser.parse();
        Logger.getLogger(EasyConfig.class.getName()).info("Parsing completed");
        
        reader.close();
        
        return (model != null ? (Validator.validate(model, parser.getProperties(), strict) ? parser.getProperties() : null) : parser.getProperties());
    }
    
    
    public static Properties getConfigs(String source) throws FileNotFoundException{
        return getConfigs(source, null, false);
    }
    
    public static Properties getConfigs(String source, Properties model, boolean strict) throws FileNotFoundException{
        Reader reader = new Reader(source);
        Parser parser = new Parser(reader);
        
        Logger.getLogger(EasyConfig.class.getName()).log(Level.INFO, "Parsing started: file ({0})", source);
        parser.parse();
        Logger.getLogger(EasyConfig.class.getName()).info("Parsing completed");
        
        reader.close();
        
        return (model != null ? (Validator.validate(model, parser.getProperties(), strict) ? parser.getProperties() : null) : parser.getProperties());
    }
}
