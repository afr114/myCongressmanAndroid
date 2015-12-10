package com.example.guest.mycongressman.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.example.guest.mycongressman.Congressman;
import com.example.guest.mycongressman.R;
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

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.zipCodeLabel) EditText mZipCodeLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.submitButton)
    public void switchLayout() {
        if (mZipCodeLabel != null && !mZipCodeLabel.getText().toString().isEmpty()) {
            setContentView(R.layout.activity_congressman);
            String apiKey = "bd43ab92ccfa4c1bb8047f654965f7e9";
            String sunlightUrl = "https://congress.api.sunlightfoundation.com/legislators/locate?zip=" + mZipCodeLabel.getText().toString() + "&apikey=" + apiKey;

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
                        String s = "s";

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
        }
    }

}
