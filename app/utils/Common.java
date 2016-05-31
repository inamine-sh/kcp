package utils;

import java.util.HashMap;
import java.util.Map;

public class Common {

    public static Map<String, String> preparedParams(Map<String, String[]> getParams) {
        Map<String, String> params = new HashMap<>();

        for(Map.Entry<String, String[]> getParam: getParams.entrySet()) {
            if(getParam != null && !getParam.getValue()[0].equals("")) {
                params.put(getParam.getKey(), getParam.getValue()[0]);
            }
        }

        return params;
    }

}
