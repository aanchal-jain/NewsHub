package com.aanchal.godric.newshub;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager pager;
    private ViewPagerAdapter adapter;
    private TabLayout tabLayout;
    private int[] icons = {R.drawable.toi,R.drawable.cnn,R.drawable.nyt,R.drawable.th,R.drawable.mtv,R.drawable.espn,R.drawable.time};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkConnection();
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pager = findViewById(R.id.pager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(pager);
        setIcons();
    }
    private void checkConnection() {
        if(!isOnline()){
            Snackbar snackbar = Snackbar.make(findViewById(R.id.layout),"No Internet Connection",Snackbar.LENGTH_LONG);
            snackbar.show();
        }

    }

    private boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null && networkInfo.isConnectedOrConnecting()){
            return true;
        }
        else
            return false;

    }

    private void setIcons() {
        for(int i = 0;i<icons.length;i++){
            tabLayout.getTabAt(i).setIcon(icons[i]);
        }

    }
    @Override
    public void onBackPressed(){
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.exit_dialog);

        Button yes = dialog.findViewById(R.id.exit_dialog_yes);
        Button no = dialog.findViewById(R.id.exit_dialog_no);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
                System.exit(0);
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }
}
