package com.example.webserviceswithvolly2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    String url = "https://my-json-server.typicode.com/jubs16/Products/Products";
    RecyclerView recyclerView;
    RequestQueue queue ;
    ArrayList<HashMap> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(this);

        //finding Id of RecyclerView
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Voley
        getData();
    }

    private void getData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("respose", response+"");
                            JSONArray jArray = new JSONArray(response);
                            for(int i=0 ; i<jArray.length() ; i++) {
                                JSONObject jObject = (JSONObject) jArray.get(i);
                                Log.d("data", jObject.get("name")+"");
                                HashMap map = new HashMap();
                                map.put("imgUrl", jObject.get("imgUrl")+"");
                                map.put("name", jObject.get("name")+"");
                                map.put("price", jObject.get("price")+"");

                                data.add(map);
                                Log.d("map", map+"");

                            }
                        }
                        catch (Exception e) {
                            Log.d("Error", e+"");
                        }


                        CustomAdaptor ca = new CustomAdaptor(MainActivity.this, data);
                        recyclerView.setAdapter(ca);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", error+"");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater  = getMenuInflater();
        inflater.inflate(R.menu.context_text, menu);
    }

}