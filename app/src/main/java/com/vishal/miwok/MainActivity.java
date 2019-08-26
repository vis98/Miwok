package com.vishal.miwok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.button);
        Button btn2 = findViewById(R.id.button2);
        Button btn3 = findViewById(R.id.button3);
        Button btn4 = findViewById(R.id.button4);

        btn.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View view){
                Intent num=new Intent(MainActivity.this,NumbersActivity.class);
                startActivity(num);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View view){
                Intent num=new Intent(MainActivity.this,ColorsActivity.class);
                startActivity(num);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View view){
                Intent num=new Intent(MainActivity.this,PhrasesActivity.class);
                startActivity(num);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View view){
                Intent num=new Intent(MainActivity.this,FamilyActivity.class);
                startActivity(num);
            }
        });
    }



}
