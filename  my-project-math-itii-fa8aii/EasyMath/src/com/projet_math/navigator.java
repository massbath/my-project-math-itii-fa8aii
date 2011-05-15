package com.projet_math;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class navigator extends Activity
{private WebView webview;
private ProgressDialog progressBar;

@Override
protected void onCreate(Bundle savedInstanceState) 
	{
	super.onCreate(savedInstanceState);
	 
    requestWindowFeature(Window.FEATURE_NO_TITLE);

    setContentView(R.layout.affichage_cours);
    Bundle urlToOpen= this.getIntent().getExtras();
    webview = (WebView)findViewById(R.id.webview);

    webview.getSettings().setJavaScriptEnabled(true);
 

    final AlertDialog alertDialog = new AlertDialog.Builder(this).create();

    progressBar = ProgressDialog.show(this, "Veuillez patienter", "Chargement ...");
   
    webview.setWebViewClient(new WebViewClient() {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
           
            view.loadUrl(url);
            return true;
        }

       
        
        public void onPageFinished(WebView view, String url) {
           
            if (progressBar.isShowing()) {
                progressBar.dismiss();
            }
        }

        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
           
            alertDialog.setTitle("Error");
            alertDialog.setMessage(description);
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    return;
                }
            });
            alertDialog.show();
        }
    });
    
    webview.loadUrl(urlToOpen.getString("url"));
}
}