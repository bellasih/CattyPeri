package com.example.cattyperi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cattyperi.Activity.AdoptionActivity;
import com.example.cattyperi.Activity.DonationActivity;
import com.example.cattyperi.Activity.HistoryActivity;
import com.example.cattyperi.Activity.LoginActivity;
import com.example.cattyperi.Activity.NewsActivity;
import com.example.cattyperi.Activity.RegisterCatActivity;
import com.example.cattyperi.Adapter.AdoptionPagerAdapter;
import com.example.cattyperi.Adapter.HistoryListAdapter;
import com.example.cattyperi.Adapter.NewsPagerAdapter;
import com.example.cattyperi.Adapter.VolleyCallbackAdapter;
import com.example.cattyperi.Fragment.AdoptionFragmentAdapter;
import com.example.cattyperi.Fragment.NewsFragmentAdapter;
import com.example.cattyperi.Fragment.ShadowTransformer;
import com.example.cattyperi.Model.Adoption;
import com.example.cattyperi.Model.News;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener,
    CompoundButton.OnCheckedChangeListener{

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    CarouselView corView;
    private AdoptionPagerAdapter mAdoptionAdapter;
    private AdoptionFragmentAdapter mFragmentAdoptionAdapter;
    private NewsPagerAdapter mNewsAdapter;
    private NewsFragmentAdapter mFragmentNewsAdapter;
    private ViewPager mViewPager, nViewPager;
    private Button mButton, nButton;
    ShadowTransformer mAdoptionShadowTransformer,mNewsShadowTransformer, mFragmentAdoptionShadowTransformer, mFragmentNewsShadowTransformer;
    private boolean mShowingFragments = false;
    String username = null;

    int[] homeImages = {R.drawable.home_1, R.drawable.home_2, R.drawable.home_3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = getIntent().getStringExtra("user");
        if(username == null){
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }

        getSupportActionBar();
        drawerLayout = findViewById(R.id.menuUtama);

        //set carousel view
        corView = findViewById(R.id.carouselView);
        corView.setPageCount(homeImages.length);
        corView.setImageListener(imageListener);

        //set navigation view
        navigationView = findViewById(R.id.navigasi);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        //set card swipe adoption (available cat)
        mViewPager = findViewById(R.id.viewPager);

        onResume();
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(homeImages[position]);
        }
    };

    public void openMenuBar(View view){
        drawerLayout.openDrawer(Gravity.LEFT);
    }

    public void onResume() {
        super.onResume();
        getData(new VolleyCallbackAdapter(){
            @Override
            public void onSuccessResponse(JSONArray result) {
                mAdoptionAdapter = new AdoptionPagerAdapter();
                for(int i =0;i<result.length();i++){
                    try {
                        JSONObject obj = result.getJSONObject(i);
                        mAdoptionAdapter.addCardItem(new Adoption(obj.getString("id_cat"), obj.getString("name_cat"),obj.getString("loc_found"),obj.getString("photo")),username);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                mFragmentAdoptionAdapter = new AdoptionFragmentAdapter(getSupportFragmentManager(),dpToPixels(2,MainActivity.this));
                mViewPager.setAdapter(mAdoptionAdapter);
                mViewPager.setPageTransformer(false, mAdoptionShadowTransformer);
                mViewPager.setOffscreenPageLimit(3);
            }
        });
    }

    public void getData(final VolleyCallbackAdapter callback){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url_getMain= "http://192.168.1.5/FP_TEKBER/getMain.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_getMain,
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        menuItem.setChecked(true);

        switch (id){
            case R.id.donation :
                Intent int1 = new Intent(MainActivity.this, DonationActivity.class);
                int1.putExtra("user", username);
                startActivity(int1);
                break;
            case R.id.adoption :
                Intent int2 = new Intent(MainActivity.this, AdoptionActivity.class);
                int2.putExtra("user", username);
                startActivity(int2);
                break;
            case R.id.news :
                Intent int3 = new Intent(MainActivity.this, NewsActivity.class);
                int3.putExtra("user", username);
                startActivity(int3);
                break;
            case R.id.regis_cat :
                Intent int4 = new Intent(MainActivity.this, RegisterCatActivity.class);
                int4.putExtra("user", username);
                startActivity(int4);
                break;
            case R.id.history :
                Intent int6 = new Intent(MainActivity.this, HistoryActivity.class);
                int6.putExtra("user", username);
                startActivity(int6);
                break;
        }
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        if (!mShowingFragments) {
            mButton.setText("Views");
            mViewPager.setAdapter(mFragmentAdoptionAdapter);
            mViewPager.setPageTransformer(false, mFragmentAdoptionShadowTransformer);
        } else {
            mButton.setText("Fragments");
            mViewPager.setAdapter(mAdoptionAdapter);
            mViewPager.setPageTransformer(false, mFragmentAdoptionShadowTransformer);
        }
        mShowingFragments = !mShowingFragments;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        mAdoptionShadowTransformer.enableScaling(b);
        mFragmentAdoptionShadowTransformer.enableScaling(b);
    }

    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }
}


//        nViewPager = findViewById(R.id.viewPager2);
//
//        mNewsAdapter = new NewsPagerAdapter();
//        mFragmentNewsAdapter = new NewsFragmentAdapter(getSupportFragmentManager(),dpToPixels(2,MainActivity.this));
//        mNewsAdapter.addCardItem(new News("2","Jaga Kebersihan Kucing","Cobaaaa","20-10-19"));
//        nViewPager.setAdapter(mNewsAdapter);
//        nViewPager.setPageTransformer(false,mNewsShadowTransformer);
//        nViewPager.setOffscreenPageLimit(3);
//                //set card swipe adoption (available cat)
//                mViewPager = findViewById(R.id.viewPager);
//                mButton = findViewById(R.id.cardTypeBtn);
//                ((CheckBox) findViewById(R.id.checkBox)).setOnCheckedChangeListener(MainActivity.this);
//                mButton.setOnClickListener(MainActivity.this);
//
//                mAdoptionAdapter = new AdoptionPagerAdapter();
//                mFragmentAdoptionAdapter = new AdoptionFragmentAdapter(getSupportFragmentManager(),dpToPixels(2,MainActivity.this));
//                mViewPager.setAdapter(mAdoptionAdapter);
//                mViewPager.setPageTransformer(false, mAdoptionShadowTransformer);
//                mViewPager.setOffscreenPageLimit(3);
//
//                //set card swipe news
//                nViewPager = findViewById(R.id.viewPager);
//                nButton = findViewById(R.id.cardTypeBtn);
//                nButton.setOnClickListener(MainActivity.this);
//                ((CheckBox) findViewById(R.id.checkBox)).setOnCheckedChangeListener(MainActivity.this);
//
//                mNewsAdapter = new NewsPagerAdapter();
//                mFragmentNewsAdapter = new NewsFragmentAdapter(getSupportFragmentManager(),dpToPixels(2,MainActivity.this));
//                nViewPager.setAdapter(mNewsAdapter);
//                nViewPager.setOffscreenPageLimit(3);
