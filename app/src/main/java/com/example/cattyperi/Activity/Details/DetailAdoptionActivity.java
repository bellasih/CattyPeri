package com.example.cattyperi.Activity.Details;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
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

public class DetailAdoptionActivity extends AppCompatActivity {
    String id_cat;
    private TextView name_cat,type_cat, condition_cat, loc_found, age;
    private ImageView img_cat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adoption_detail);

        name_cat = findViewById(R.id.tv_name_cat);
        type_cat = findViewById(R.id.tv_type_cat);
        condition_cat = findViewById(R.id.tv_condition_cat);
        loc_found = findViewById(R.id.tv_loc_found);
        age = findViewById(R.id.tv_age);
        img_cat = findViewById(R.id.tv_img_cat);

        id_cat = getIntent().getStringExtra("id_cat");
        onResume();
    }

    public void onResume() {
        super.onResume();
        getData(new VolleyCallbackAdapter(){
            @Override
            public void onSuccessResponse(JSONArray result) throws JSONException {
                Toast.makeText(DetailAdoptionActivity.this, result.toString(), Toast.LENGTH_LONG).show();

                img_cat.setImageResource(R.drawable.logo);
                JSONObject obj = result.getJSONObject(0);
                name_cat.setText("Cat's name : " + obj.getString("name_cat"));
                type_cat.setText("The type of cat : " + obj.getString("type_cat"));
                condition_cat.setText("Condition of cat : " + obj.getString("condition_cat"));
                loc_found.setText("Location found : " + obj.getString("loc_found"));
                age.setText(obj.getString("Age of cat : " + "age"));
            }
        });
    }

    public void getData(final VolleyCallbackAdapter callback){
        RequestQueue queue = Volley.newRequestQueue(this);
        Toast.makeText(DetailAdoptionActivity.this, id_cat, Toast.LENGTH_LONG).show();
        String url      = "http://192.168.1.5/FP_TEKBER/getAvailableCat.php?id_cat="+id_cat;
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
