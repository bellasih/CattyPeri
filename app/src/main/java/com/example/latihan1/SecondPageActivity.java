package com.example.latihan1;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static java.lang.Double.parseDouble;

public class SecondPageActivity extends AppCompatActivity
{
    TextView tv_hasil;
    EditText bil1, bil2;
    RadioGroup op;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondpage);

        tv_hasil =findViewById(R.id.tv_hasil);
        bil1 = findViewById(R.id.et_bil1);
        bil2 = findViewById(R.id.et_bil2);
        op = findViewById(R.id.operasi);
    }

    public void onHitungClick(View view){
        Double hasil = null;
        Double angka1 = parseDouble(bil1.getText().toString());
        Double angka2 = parseDouble(bil2.getText().toString());

        Integer select = op.getCheckedRadioButtonId();
        RadioButton selectOp = findViewById(select);

        String param = selectOp.getText().toString();

        if(param.equals("+")){
            hasil = angka1 + angka2;
        }
        else if(param.equals("-")){
            hasil = angka1 - angka2;
        }
        else if(param.equals("x")){
            hasil = angka1 * angka2;
        }
        else if(param.equals("/")){
            hasil = angka1 / angka2;
        }

        tv_hasil.setText(hasil.toString());
    }

}
