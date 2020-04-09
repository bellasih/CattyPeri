package com.example.cattyperi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    CarouselView corView;

    int[] homeImages = {R.drawable.home_1, R.drawable.home_2, R.drawable.home_3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar();
        drawerLayout = findViewById(R.id.menuUtama);

        corView = findViewById(R.id.carouselView);
        corView.setPageCount(homeImages.length);
        corView.setImageListener(imageListener);

        navigationView = findViewById(R.id.navigasi);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        menuItem.setChecked(true);

        switch (id){
            case R.id.home :
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.donation :
                Intent int1 = new Intent(this, DonationActivity.class);
                int1.putExtra("msg", "1");
                startActivity(int1);
                break;
            case R.id.adoption :
                Intent int2 = new Intent(this, AdoptionActivity.class);
                int2.putExtra("msg", "2");
                startActivity(int2);
                break;
            case R.id.news :
                Intent int3 = new Intent(this, NewsActivity.class);
                int3.putExtra("msg", "3");
                startActivity(int3);
                break;
            case R.id.regis_cat :
                Intent int4 = new Intent(this, RegisterCatActivity.class);
                int4.putExtra("msg", "4");
                startActivity(int4);
                break;
            case R.id.login :
                Intent int5 = new Intent(this, LoginActivity.class);
                int5.putExtra("msg", "5");
                startActivity(int5);
                break;
        }
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }

        return false;
    }
}

