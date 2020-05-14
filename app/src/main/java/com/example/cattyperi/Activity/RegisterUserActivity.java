package com.example.cattyperi.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cattyperi.R;

public class RegisterUserActivity extends AppCompatActivity {
    private EditText username, password, email, hp, address, name;
    private RadioGroup jk_group;
    private RadioButton jk_btn;
    private Button btn;
    String username_txt, password_txt, email_txt, hp_txt, jk_txt, address_txt, name_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_acc);

        username = findViewById(R.id.editText6);
        password = findViewById(R.id.editText4);
        email    = findViewById(R.id.editText3);
        address  = findViewById(R.id.editText2);
        hp       = findViewById(R.id.editText5);
        name     = findViewById(R.id.editText);

        btn = findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username_txt    = username.getText().toString();
                password_txt    = password.getText().toString();
                email_txt       = email.getText().toString();
                hp_txt          = hp.getText().toString();
                address_txt     = address.getText().toString();
                name_txt        = name.getText().toString();
                jk_group        = findViewById(R.id.jks);
                jk_btn          = findViewById(jk_group.getCheckedRadioButtonId());
                jk_txt          = jk_btn.getText().toString();
                if(jk_txt.equals("Perempuan")){
                    jk_txt = "Female";
                }
                else{
                    jk_txt = "Male";
                }
                storeUser();
            }
        });
    }

    public void storeUser(){
        String url_storeDonation = "http://192.168.1.5/FP_TEKBER/storeUser.php?";
        String url = url_storeDonation + "user=" + username_txt + "&password=" + password_txt + "&email=" + email_txt + "&hp=" + hp_txt + "&address=" + address_txt + "&jk=" + jk_txt + "&name=" + name_txt;
        Toast.makeText(RegisterUserActivity.this, url, Toast.LENGTH_LONG).show();
        Log.e("URL", url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(RegisterUserActivity.this, "Successfull for register", Toast.LENGTH_LONG).show();
                username.setText("");
                password.setText("");
                email.setText("");
                address.setText("");
                hp.setText("");
                name.setText("");
            }
        },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(RegisterUserActivity.this, "err" + error.toString(), Toast.LENGTH_LONG).show();
                }
            }
        );
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
            500000,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(RegisterUserActivity.this);
        requestQueue.add(stringRequest);
    }
}

