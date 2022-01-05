package com.finerioconnect.coreexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.finerioconnect.sdk.core.FinerioConnectSDK;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FinerioConnectSDK finerioConnectSDK = FinerioConnectSDK.Companion.getShared();
        finerioConnectSDK.setApiKey("api_key");
        finerioConnectSDK.configure();
    }

}