package net.lib.easyconfig;

import java.util.Properties;

public final class Validator{
    public static boolean validate(Properties model, Properties prop, boolean strict){
        int model_count = 0;
    
        for(Object model_key : model.keySet()){
            String conditions = model.get(model_key).toString();
            
            Object prop_value = prop.get(model_key);
            
            if(conditions.contains("req") && prop_value == null){
                return false;
            }
            
            if(conditions.contains("not_empty") && prop_value.toString().equals("")){
                return false;
            }
            
            int index = conditions.indexOf("matches[/");
            if(index != -1){
                int index_end = conditions.indexOf("/]-");
                String regex = conditions.substring(index + 9, index_end);
                
                if(!prop_value.toString().matches(regex)){
                    return false;
                }
            }
            
            model_count++;
        }
        
        return (strict ? model_count == prop.keySet().size() : true);
    }
}
