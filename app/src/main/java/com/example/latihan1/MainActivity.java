package com.example.latihan1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this,"selamat datang",Toast.LENGTH_LONG).show();
    }

    public void onSecondClick(View view){
        Intent intent = new Intent(this, SecondPageActivity.class);
        startActivity(intent);
    }
}
