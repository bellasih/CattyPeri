package com.example.cattyperi.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cattyperi.R;

public class RegisterCatActivity extends AppCompatActivity {
    EditText ed_name_cat, ed_condition, ed_location, ed_disease;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_cat);

        ed_name_cat      = findViewById(R.id.ed1);
        ed_location      = findViewById(R.id.ed2);
        ed_condition     = findViewById(R.id.ed3);
        ed_disease       = findViewById(R.id.ed4);

        username = getIntent().getStringExtra("user");

        Button send = findViewById(R.id.sendbtn);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_cat     = ed_name_cat.getText().toString();
                String condition    = ed_condition.getText().toString();
                String location     = ed_location.getText().toString();
                String disease      = ed_disease.getText().toString();

                storeCat(name_cat,condition,location,disease);
            }
        });
    }

    private void storeCat(String name_cat, String condition, String location, String disease){
        String url_storeCat = "http://192.168.1.5/FP_TEKBER/storeCat.php";
        String url = url_storeCat + "?user=" + username + "&name_cat=" + name_cat + "&condition_cat=" + condition + "&photo=temp.jpg" + "&loc_found=" + location + "&penyakit=" + disease;
        Log.e("URL", url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(RegisterCatActivity.this, "Successfull to register",Toast.LENGTH_LONG).show();
                ed_condition.setText("");
                ed_location.setText("");
                ed_disease.setText("");
                ed_location.setText("");
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
            500000,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(RegisterCatActivity.this);
        requestQueue.add(stringRequest);
    }
}
