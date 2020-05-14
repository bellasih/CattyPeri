package com.example.cattyperi.Activity;

import android.content.Intent;
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
import com.example.cattyperi.MainActivity;
import com.example.cattyperi.R;

public class DonationActivity extends AppCompatActivity {
    EditText ed_nominal;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);

        ed_nominal      = findViewById(R.id.edd1);
        username        = getIntent().getStringExtra("user");

        Button send = findViewById(R.id.submitbtn);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nominal  = ed_nominal.getText().toString();
                storeDonation(nominal);
            }
        });
    }

    public void storeDonation(String nominal){
        String url_storeDonation = "http://192.168.1.5/FP_TEKBER/storeDonation.php?";
        String url = url_storeDonation + "user=" + username + "&nominal=" + nominal;
        Log.e("URL", url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(DonationActivity.this, "Successfull for register", Toast.LENGTH_LONG).show();
            }
        },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(DonationActivity.this, "err" + error.toString(), Toast.LENGTH_LONG).show();
                }
            }
        );
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
            500000,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(DonationActivity.this);
        requestQueue.add(stringRequest);
    }

    public void onBackPressed() {
        Intent setIntent = new Intent(this, MainActivity.class);
        setIntent.putExtra("user",username);
        startActivity(setIntent);
        finish();
    }
}
