package com.example.cattyperi;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
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

public class AdoptionActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<ArrayList<String>> cat_data;
    int role;
    String temp;
    String url_getAvailableCat      = "http://192.168.1.4/FP_TEKBER/getAvailableCat.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int button_id = 1;
        /*get button on click method*/

        if(button_id == 1) {
            setContentView(R.layout.activity_adoption);
            getData(0);

            recyclerView = findViewById(R.id.recycler_view);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(AdoptionActivity.this);
            recyclerView.setLayoutManager(layoutManager);
            //recyclerView.setAdapter(adapter);

        }
        else{
            setContentView(R.layout.activity_adoption_detail);
            getData(1);
        }
    }

    public void getData(int id_cat){
        RequestQueue queue = Volley.newRequestQueue(this);
        if(id_cat != 0){
            url_getAvailableCat = url_getAvailableCat + "?id_cat=" + id_cat;
        }
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_getAvailableCat,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        cat_data = new ArrayList<ArrayList<String>>();
                        ArrayList<String> temp_data = new ArrayList<>();

                        JSONArray mJsonArray = new JSONArray(response);
                        if(mJsonArray.length()==0){
                            temp_data.add("Sorry, there are no cats that available for adoption");
                        }
                        else{
                            String index [] = {"id_cat","name_cat","type_cat","photo","date_found"};
                            for(int i=0;i<mJsonArray.length();i++) {
                                JSONObject mJsonObject = mJsonArray.getJSONObject(i);

                                for (int k = 0; k < index.length; k++) {
                                    temp_data.add(mJsonObject.getString(index[k]));
                                }

                                cat_data.add(temp_data);
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
