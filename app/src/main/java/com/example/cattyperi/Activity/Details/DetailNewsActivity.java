package com.example.cattyperi.Activity.Details;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cattyperi.Adapter.VolleyCallbackAdapter;
import com.example.cattyperi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class DetailNewsActivity extends AppCompatActivity {
    String id_news;
    private TextView title, date, content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        title = findViewById(R.id.tv_title);
        date = findViewById(R.id.tv_date);
        content = findViewById(R.id.tv_content);

        id_news = getIntent().getStringExtra("id_news");
        onResume();
    }

    public void onResume() {
        super.onResume();
        getData(new VolleyCallbackAdapter(){
            @Override
            public void onSuccessResponse(JSONArray result) throws JSONException {
                JSONObject obj = result.getJSONObject(0);
                title.setText("Title of news : " + obj.getString("title_news"));
                date.setText("Date publish : "+obj.getString("date"));
                content.setText(obj.getString("content_news"));
            }
        });
    }
    public void getData(final VolleyCallbackAdapter callback){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url      = "http://192.168.1.5/FP_TEKBER/getNews.php?id_news="+id_news;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
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

}
