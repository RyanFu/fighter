package com.android.archermind.test;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewTest extends Activity {
	private WebView webView;
	private Handler handler = new Handler();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		webView = (WebView) findViewById(R.id.webkit);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.requestFocus();
		webView.setWebChromeClient(new WebChromeClient());
	
		webView.setWebChromeClient(new MyWebChromeClient());
		webView.addJavascriptInterface(new DemoJavaScriptInterface(), "demo");
		
		// 避免新开Android的系统browser中响应该链接
		webView.setWebViewClient(new WebViewClient(){   
		    public boolean shouldOverrideUrlLoading(WebView view, String url) {   
		        view.loadUrl(url);   
		        return true;    
		    }   
		}); 

		webView.loadUrl("file:///android_asset/AN/index.html");
	}

	final class DemoJavaScriptInterface {
		DemoJavaScriptInterface() {
		}

		/**
		 * * This is not called on the UI thread. Post a runnable to invoke *
		 * loadUrl on the UI thread.
		 */
		public void clickOnAndroid() {
			handler.post(new Runnable() {
				public void run() {
					webView.loadUrl("javascript:toTest()");
				}
			});
		}
	}	
	
	public boolean onKeyDown(int keyCoder, KeyEvent event) {
		webView.clearCache(true);
		webView.loadUrl("file:///android_asset/AN/index.html");
		
		return true;
		
		/*if (webView.canGoBack() && keyCoder == KeyEvent.KEYCODE_BACK) {
			// goBack()表示返回webView的上一页面
			webView.goBack(); 
			
			return true;
		}

		return false;*/
	}
	
	/**
	 * * Provides a hook for calling "alert" from javascript. Useful for *
	 * debugging your javascript.
	 */
	final class MyWebChromeClient extends WebChromeClient {
		@Override
		public boolean onJsAlert(WebView view, String url, String message,
				JsResult result) {
			Log.d("ARCHERMIND", message);
			// webView.loadData(URLEncoder.encode(message, "UTF-8"), "text/html", "UTF-8");
			webView.loadDataWithBaseURL ("file:///android_asset/AN/index.html", message, "text/html", "UTF-8", "") ;
			result.confirm();
			return true;
		}
	}	
}