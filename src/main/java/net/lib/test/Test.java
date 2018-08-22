/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.lib.test;

import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.lib.easyconfig.EasyConfig;

/**
 *
 * @author henri
 */
public class Test {

    public static void main(String... args) {
        Properties prop;
        try {
            Properties model = new Properties();
            
            model.put("valor", "req");
            model.put("valor2", "req-matches[/^([a-z]|[A-Z]){1,}/]-");
            model.put("valor3", "req-not_empty");
            model.put("valor 4", "req");
            
            prop = EasyConfig.getConfigs("config.config", model, true);
            System.out.println(prop.toString());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
