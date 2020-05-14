package com.example.cattyperi.Activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cattyperi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DonationTransactionActivity extends AppCompatActivity {
    private RelativeLayout rl;
    private TextView tv, tv1;
    private ArrayList<String> arr_data;

    String url_getDonationTransaction = "http://192.168.1.4/FP_TEKBER/getDonationTransaction.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_transaction);
        rl = findViewById(R.id.relative_layout);
        getData();
    }

    public void getData(){
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_getDonationTransaction,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        );

                        JSONArray mJsonArray = new JSONArray(response);
                        if(mJsonArray.length()==0){
                            tv = new TextView(DonationTransactionActivity.this);
                            tv.setPadding(10,50,10,0);
                            tv.setText("Sorry, the data isn't available");
                            tv.setTextSize(16);
                            tv.setTextColor(000000);
                            rl.addView(tv, p);
                        }
                        else{

                            String index [] = {"id_donation","id_cat","donator","nominal","date_donation"};
                            for(int i=0;i<mJsonArray.length();i++) {
                                arr_data = new ArrayList<>();
                                arr_data.add(String.valueOf(i + 1));
                                JSONObject mJsonObject = mJsonArray.getJSONObject(i);

                                for (int k = 0; k < index.length; k++) {
                                    arr_data.add(mJsonObject.getString(index[k]));
                                }

                                TableRow tr = new TableRow(DonationTransactionActivity.this);
                                for (int j = 0; j < 6; j++) {
                                    tv1 = new TextView(DonationTransactionActivity.this);
                                    tv1.setText(arr_data.get(j));
                                    tr.addView(tv1);
                                }
                                rl.addView(tr, p);
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
