package com.example.guest.mycongressman.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.guest.mycongressman.Congressman;
import com.example.guest.mycongressman.R;
import com.example.guest.mycongressman.adapters.CongressmanAdapter;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class CongressmanList extends AppCompatActivity {

    private String mZipCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congressman_list);

        Intent intent = getIntent();
        mZipCode = intent.getStringExtra("KEY_ZIP_CODE");

        congressmanApiCall(mZipCode);
        String S = "s";
    }


    public void congressmanApiCall(String zipCode) {
        String apiKey = "bd43ab92ccfa4c1bb8047f654965f7e9";
        String sunlightUrl = "https://congress.api.sunlightfoundation.com/legislators/locate?zip=" + zipCode + "&apikey=" + apiKey;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(sunlightUrl).build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String jsonData = response.body().string();

                try {
                    JSONObject congressInfo = new JSONObject(jsonData);
                    JSONArray results = congressInfo.getJSONArray("results");
                    ArrayList<Congressman> congressmans = Congressman.fromJson(results);
                    CongressmanAdapter adapter = new CongressmanAdapter(CongressmanList.this, congressmans);
                    String S = "s";

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    };
}
