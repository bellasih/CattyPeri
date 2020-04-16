package com.example.cattyperi;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<ArrayList<String>> news_data;
    String url_getNews = "http://192.168.1.4/FP_TEKBER/getNews.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        getData();
    }

    public void getData(){
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_getNews,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        news_data = new ArrayList<ArrayList<String>>();
                        ArrayList<String> temp_data = new ArrayList<>();

                        JSONArray mJsonArray = new JSONArray(response);
                        if(mJsonArray.length()==0){
                            temp_data.add("Sorry, there are no news that available to access");
                        }
                        else{
                            String index [] = {"id_news","title_news","content_news","date"};
                            for(int i=0;i<mJsonArray.length();i++) {
                                JSONObject mJsonObject = mJsonArray.getJSONObject(i);

                                for (int k = 0; k < index.length; k++) {
                                    temp_data.add(mJsonObject.getString(index[k]));
                                }
                                news_data.add(temp_data);
                                temp_data.clear();
                            }
                        }
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
}
