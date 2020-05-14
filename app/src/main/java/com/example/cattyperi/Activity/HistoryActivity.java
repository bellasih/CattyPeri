package com.example.cattyperi.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cattyperi.Adapter.HistoryListAdapter;
import com.example.cattyperi.Adapter.VolleyCallbackAdapter;
import com.example.cattyperi.MainActivity;
import com.example.cattyperi.Model.Adoption;
import com.example.cattyperi.Model.History;
import com.example.cattyperi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    private HistoryListAdapter recyclerView;
    private RecyclerView rv;
    private ArrayList<History> HistoryArrayList;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        username = getIntent().getStringExtra("user");
        onResume();
    }

    public void onResume() {
        super.onResume();
        getData(new VolleyCallbackAdapter(){
            @Override
            public void onSuccessResponse(JSONArray result) {
                HistoryArrayList = new ArrayList<>();
                for(int i =0;i<result.length();i++){
                    try {
                        JSONObject obj = result.getJSONObject(i);
                        HistoryArrayList.add(new History(obj.getString("id_history"), obj.getString("user"), obj.getString("msg"), obj.getString("date")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                rv = findViewById(R.id.recycler_view_1);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HistoryActivity.this);
                recyclerView = new HistoryListAdapter(HistoryArrayList);
                rv.setLayoutManager(layoutManager);
                rv.setAdapter(recyclerView);
            }
        });
    }

    public void getData(final VolleyCallbackAdapter callback){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url_getHistory = "http://192.168.1.5/FP_TEKBER/getHistory.php?user="+username;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_getHistory,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray mJsonArray = new JSONArray(response);
                        callback.onSuccessResponse(mJsonArray);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("Error Response",error.toString());
                }
            }
        );
        queue.add(stringRequest);
    }

    public void onBackPressed() {
        Intent setIntent = new Intent(this, MainActivity.class);
        setIntent.putExtra("user",username);
        startActivity(setIntent);
        finish();
    }
}

