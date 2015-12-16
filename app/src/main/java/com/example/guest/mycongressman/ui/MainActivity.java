package com.example.guest.mycongressman.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.guest.mycongressman.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    private static final String KEY_ZIP_CODE = "KEY_ZIP_CODE";
    @Bind(R.id.zipCodeLabel) EditText mZipCodeLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.submitButton)
    public void sendZipCode(View view){
        Intent intent = new Intent(this, CongressmanList.class);
        String zipCode = mZipCodeLabel.getText().toString();
        if (zipCode != null && !zipCode.isEmpty()) {
        intent.putExtra(KEY_ZIP_CODE, zipCode);
        startActivity(intent);
        } else {
            Toast.makeText(MainActivity.this, R.string.zip_code_warning, Toast.LENGTH_LONG).show();
        }
    }
}
