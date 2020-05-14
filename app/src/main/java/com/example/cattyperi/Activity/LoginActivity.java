package com.example.cattyperi.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cattyperi.Adapter.VolleyCallbackAdapter;
import com.example.cattyperi.MainActivity;
import com.example.cattyperi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends  AppCompatActivity{
    private EditText ed_username, ed_password;
    String username, password;
    String params, status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ed_username = findViewById(R.id.ed_username);
        ed_password = findViewById(R.id.ed_password);

        Button submit = findViewById(R.id.buttons2);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = ed_username.getText().toString();
                password = ed_password.getText().toString();
                Toast.makeText(LoginActivity.this, username + " " + password, Toast.LENGTH_LONG).show();

                params = username;
                onResume(username,password);
            }
        });
    }

    public void registerUserBtn(View view){
        Intent intents = new Intent(LoginActivity.this, RegisterUserActivity .class);
        startActivity(intents);
    }

    public void onResume(String username, String password) {
        super.onResume();

        getData(username,password,new VolleyCallbackAdapter(){
            @Override
            public void onSuccessResponse(JSONArray result) {
                try {
                    JSONObject obj = result.getJSONObject(0);
                    status = obj.getString("total");
                    if(("1").equals(status)){
                        Intent main = new Intent(LoginActivity.this, MainActivity.class);
                        main.putExtra("user", params);
                        startActivity(main);
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "Username atau password salah", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void getData(String username, String password,final VolleyCallbackAdapter callback){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url_getLogin = "http://192.168.1.5/FP_TEKBER/getLogin.php?user=" + username + "&password=" + password;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_getLogin,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray mJsonArray = new JSONArray(response);
                        Toast.makeText(LoginActivity.this, mJsonArray.toString(), Toast.LENGTH_LONG).show();
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
