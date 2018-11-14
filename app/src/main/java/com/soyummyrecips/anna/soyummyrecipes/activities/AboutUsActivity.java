package com.soyummyrecips.anna.soyummyrecipes.activities;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import com.soyummyrecips.anna.soyummyrecipes.R;

public class AboutUsActivity extends AppCompatActivity {
    WebView mWebView;
    private BottomSheetBehavior mBottomSheetBehavior1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(this.getResources().getString(R.string.nav_about_us));

        mWebView = findViewById(R.id.codeitbro_web_view);
        mWebView.loadUrl("http://i.prypl.com/privacy-policy/privacy-policy.html");
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        View bottomSheet = findViewById(R.id.bottom_sheet1);
        mBottomSheetBehavior1 = BottomSheetBehavior.from(bottomSheet);
        mBottomSheetBehavior1.setPeekHeight(100);
        mBottomSheetBehavior1.setState(BottomSheetBehavior.STATE_COLLAPSED);
        Button mButton1 = (Button) findViewById(R.id.btn_privacypolocy);
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBottomSheetBehavior1.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    mBottomSheetBehavior1.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    mBottomSheetBehavior1.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            // finish the activity
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
