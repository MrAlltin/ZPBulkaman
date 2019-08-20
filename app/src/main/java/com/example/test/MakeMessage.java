package com.example.test;

import org.json.JSONException;
import org.json.JSONObject;

public class MakeMessage {
    String json = "{\"id\":1,\"link\":12}";
    JSONObject obj = new JSONObject(json);
    String url = obj.getString("link");
    String id = obj.getString("id");
    return url;

}
