package net.lib.easyconfig.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.lib.easyconfig.EasyConfig;

/**
 *
 * @author henri
 */
public final class Reader {

    private final File configFile;
    private final BufferedReader reader;
    private String nextLine;

    public Reader(String configFile) throws FileNotFoundException {
        this.configFile = new File(configFile);

        reader = new BufferedReader(new FileReader(configFile));
    }

    public boolean hasMoreLines() {
        if(nextLine == null){
            return ((nextLine = getLine()) != null);
        }
        
        return true;
    }

    public String getLine() {
        if (nextLine == null) {
            try {
                nextLine = reader.readLine();
            } catch (IOException ex) {
                Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        String ret = nextLine;
        nextLine = null;
        return ret;
    }
    
    public void close(){
        try {
            reader.close();
        } catch (IOException ex) {
            Logger.getLogger(EasyConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
