/*
  Main2Activity.java
  Project-2
  Created by Harshil Patel on 02/25/18.
  Copyright © 2018 UIC. All rights reserved.

  <<Harshil Patel>>
   U. of Illinois, Chicago
   CS 478: Spring 2018
   Project 02
 */
package com.example.harshilpatel.project2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.widget.Toast;


public class Main2Activity extends AppCompatActivity {
    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //get the intent send by the first activity
        Intent intent = getIntent();


        //get the url
        String url = intent.getStringExtra("url");

        //display the song by the artist
        webview = new WebView(this);
        setContentView(webview);
        webview.loadUrl(url);

       // Toast.makeText(Main2Activity.this, url, Toast.LENGTH_LONG).show();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //Destroys activity.
        finish();
        return true;
    }
}
