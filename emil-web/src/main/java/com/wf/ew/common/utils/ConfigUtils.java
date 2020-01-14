package com.wf.ew.common.utils;

import java.util.ResourceBundle;

public class ConfigUtils {


    public static String getConfigValue(String configname) {
        return getConfigValue("emilframe", configname);
    }

    public static String getConfigValue(String fileName, String configname) {
        String result = null;

        try {
            ResourceBundle resBundle = ResourceBundle.getBundle(fileName);
            if (resBundle.containsKey(configname)) {
                result = resBundle.getString(configname);
                if (StringUtil.isNotBlank(result)) {
                    result = result.trim();
                }
            }
        } catch (Exception var4) {
        }

        return result;
    }
}
