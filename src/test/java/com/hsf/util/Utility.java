package com.hsf.util;

import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Utility {

    public static JSONObject getCombinedCapability(Map<String, String> envCapabilities, JSONObject config) {
        JSONObject capabilities = new JSONObject();
        JSONObject commonCapabilities = (JSONObject) config.get("capabilities");
        Iterator it = envCapabilities.entrySet().iterator();

        String username = System.getenv("QA_USERNAME");
        if(username == null) {
            username = (String) config.get("user");
        }

        String accessKey = System.getenv("QA_ACCESS_KEY");
        if(accessKey == null) {
            accessKey = (String) config.get("key");
        }

        JSONObject bstackOptions = commonCapabilities.get("options") != null ? (JSONObject) commonCapabilities.get("options") : new JSONObject();
        bstackOptions.putIfAbsent("userName", username);
        bstackOptions.putIfAbsent("accessKey", accessKey);
        bstackOptions.putIfAbsent("source", "cucumber-java:sample-master:v1.0");


        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            capabilities.put(pair.getKey().toString(), pair.getValue());
        }

        it = commonCapabilities.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if (capabilities.get(pair.getKey().toString()) == null) {
                capabilities.put(pair.getKey().toString(), pair.getValue());
            } else if (pair.getKey().toString().equalsIgnoreCase("options")) {
                HashMap<String, String> bstackOptionsMap = (HashMap) pair.getValue();
                bstackOptionsMap.putAll((HashMap) capabilities.get("options"));
                capabilities.put(pair.getKey().toString(), bstackOptionsMap);
            }
        }
        return capabilities;
    }

}
