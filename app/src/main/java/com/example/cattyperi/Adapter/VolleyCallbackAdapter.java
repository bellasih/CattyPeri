package com.example.cattyperi.Adapter;

import org.json.JSONArray;
import org.json.JSONException;

public interface VolleyCallbackAdapter {
    void onSuccessResponse(JSONArray result) throws JSONException;
}
