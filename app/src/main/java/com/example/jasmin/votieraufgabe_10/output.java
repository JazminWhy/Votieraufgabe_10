package com.example.jasmin.votieraufgabe_10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class output extends AppCompatActivity {

    String url;
    Intent intent, intent_return;
    TextView ausgabe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);
        intent = getIntent();
        intent_return = new Intent(this,UserInput.class);
        url = intent.getStringExtra("URL");
        ausgabe = findViewById(R.id.textView);
        System.out.println("Url" + intent.getStringExtra("URL"));
        MyThread cloud= new MyThread(ausgabe);
        cloud.execute(url);
    }

    public void clickReturn(View aView){
    startActivity(intent_return);
    }
}
