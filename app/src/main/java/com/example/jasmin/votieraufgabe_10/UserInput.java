package com.example.jasmin.votieraufgabe_10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UserInput extends AppCompatActivity {
    /*
    zum Auflisten aller vorhandener Nachrichten:
http://webtechlecture.appspot.com/chat/posting/list
zum Auflisten der Nachrichten eines bestimmten Users :
http://webtechlecture.appspot.com/chat/posting/list?userid=student
zum Senden der Nachricht an den Server im Namen eines Users
webtechlecture.appspot.com/chat/posting/new?text=dertext&userid=username
     */
    /*
    Step 1: Get Permission => insert in Manifest  <uses-permission android:name="android.permission.INTERNET"></uses-permission>
    Step 2: Configure AsyncThreacClass
     */
    Intent intent;
    EditText userInput, text;
    String userName, userText;
    Button showAll;
    Boolean empty;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(this,output.class);
        userInput = findViewById(R.id.userInput);
        text = findViewById(R.id.text);
        showAll = findViewById(R.id.showAll);
        userName="";
        userText="";
        url= "";
        empty = false;
    }
    public void start(){
        intent.putExtra("URL", url);
        startActivity(intent);
    }
    public String encode(String s) {
        String out = "";
        empty = false;
            try {
                out = URLEncoder.encode(s, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            System.out.println("####out" + out);
        return out;
    }
    public String getUser(){
        userName = userInput.getText().toString();
        if(!(userName.equals("") )){
            userName = encode(userName);
        }
        else{
            empty = true;
        }
        return userName;
    }
    public String getText(){
        userText = text.getText().toString();
        if(!(userText.equals("") )){
            userText = encode(userText);
        }
        else{
            empty = true;
        }
        return userText;
    }
    public void showAll(View aView){
        url = "http://webtechlecture.appspot.com/chat/posting/list";
        start();
    }
    public void send(View aView){
        url = "http://webtechlecture.appspot.com/chat/posting/new?text=" + getText() + "&userid=" + getUser();
        if(!empty){
            start();
        }

    }
    public void receive(View aView){
        url = "http://webtechlecture.appspot.com/chat/posting/list?userid=" + getUser();
        if(!empty){
            start();
        }
    }
}
