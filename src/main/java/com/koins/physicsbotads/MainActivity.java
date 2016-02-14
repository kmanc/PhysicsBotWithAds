package com.koins.physicsbotads;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    InterstitialAd mInterstitialAd;
    int clickCheck = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button mechButton = (Button) findViewById(R.id.Mechanics);
        final Button kinButton = (Button) findViewById(R.id.Kinematics);
        final Button elecButton = (Button) findViewById(R.id.Electricity);
        final Button magButton = (Button) findViewById(R.id.Magnetism);
        final Button directionButton = (Button) findViewById(R.id.Directions);
        final Button exitButton = (Button) findViewById(R.id.Exit);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-5775817630213182/9386371957");
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                switch (clickCheck) {
                    case 1:
                        mechSetup();
                        break;
                    case 2:
                        kinSetup();
                        break;
                    case 3:
                        elecSetup();
                        break;
                    case 4:
                        magSetup();
                        break;
                }
            }
        });

        requestNewInterstitial();

        mechButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    clickCheck = 1;
                    mInterstitialAd.show();
                } else {
                    mechSetup();
                }
            }
        });

        kinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    clickCheck = 2;
                    mInterstitialAd.show();
                } else {
                    kinSetup();
                }
            }
        });

        elecButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    clickCheck = 3;
                    mInterstitialAd.show();
                } else {
                    elecSetup();
                }
            }
        });

        magButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    clickCheck = 4;
                    mInterstitialAd.show();
                } else {
                    magSetup();
                }
            }
        });

        directionButton.setOnClickListener(this);
        exitButton.setOnClickListener(this);
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("SEE_YOUR_LOGCAT_TO_GET_YOUR_DEVICE_ID")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }


    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.Mechanics:
                mechSetup();
                break;
            case R.id.Kinematics:
                kinSetup();
                break;
            case R.id.Electricity:
                elecSetup();
                break;
            case R.id.Magnetism:
                magSetup();
                break;
            case R.id.Directions:
                directMe();
                break;
            case R.id.Exit:
                exit();
                break;
        }
    }

    private void mechSetup() {
        Intent mechanics = new Intent(getApplicationContext(), com.koins.physicsbotads.Mechanics.class);
        startActivity(mechanics);
    }

    private void kinSetup() {
        Intent kinematics = new Intent(getApplicationContext(), com.koins.physicsbotads.Kinematics.class);
        startActivity(kinematics);
    }

    private void elecSetup() {
        Intent electricity = new Intent(getApplicationContext(), com.koins.physicsbotads.Electricity.class);
        startActivity(electricity);
    }

    private void magSetup() {
        Intent magnetism = new Intent(getApplicationContext(), com.koins.physicsbotads.Magnetism.class);
        startActivity(magnetism);
    }

    private void directMe() {
        Intent direct = new Intent(getApplicationContext(), Directions.class);
        startActivity(direct);
    }

    private void exit() {
        this.finishAffinity();
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
