package com.example.lovecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    EditText name,crushName;
    Button btn_cal;
    TextView calPer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        crushName = findViewById(R.id.crushName);
        btn_cal = findViewById(R.id.btn_cal);
        calPer = findViewById(R.id.totalPer);
        final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        btn_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_name = name.getText().toString();
                String txt_crushName = crushName.getText().toString();

                if (TextUtils.isEmpty(txt_name)){
                    Toast.makeText(MainActivity.this, "Enter your name...", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(txt_crushName)){
                    Toast.makeText(MainActivity.this, "Enter crush name...", Toast.LENGTH_SHORT).show();
                }else {
                    String sum = txt_crushName + txt_name;
                    sum = sum.toLowerCase();
                    int value = sum.hashCode();
                    Random random = new Random(value);
                    String per = (random.nextInt(100)+1)+"%";

                    calPer.setText(per);

                    HashMap<String,Object> map = new HashMap<>();
                    map.put("Name" , txt_name);
                    map.put("Crush Name" ,txt_crushName);
                    map.put("Bonding" , per);

                    firebaseDatabase.getReference().child("Love Calculator").child(txt_name).setValue(map);
                    name.setText("");
                    crushName.setText("");
                }

            }
        });
    }
}