package com.example.cattyperi;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class RegisterCatActivity extends AppCompatActivity {
    String temp;
    String url_storeCat = "http://192.168.1.4/FP_TEKBER/storeCat.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_cat);
    }

    private void storeCat(){
        String url = url_storeCat + "?username=" + temp + "&name_cat=" + temp + "&condition_cat=" + temp + "&photo=" + temp + "&loc_found=" + temp + "&gender=" + temp + "&penyakit=" + temp;
        Log.e("URL", url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(RegisterCatActivity.this, "err" + error.toString(), Toast.LENGTH_LONG).show();
                }
            }
        );
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
            300000,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(RegisterCatActivity.this);
        requestQueue.add(stringRequest);
    }
}
