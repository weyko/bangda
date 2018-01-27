package com.weyko.shops;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.webkit.WebSettings;

import com.weyko.shops.base.BaseActivity;
import com.weyko.shops.databinding.ActivityWebBinding;
import com.weyko.shops.util.ThemeUtil;
import com.weyko.shops.view.ProgressWebView;

/**
 * 
 * @ClassName: CommonWebActivity
 * @Description: 通用内置浏览器类
 *
 */
public class CommonWebActivity extends BaseActivity<ActivityWebBinding>{
	public static final String WEB_TITLE = "TITLE";
	public static final String WEB_URL = "URL";
	private ProgressWebView browser = null;
	private String url = null;
	private String title = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		ThemeUtil.getInstance().setWindow(this);
		getIntentParams();
		super.onCreate(savedInstanceState);
		showTitle(title);
		showBack();
	}

	@Override
	protected int setContent() {
		return R.layout.activity_web;
	}

	private void getIntentParams() {
		Intent intent = getIntent();
		if (intent != null) {
			url = intent.getStringExtra(WEB_URL);
			title = intent.getStringExtra(WEB_TITLE);
		}
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (browser != null) {
			// browser.clearCache(true);
			browser.removeAllViews();
			browser.destroy();
		}
		System.gc();
	}
	public void initData() {
		browser=binding.webview;
		WebSettings setting = browser.getSettings();
		setting.setJavaScriptEnabled(true);// 支持js
		setting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
		setting.setUseWideViewPort(true);// 将图片调整到适合webview的大小
		// setting.setLoadWithOverviewMode(true);
		setting.setDatabasePath(this.getApplicationContext().getCacheDir().getAbsolutePath());
//		 setting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//禁止左右滚动
//		setting.setBuiltInZoomControls(false);
//		setting.setSupportZoom(false);
//		setting.setJavaScriptCanOpenWindowsAutomatically(true);// 支持通过JS打开新窗口
//		setting.setLoadsImagesAutomatically(true);// 支持自动加载图片
//		setting.setDomStorageEnabled(true);
//		setting.setDatabaseEnabled(true);
//		setting.setAppCacheEnabled(true);
//		browser.requestFocusFromTouch();
//		String ua = browser.getSettings().getUserAgentString();
//		browser.getSettings().setUserAgentString(ua+"bangda");
		View view = findViewById(R.id.web_title);
		if (view != null) {
			view.setVisibility(View.GONE);
		}

		if (url != null && url.length() > 0) {
			browser.loadUrl(url);
		}
	}
	public static void openWeb(FragmentActivity activity, String fileName, String title){
		String url="file:///android_asset/"+fileName+".html";
		Intent intent=new Intent(activity,CommonWebActivity.class);
		intent.putExtra(CommonWebActivity.WEB_URL,url);
		intent.putExtra(CommonWebActivity.WEB_TITLE,title);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.in_from_right,0);
	}
	@Override
	protected boolean onBackIntercept() {
		this.finish();
		return false;
	}

	@Override
	protected String headerTitle() {
		return "";
	}
}