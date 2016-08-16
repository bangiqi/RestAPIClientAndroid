package com.labs.ramdani.restapiclient.ui;

import android.app.DownloadManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.labs.ramdani.restapiclient.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "debug_log";
    String getURLFromText;

    EditText _txtURL;
    Button _btnSend;
    TextView _tvResult;

    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        _txtURL = (EditText) findViewById(R.id.txt_url);

        _btnSend = (Button) findViewById(R.id.btn_send);
        _tvResult = (TextView) findViewById(R.id.tv_result);


        url ="http://tesyii.pe.hu/";
        _txtURL.setText(url);

        _btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getURLFromText = _txtURL.getText().toString();
                Log.d(TAG, "get url "+getURLFromText);

                sendRequest(getURLFromText);
                //add value to tvResult
                //_tvResult.setText(getURLFromText);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public void sendRequest(String getURL){

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, getURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        _tvResult.setText("Response is: "+ response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                _tvResult.setText("That didn't work!");
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);


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
