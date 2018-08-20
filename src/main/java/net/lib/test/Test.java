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
            prop = EasyConfig.getConfigs("config.config");
            System.out.println(prop.toString());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
