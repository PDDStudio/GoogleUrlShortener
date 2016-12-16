package com.pddstudio.googleurlshortener;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pddstudio.urlshortener.URLShortener;

public class MainActivity extends AppCompatActivity {

    EditText urlText;
    Button createButton;
    TextView outputLink;

    EditText shortUrlEditText;
    Button getLongUrlButton;
    TextView longUrlOutputLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        urlText = (EditText) findViewById(R.id.urlText);
        createButton = (Button) findViewById(R.id.createBtn);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*new ShortUrlTask(urlText.getText().toString())
                        .displayIn(outputLink)
                        .execute();*/
                URLShortener.shortUrl(urlText.getText().toString(), new URLShortener.LoadingCallback() {
                    @Override
                    public void startedLoading() {
                        outputLink.setText("Loading...");
                    }

                    @Override
                    public void finishedLoading(@Nullable String shortUrl) {
                        if(shortUrl != null) outputLink.setText(shortUrl);
                        else outputLink.setText("Unable to generate Link!");
                    }
                });
            }
        });
        outputLink = (TextView) findViewById(R.id.outputLink);
        outputLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(outputLink.getText() != null && outputLink.getText().length() > 0) {
                    Intent action = new Intent(Intent.ACTION_VIEW);
                    action.setData(Uri.parse(outputLink.getText().toString()));
                    startActivity(action);
                }
            }
        });



        shortUrlEditText = (EditText) findViewById(R.id.shortUrlEditText);
        getLongUrlButton = (Button) findViewById(R.id.getLongUrlBtn);
        getLongUrlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*new ShortUrlTask(urlText.getText().toString())
                        .displayIn(outputLink)
                        .execute();*/
                URLShortener.longUrl(shortUrlEditText.getText().toString(), new URLShortener.LoadingCallback() {
                    @Override
                    public void startedLoading() {
                        longUrlOutputLink.setText("Loading...");
                    }

                    @Override
                    public void finishedLoading(@Nullable String longUrl) {
                        if(longUrl != null) longUrlOutputLink.setText(longUrl);
                        else longUrlOutputLink.setText("Unable to generate Link!");
                    }
                });
            }
        });
        longUrlOutputLink = (TextView) findViewById(R.id.longUrlOutputLink);
        longUrlOutputLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(longUrlOutputLink.getText() != null && longUrlOutputLink.getText().length() > 0) {
                    Intent action = new Intent(Intent.ACTION_VIEW);
                    action.setData(Uri.parse(longUrlOutputLink.getText().toString()));
                    startActivity(action);
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
