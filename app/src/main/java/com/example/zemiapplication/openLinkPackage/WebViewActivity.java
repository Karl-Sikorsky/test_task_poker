package com.example.zemiapplication.openLinkPackage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.zemiapplication.foreignPockerGame.MainActivity;
import com.example.zemiapplication.R;


public class WebViewActivity extends AppCompatActivity implements MvpContract.MainView {
    MvpContract.MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        mPresenter = new WebViewPresenter(this);

        mPresenter.loadAllow();


    }


    public void showError() {
        Log.d("AllowResult", "error");
        AlertDialog.Builder builder = new AlertDialog.Builder(WebViewActivity.this);
        builder.setTitle("Error")
                .setMessage("Bad internet connection");
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();

    }

    @Override
    public void showWebView(String link) {
        WebView vistaWeb = (WebView) findViewById(R.id.webView);
        vistaWeb.setWebChromeClient(new WebChromeClient());
        vistaWeb.setWebViewClient(new WebViewClient());
        vistaWeb.clearCache(true);
        vistaWeb.clearHistory();
        vistaWeb.getSettings().setJavaScriptEnabled(true);
        vistaWeb.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        vistaWeb.loadUrl(link);
    }

    @Override
    public void showGame() {
        Log.d("allowlog", "startgame");
        Intent intent = new Intent(WebViewActivity.this, MainActivity.class);
        startActivity(intent);
    }


}
