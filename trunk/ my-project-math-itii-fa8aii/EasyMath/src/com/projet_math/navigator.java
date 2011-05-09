package com.projet_math;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class navigator extends Activity
{
	
	WebView webview;

	public void OnCreate(Bundle SavedInstance)
	{
		super.onCreate(SavedInstance);
		setContentView(R.layout.affichage_cours);
		webview =(WebView) findViewById(R.id.webview);
		webview.setWebViewClient(new myWebViewClient());
		webview.getSettings().setJavaScriptEnabled(true);
		webview.loadUrl("http://forum.frandroid.com/");
		
		Toast.makeText(this, "webview", Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
			webview.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	private class myWebViewClient extends WebViewClient 
		{
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
}
}